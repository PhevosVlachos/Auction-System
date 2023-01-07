package ClientApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientTest {

    public static void main(String[] args) throws Exception {
        String messageToServer = null;
        String serverResponse = null;
        String serverMachine;
        int port;

        /* The first argument is the server's name */
        serverMachine = "localhost";

        /* The second argument the port that the server accepts connections */
        port = 4567;

        /* Create a buffer to hold the user's input */
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        /* Create the client socket according to the server's address and port */
        Socket clientSocket = new Socket(serverMachine, port);

        /* Display a connection established message  */
        System.out.println("Connected to: " + clientSocket.getInetAddress() + " on port " + port);

        /* Create a writing buffer to the socket */
        PrintStream outToServer = new PrintStream(clientSocket.getOutputStream());

        /* Create a reading buffer to the socket */
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {


            /* Get user's input */
            messageToServer = inFromUser.readLine();

            /* Send the message to server */
            outToServer.println(messageToServer);

            /* Stop infinite loop if user wants to stop getting echos by typing exit */
            if (messageToServer.equals("exit"))
                break;

            /* Read the server's response */
            serverResponse = inFromServer.readLine();

            /* Display echoed message from server */
            System.out.println(serverResponse);
        }

        System.out.println("Closing socket.");
        clientSocket.close();
    }

}
