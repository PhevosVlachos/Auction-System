package ServerApplication;

import Services.ServiceImplementation;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
                service.receiveFromClient(myServer);

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
                        
                        Auction auction = new Auction("1", name, startingPrice, closingType, new ArrayList<Bid>());
                        auctions.add(auction);
                        System.out.println(auctions.toString());

                        break;

                    case "4":
                        service.sendToClient(myServer);
                        service.receiveFromClient(myServer);
                        double price = Double.parseDouble(myServer.clientSentence);
                        System.out.println("Bidding price : " + price);



                        Bid bid = new Bid(price, LocalTime.now(), auctions.get(0));

                        auctions.get(0).allBids.add(bid);

                        System.out.println(bid.toString());
                        System.out.println(auctions.get(0).toString());

                    case "exit":
                        System.out.println("Closing connection with " + myServer.clientSocket.getInetAddress() + ".");
                        break;

                }

//                if (myServer.clientSentence.equals("1")) {
//                    service.sendToClient(myServer);
//                    service.receiveFromClient(myServer);
//
//                }

                /* If message is exit then terminate specific connection - exit the loop */
//                if (myServer.clientSentence.equals("exit")) {
//                    System.out.println("Closing connection with " + myServer.clientSocket.getInetAddress() + ".");
//                    break;
//                }

                myServer.response = myServer.clientSentence;

                /* Send it back through socket's output buffer */
                myServer.outToClient.println(myServer.response);

            }
        }
    }
}
