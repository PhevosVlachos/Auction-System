package Services;

import ClientApplication.Client;
import ServerApplication.Auction;
import ServerApplication.Bid;
import ServerApplication.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<Auction> listAuctions() {
        return null;
    }

    @Override
    public Double checkHighest(Auction auction) {
        List<Double> bids  = new ArrayList<Double>(auction.getAllBids());
        Collections.sort(bids);
        return bids.get(0);
    }

    @Override
    public void getServer(Client myClient, String serverMachine, int port) throws Exception {

        myClient.setServerMachine(serverMachine);
        myClient.setPort(port);
        myClient.setServerAdress(InetAddress.getByName(myClient.getServerMachine()));



        System.out.println
                ("\n--->> Sending to: " + myClient.getServerAdress()+ " on port " + port + " <<---\n\n" +

                "Welcome to the Auction House!!! What would you like to do?" + "\n" + "\n"
                );

    }


    @Override
    public void acceptConnections(Server myServer) throws Exception {

//        myServer.setClientSocket(myServer.getSocket().accept());
//
//
//        BufferedReader inStream = new BufferedReader(new InputStreamReader(myServer.getClientSocket().getInputStream()));
//        PrintStream outStream = new PrintStream(myServer.getClientSocket().getOutputStream());
//
//
//        myServer.setInFromClient(inStream);
//        myServer.setOutToClient(outStream);
//
//        System.out.println("Accepted connection from: " + myServer.getClientSocket().getInetAddress());

    }



    @Override
    public void runServer(Server myServer, int port) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(port);
        DatagramPacket inDatagram = new DatagramPacket(myServer.getBuffer(), myServer.getMaxLength());
      ;

        myServer.setPort(port);
        myServer.setSocket(serverSocket);
        myServer.setInFromClient(inDatagram);


        System.out.println("Server is up and running. Waiting on port " + serverSocket.getLocalPort());
    }

    @Override
    public void sendToClient(Server myServer) throws Exception {
        /* Create a new datagram data buffer (byte array) for echoing capitalized message */
        myServer.setResponse(myServer.getClientSentence().getBytes());

        /* Create an outgoing datagram by extracting the client's address and port from incoming datagram */
       myServer.setOutToClient(new DatagramPacket( myServer.getResponse(), myServer.getResponse().length,
                myServer.getInFromClient().getAddress(), myServer.getInFromClient().getPort() ));

        /* Send the reply back to the client */
        myServer.getSocket().send( myServer.getOutToClient() );
    }

    @Override
    public void receiveFromClient(Server myServer) throws Exception {
        /* Receive the datagram from the client  */
        myServer.getSocket().receive( myServer.getInFromClient() );

        /* Convert the message from the byte array to a string array for displaying */
        myServer.setClientSentence(new String( myServer.getInFromClient().getData(), 0, myServer.getInFromClient().getLength() ));

        /* Display the message on the screen */
        System.out.println( "\nMessage received from " + myServer.getInFromClient().getAddress() + " from port "
                + myServer.getInFromClient().getPort() + ".\nContent: " + myServer.getClientSentence());
    }

    @Override
    public void sendToServer(Client myClient) throws Exception {
        /* Create a buffer to hold the user's input */
        BufferedReader userInput = new BufferedReader( new InputStreamReader ( System.in ) );

        /* Get the user's input */
        myClient.setMessageToServer(userInput.readLine());

        /* Create array of 255 bytes to hold outgoing message */
        byte[] data = new byte[myClient.getMaxLength()];
        data = myClient.getMessageToServer().getBytes();

        /* Create datagram to send to server specifying message, message length, server address, port */
        DatagramPacket outToServer = new DatagramPacket( data, data.length, myClient.getServerAdress(), myClient.getPort() );

        /* Create a datagram socket through which the data will be sent */
        DatagramSocket socket = new DatagramSocket();
        myClient.setSocket(socket);

        /* Send the datagram through the socket */
        myClient.getSocket().send( outToServer );
    }

    @Override
    public void receiveFromServer(Client myClient) throws Exception {

        /* Create array of 255 raw bytes to hold incomingmessage */
        byte [] response = new byte[myClient.getMaxLength()];

        /* Create a datagram to receive from server specifying the message received */
        myClient.setInFromServer(new DatagramPacket(response, myClient.getMaxLength() ));

        /* Receive the echo datagram from server (capitalized) */
        myClient.getSocket().receive( myClient.getInFromServer() );

        /* Convert received byte array to string for displaying */
        myClient.setServerResponse(new String( myClient.getInFromServer().getData(), 0, myClient.getInFromServer().getLength()));

        /* Output echoed message to the screen */
        System.out.println( "Received: " + myClient.getServerResponse() );
    }
}
