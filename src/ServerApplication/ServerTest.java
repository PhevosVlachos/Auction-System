package ServerApplication;

import Services.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main( String[] args ) throws Exception {
        String clientSentence;
        String response = null;
        int port;
        Socket connectionSocket = null;
        BufferedReader inFromClient;
        PrintStream outToClient;


        /* port is the argument passed to program */
        port = 4567;

        /* Create socket for port */
        ServerSocket welcomeSocket = new ServerSocket( port );

        System.out.println("Server is up and running. Waiting on port " + welcomeSocket.getLocalPort());

        /* Wait endlessly for connections */
        while(true) {
            /* Accept the connection */
            connectionSocket = welcomeSocket.accept();


            /* Create a reading stream to the socket */
            inFromClient = new BufferedReader( new InputStreamReader( connectionSocket.getInputStream() ) );

            /* Create a writing stream to the socket */
            outToClient = new PrintStream( connectionSocket.getOutputStream() );

            System.out.println("Accepted connection from: " + connectionSocket.getInetAddress() );

            /* Wait endlessly for specific client to type messages */
            while ( true ) {
                clientSentence = null;
                try {
                    /* Read client's message through the socket's input buffer */
                    clientSentence = inFromClient.readLine();
                }
                catch (IOException e) {
                    System.out.println( connectionSocket.getInetAddress() + " broke the connection." );
                    break;
                }

                /* Output to screen the message received by the client */
                System.out.println( "Message Received: " + clientSentence );





                /* If message is exit then terminate specific connection - exit the loop */
                if ( clientSentence.equals( "exit" ) ) {
                    System.out.println( "Closing connection with " + connectionSocket.getInetAddress() + "." );
                    break;
                }



                /* Send it back through socket's output buffer */
                outToClient.println( response );
            }
            /* Close input stream */
            inFromClient.close();

            /* Close output stream */
            outToClient.close();

            /* Close TCP connection with client on specific port */
            connectionSocket.close();

            /* Wait for more connections */
            System.out.println( "Server waiting at port: " + welcomeSocket.getLocalPort() );
        }
    }
}
