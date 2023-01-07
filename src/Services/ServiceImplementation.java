package Services;

import ClientApplication.Client;
import ServerApplication.Auction;
import ServerApplication.Bid;
import ServerApplication.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class ServiceImplementation implements Service {


    @Override
    public Auction createAuction() {
        return null;
    }

    @Override
    public Bid placeBid() {
        return null;
    }

    @Override
    public void auctionRegister() {

    }

    @Override
    public List<Auction> listAuctions() {
        return null;
    }

    @Override
    public Bid checkHighest() {
        return null;
    }

    @Override
    public void connectToServer(Client myClient, String serverMachine, int port) throws Exception {

        Socket clientSocket = new Socket(serverMachine, port);
        PrintStream outStream = new PrintStream(clientSocket.getOutputStream());
        BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        myClient.setOutToServer(outStream);
        myClient.setInFromServer(inStream);



        System.out.println
                ("\n--->> Connected to: " + clientSocket.getInetAddress()+ " on port " + port + " <<---\n\n" +

                "Welcome to the Auction House!!! What would you like to do?" + "\n" + "\n"
                );

    }


    @Override
    public void acceptConnections(Server myServer) throws Exception {

        myServer.setClientSocket(myServer.getServerSocket().accept());


        BufferedReader inStream = new BufferedReader(new InputStreamReader(myServer.getClientSocket().getInputStream()));
        PrintStream outStream = new PrintStream(myServer.getClientSocket().getOutputStream());


        myServer.setInFromClient(inStream);
        myServer.setOutToClient(outStream);

        System.out.println("Accepted connection from: " + myServer.getClientSocket().getInetAddress());

    }

    @Override
    public void runServer(Server myServer, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);

        myServer.setPort(port);
        myServer.setServerSocket(serverSocket);


        System.out.println("Server is up and running. Waiting on port " + serverSocket.getLocalPort());




    }


}
