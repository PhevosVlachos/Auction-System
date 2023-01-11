package ServerApplication;

import ServerApplication.Auction;
import ServerApplication.Bid;
import Services.ServiceImplementation;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ServerApplication.MuTCPEchoServer.auctions;

public class ConnectionHandler implements Runnable {
    // Socket for our endpoint
    protected Socket echoSocket;

    public ConnectionHandler(Socket aSocketToHandle) {
        echoSocket = aSocketToHandle;
    }

    /**
     * New thread for handling client interaction will start here.
     */
    public void run() {
        ServiceImplementation service = new ServiceImplementation();
        Handler myHandler = new Handler();


        // Attach a println/readLine interface to the socket so we can read and write strings to the socket.
        try {
            /* Get the IP address from the client */
            myHandler.setPeerName(echoSocket.getInetAddress().getHostAddress());
            /* Create a writing stream to the socket */
            myHandler.setOutToClient(new PrintStream(echoSocket.getOutputStream(), true));
            /* Create a reading stream to the socket */
            myHandler.setInFromClient(new BufferedReader(new InputStreamReader(echoSocket.getInputStream())));
        } catch (IOException e) {
            System.out.println("Error creating buffered handles.");
            return;
        }

        System.out.println("Handling connection to client at " + myHandler.peerName + " --");

        while (true) {
            try {
                /* Read client's message through the socket's input buffer */
                service.receiveFromClient(myHandler);
            } catch (IOException e) {
                System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                break;
            }

            /* Send it back through socket's output buffer */
            myHandler.setResponse(myHandler.getClientSentence());
            service.sendToClient(myHandler);


            switch (myHandler.getClientSentence()) {

                case "1":

                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }

                    String name = myHandler.clientSentence;
                    System.out.println("Name is:" + name);

                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);
                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }

                    String description = myHandler.clientSentence;
                    System.out.println("Description :" + description);

                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);
                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }

                    double startingPrice = Double.parseDouble(myHandler.clientSentence);
                    System.out.println("Start Price :" + startingPrice);

                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);
                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }

                    String closingType = myHandler.clientSentence;
                    System.out.println("Closing Type :" + closingType);

                    /* Create Auction */
                    Auction auction = new Auction(name, startingPrice, closingType, new ArrayList<Bid>());
                    auctions.add(auction);
                    for (Auction a : auctions) {
                        a.setAuctionID(auctions.indexOf(a) + 1);
                    }
                    System.out.println("Successfully created auction");

                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);

                    break;
                case "2":

                    /* Send size of list to client */

                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }
                    myHandler.setResponse(String.valueOf(auctions.size()));
                    service.sendToClient(myHandler);


                    for (Auction a : auctions) {

                        myHandler.response = a.toString();

                        service.sendToClient(myHandler);

                        try {
                            /* Read client's message through the socket's input buffer */
                            service.receiveFromClient(myHandler);
                        } catch (IOException e) {
                            System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                            break;
                        }
                    }


                    break;


                case "3":
                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }

                    for (Auction a : auctions) {
                        int id = a.getAuctionID();

                        if (id == Integer.parseInt(myHandler.clientSentence)) {
                            a.clientID.add(echoSocket.getInetAddress());
                            System.out.println(a.clientID.get(0));
                        }
                    }

                    service.sendToClient(myHandler);

                    break;

                case "4":
                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }
                    service.sendToClient(myHandler);

                    int id = Integer.parseInt(myHandler.clientSentence);


                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }
                    service.sendToClient(myHandler);


                    double price = Double.parseDouble(myHandler.clientSentence);
                    System.out.println("Bidding price : " + price);

                    for (Auction a : auctions) {

                        if (a.auctionID == id) {

                            Bid bid = new Bid(price, LocalTime.now(), a);
                            a.allBids.add(bid);

                            System.out.println(bid.toString());
                            System.out.println(a.toString());
                        }
                    }





                    break;

                case "6":
                    service.sendToClient(myHandler);
                    break;

                case "7":
                    System.out.println("Closing connection with " + echoSocket.getInetAddress() + ".");
                    break;

            }



            /* If message is exit then terminate specific connection - exit the loop */
//				if ( clientSentence.equals( "exit" ) ) {
//					System.out.println( "Closing connection with " + echoSocket.getInetAddress() + "." );
//					break;
//				}


        }

        System.out.println("Closing " + myHandler.peerName + " connection");

        // Close all the handles
        try {
            /* Close input stream */
            myHandler.inFromClient.close();
            /* Close output stream */
            myHandler.outToClient.close();
            /* Close TCP connection with client on specific port */
            echoSocket.close();
        } catch (IOException e) {
        }

    }  /* End run method */

} // end class

