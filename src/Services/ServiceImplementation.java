package Services;


import ClientApplication.Client;
import ClientApplication.ClientHandler;
import ServerApplication.Auction;
import ServerApplication.Bid;
import ServerApplication.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static ServerApplication.MuTCPEchoServer.auctions;

public class ServiceImplementation implements Service {


    @Override
    public Bid placeBid() {
        return null;
    }


    @Override
    public List<Auction> listAuctions() {
        return null;
    }

//    @Override
//    public Double checkHighest(Auction auction) {
//        List<Double> bids  = new ArrayList<Double>(auction.getAllBids());
//        Collections.sort(bids);
//        return bids.get(0);
//    }

//    @Override
//    public void connectToServer(Client myClient, String serverMachine, int port) throws Exception {
//
//        Socket clientSocket = new Socket(serverMachine, port);
//        PrintStream outStream = new PrintStream(clientSocket.getOutputStream());
//        BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//
//        myClient.setOutToServer(outStream);
//        myClient.setInFromServer(inStream);
//
//
//
//        System.out.println
//                ("\n--->> Connected to: " + clientSocket.getInetAddress()+ " on port " + port + " <<---\n\n" +
//
//                "Welcome to the Auction House!!! What would you like to do?" + "\n" + "\n"
//                );
//
//    }
    
//    public int auctionId(){
////        byte[] array = new byte[7]; // length is bounded by 7
////        new Random().nextBytes(array);
////        return new String(array, StandardCharsets.UTF_8);
//
//
//    }


    public Auction findById(int id){
        for (Auction a:auctions) {
            if (a.getAuctionID() == id)
                return a;
            else
                return null;
        }
        return null;
    }

    public Double checkHighest(Auction auction) {
        List<Bid> bids  = auction.getAllBids();
        List<Double> prices= new ArrayList<>();
        for (Bid a:bids
        ) {
            prices.add(a.getPrice());
        }
        Collections.sort(prices);
        return prices.get(0);
    }

    @Override
    public void sendToClient(Handler myHandler)  {
        myHandler.getOutToClient().println(myHandler.getResponse());
        System.out.println("Sent To Client:" + myHandler.getResponse());
    }

    @Override
    public void receiveFromClient(Handler myHandler) throws IOException {
        myHandler.setClientSentence(myHandler.getInFromClient().readLine());
        System.out.println("Message Received: " + myHandler.getClientSentence());
    }

    @Override
    public void getUserInput(ClientHandler myClientHandler) throws IOException {
        /* Get user's input */
        myClientHandler.setMessageToServer(myClientHandler.getInFromUser().readLine());
    }

    @Override
    public void sendToServer(ClientHandler myClientHandler) throws IOException {

        /* Send the message to server */
        myClientHandler.getOutToServer().println(myClientHandler.getMessageToServer());
    }

    @Override
    public void receiveFromServer(ClientHandler myClientHandler) throws IOException {

        /* Read the server's response */
        myClientHandler.setServerResponse(myClientHandler.getInFromServer().readLine());


    }

    @Override
    public void printServerResponse(ClientHandler myClientHandler) {
        /* Display echoed message from server */
        System.out.println("\nServer Responded:");
        System.out.println(myClientHandler.getServerResponse());
    }
}
