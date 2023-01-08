package ServerApplication;

import Services.ServiceImplementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TCPServer {

    public static void main(String[] args) throws Exception {
        ServiceImplementation service = new ServiceImplementation();
        Server myServer = new Server();
        List<Auction> Auctions = new ArrayList<>();


        service.runServer(myServer, 4567);

        while (true) {
            service.acceptConnections(myServer);


            /* Wait endlessly for specific client to type messages */
            while (true) {
                service.receiveFromClient(myServer);

                if (myServer.clientSentence.equals("1")) {
                    service.sendToClient(myServer);
                    service.receiveFromClient(myServer);

                }

                /* If message is exit then terminate specific connection - exit the loop */
                if (myServer.clientSentence.equals("exit")) {
                    System.out.println("Closing connection with " + myServer.clientSocket.getInetAddress() + ".");
                    break;
                }

                myServer.response = myServer.clientSentence;

                /* Send it back through socket's output buffer */
                myServer.outToClient.println(myServer.response);

            }
        }
    }
}
