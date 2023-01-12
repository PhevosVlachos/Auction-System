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


                    /* Read client's message through the socket's input buffer */
                    try {
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }


                    /* Storing the name to create an auction */
                    String name = myHandler.clientSentence;
                    System.out.println("Name is:" + name);


                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);


                    /* Read client's message through the socket's input buffer */
                    try {
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }


                    /* Storing a description for the auction */
                    String description = myHandler.clientSentence;
                    System.out.println("Description :" + description);


                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);


                    /* Read client's message through the socket's input buffer */
                    try {
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }


                    /* Store a starting price for the auction */
                    double startingPrice = -1;

                    while (startingPrice == -1) {
                        try {
                            startingPrice = Double.parseDouble(myHandler.clientSentence);
                            System.out.println("Start Price :" + startingPrice);
                        } catch (Exception e) {
                            System.out.println("Not a valid price. Requesting price from client:");
                            myHandler.clientSentence = "try again";
                            myHandler.setResponse(myHandler.getClientSentence());
                            service.sendToClient(myHandler);
                            try {
                                service.receiveFromClient(myHandler);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }


                    /* Send it back through socket's output buffer */
                    myHandler.setResponse(myHandler.getClientSentence());
                    service.sendToClient(myHandler);


                    /* Read client's message through the socket's input buffer */
                    try {
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }


                    /* Storing a closing type for the auction */
                    String closingType = myHandler.clientSentence;
                    System.out.println("Closing Type :" + closingType);


                    /* Create Auction */
                    Auction auction = new Auction(name, description, startingPrice, closingType, echoSocket.getInetAddress(), new ArrayList<>());
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
                        int id = Integer.parseInt(myHandler.clientSentence);
                        Auction a = service.findById(id);
                        if (auctions.contains(a)) {
                            a.clientID.add(echoSocket.getInetAddress());
                            System.out.println(a.clientID.get(0));
                        } else {
                            System.out.println("Error");
                        }
                        service.sendToClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }


                    break;

                case "4":

                    try {
                        /* Read client's message through the socket's input buffer */
                        service.receiveFromClient(myHandler);
                    } catch (IOException e) {
                        System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                        break;
                    }


                    int id = -1;

                    while (id == -1) {
                        try {
                            id = Integer.parseInt(myHandler.clientSentence);
                            System.out.println("Id is :" + id);
                        } catch (Exception e) {
                            System.out.println("Not a valid id. Requesting id from client:");
                            myHandler.clientSentence = "try again";
                            myHandler.setResponse(myHandler.getClientSentence());
                            service.sendToClient(myHandler);
                            try {
                                service.receiveFromClient(myHandler);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }


                    while (true) {

                        boolean auctionExists = false;


                        for (Auction a : auctions) {
                            int auctionID = a.getAuctionID();
                            if (auctionID == Integer.parseInt(myHandler.clientSentence)) {
                                auctionExists = true;
                            }
                        }


                        if (auctionExists) {

                            /* Send it back through socket's output buffer */
                            myHandler.setResponse(myHandler.getClientSentence());
                            service.sendToClient(myHandler);
                            System.out.println(myHandler.clientSentence);

                            try {
                                /* Read client's message through the socket's input buffer */
                                service.receiveFromClient(myHandler);
                                break;
                            } catch (IOException e) {
                                System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                                break;
                            }

                        } else {

                            myHandler.clientSentence = "Auction does not exist";
                            myHandler.setResponse(myHandler.getClientSentence());
                            service.sendToClient(myHandler);

                            try {
                                /* Read client's message through the socket's input buffer */
                                service.receiveFromClient(myHandler);
                            } catch (IOException e) {
                                System.out.println(echoSocket.getInetAddress() + "-" + myHandler.peerName + " broke the connection.");
                                break;
                            }

                        }

                    }


                    double price = -1;


                    while (price == -1) {
                        try {
                            price = Integer.parseInt(myHandler.clientSentence);
                            System.out.println("Bidding Price is :" + price);
                        } catch (Exception e) {
                            System.out.println("Not a valid price. Requesting price from client:");
                            myHandler.clientSentence = "try again";
                            myHandler.setResponse(myHandler.getClientSentence());
                            service.sendToClient(myHandler);
                            try {
                                service.receiveFromClient(myHandler);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }


                        for (Auction a : auctions) {


                            if (a.auctionID == id && a.getHighestBid() < price) {


                                Bid bid = new Bid(price, LocalTime.now(), a);
                                a.allBids.add(bid);
                                a.highestBid = price;
                                System.out.println(bid.toString());
                                System.out.println(a.toString());

                            }
                        }


                        myHandler.setResponse(myHandler.getClientSentence());
                        service.sendToClient(myHandler);


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

