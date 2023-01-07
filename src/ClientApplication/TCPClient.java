package ClientApplication;

import Services.ServiceImplementation;

public class TCPClient {
    public static void main(String[] args) throws Exception{

        ServiceImplementation service = new ServiceImplementation();
        Client myClient = new Client();


        service.connectToServer(myClient, "localhost", 4567);

        while (true) {
            System.out.println(
                    "1. Enlist An Item" + "\n" +
                    "2. List Active Auctions" + "\n" +
                    "3. Register In An Auction" + "\n" +
                    "4. Place A Bid" + "\n" +
                    "5. Check Highest Bid" + "\n" +
                    "6. Withdraw From An Auction" + "\n" +
                    "7. Disconnect" + "\n");

            /* Get user's input */
            myClient.messageToServer = myClient.inFromUser.readLine();

            /* Send the message to server */
            myClient.outToServer.println(myClient.messageToServer);

            /* Stop infinite loop if user wants to stop getting echos by typing exit */
            if (myClient.messageToServer.equals("exit"))
                break;

            /* Read the server's response */
            myClient.serverResponse = myClient.inFromServer.readLine();

            /* Display echoed message from server */
            System.out.println(myClient.serverResponse);
        }




    }
}