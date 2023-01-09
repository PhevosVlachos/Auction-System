package ServerApplication;

import Services.ServiceImplementation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TCPServer {

    public static void main(String[] args) throws Exception {
        ServiceImplementation service = new ServiceImplementation();
        Server myServer = new Server();
        List<Auction> auctions = new ArrayList<>();




        service.runServer(myServer, 4579);

        while (true) {
            service.acceptConnections(myServer);


            /* Wait endlessly for specific client to type messages */
            while (true) {

                try {
                    /* Read client's message through the socket's input buffer */
                    service.receiveFromClient(myServer);
                }
                catch (IOException e) {
                    System.out.println( myServer.clientSocket.getInetAddress() + " broke the connection." );
                    break;
                }



                switch (myServer.clientSentence) {

                    case "1":
                        service.sendToClient(myServer);
                        service.receiveFromClient(myServer);
                        String name = myServer.clientSentence;
                        System.out.println("Name is:" + name);

                        service.sendToClient(myServer);
                        service.receiveFromClient(myServer);
                        String description = myServer.clientSentence;
                        System.out.println("Description :" + description);

                        service.sendToClient(myServer);
                        service.receiveFromClient(myServer);
                        double startingPrice = Double.parseDouble(myServer.clientSentence);
                        System.out.println("Start Price :" + startingPrice);

                        service.sendToClient(myServer);
                        service.receiveFromClient(myServer);
                        String closingType = myServer.clientSentence;
                        System.out.println("Closing Type :" + closingType);

                        Auction auction = new Auction(service.auctionId(), name, startingPrice, closingType, new ArrayList<Double>());
                        auctions.add(auction);
                        System.out.println(auctions.toString());

                        break;
                    case "2":
                        System.out.println(auctions.toString());
                        break;

                    case "4":
                        service.sendToClient(myServer);
                        service.receiveFromClient(myServer);
                        double price = Double.parseDouble(myServer.clientSentence);
                        System.out.println("Bidding price : " + price);



                        Bid bid = new Bid(price, LocalTime.now(), auctions.get(0));

                        auctions.get(0).allBids.add(bid.price);

                        System.out.println(bid.toString());
                        System.out.println(auctions.get(0).toString());

                        break;

                    case "6":

                        break;

                    case "7":
                        System.out.println("Closing connection with " + myServer.clientSocket.getInetAddress() + ".");
                        break;

                }

                myServer.response = myServer.clientSentence;

                /* Send it back through socket's output buffer */
                myServer.outToClient.println(myServer.response);

            }

            /* Close input stream */
            myServer.inFromClient.close();

            /* Close output stream */
            myServer.outToClient.close();

            /* Close TCP connection with client on specific port */
            myServer.clientSocket.close();

            /* Wait for more connections */
            System.out.println( "Server waiting at port: " + myServer.serverSocket.getLocalPort() );
        }
    }
}
