package ClientApplication;

import Services.ServiceImplementation;

public class TCPClient2 {
    public static void main(String[] args) throws Exception {



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
            /* Send the message to server */
            service.sendToServer(myClient);
            service.receiveFromServer(myClient);



            /* Stop infinite loop if user wants to stop getting echos by typing exit */
            if (myClient.messageToServer.equals("7")) {
                System.out.println("Goodbye");
                break;
            }


            switch (myClient.serverResponse){
                case "1":
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
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    System.out.println("Please select a bidding price: ");
                    service.sendToServer(myClient);
                    service.receiveFromServer(myClient);
                    break;

                case "5":
                    break;

                case "6":
                    System.out.println("Withdrawing from Auction");
                    service.sendToServer(myClient);
                    service.receiveFromServer(myClient);
                    break;

            }










        }

        System.out.println("Closing socket.");
        myClient.clientSocket.close();

    }

}
