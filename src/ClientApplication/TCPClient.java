package ClientApplication;

import Services.ServiceImplementation;

import java.sql.SQLOutput;
import java.util.Objects;

public class TCPClient {
    public static void main(String[] args) throws Exception {

        String testo;

        ServiceImplementation service = new ServiceImplementation();
        Client myClient = new Client();


        service.connectToServer(myClient, "localhost", 4579);

        while (true) {
            System.out.println
                    ("\n" + "1. Enlist An Item" + "\n" +
                            "2. List Active Auctions" + "\n" +
                            "3. Register In An Auction" + "\n" +
                            "4. Place A Bid" + "\n" +
                            "5. Check Highest Bid" + "\n" +
                            "6. Withdraw From An Auction" + "\n" +
                            "7. Disconnect" + "\n" + "\n" +
                            "Choose by typing 1-7:" + "\n"
                    );



            /* Get user's input */
            myClient.messageToServer = myClient.inFromUser.readLine();

            /* Send the message to server */
            myClient.outToServer.println(myClient.messageToServer);



            /* Stop infinite loop if user wants to stop getting echos by typing exit */
            if (myClient.messageToServer.equals("exit"))
                break;

            /* Read the server's response and echo message*/
            service.receiveFromServer(myClient);


            if (myClient.serverResponse.equals("1")) {

                System.out.println("Please input a name:");
                service.sendToServer(myClient);
                service.receiveFromServer(myClient);

                System.out.println("Please enter a description for your item:");
                service.sendToServer(myClient);
                service.receiveFromServer(myClient);


                System.out.println("Please enter a starting price:");
                service.sendToServer(myClient);
                service.receiveFromServer(myClient);

                System.out.println
                        (
                                "Please enter a closing type from these options: " + "\n" +
                                        "Set Time" + "\n" +
                                        "Bid Time" + "\n "
                        );
                service.sendToServer(myClient);
                service.receiveFromServer(myClient);


            }


        }


    }
}