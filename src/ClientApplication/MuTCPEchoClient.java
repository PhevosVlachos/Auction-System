package ClientApplication;/*
 * Filename: TCPEchoClient.java
 * Description: An echo client using connection-oriented delivery system (TCP).
 *              Sends character messages to a server which are echoed capitalized.
 *              Error handling and exceptions are implemented!
 * Operation: java TCPEchoClient [hostname] [port]
 *
 */

import ClientApplication.ClientHandler;
import Services.ServiceImplementation;

import java.io.*; 
import java.net.*; 


public class MuTCPEchoClient
{
	public void connect(String host, int port)
	{
		ServiceImplementation service = new ServiceImplementation();
		ClientHandler myHandler = new ClientHandler();
        
		System.out.println("-- Client connecting to host/port " + host + "/" + port + " --");

			/* Connect to the server at the specified host/port */
            try {
                myHandler.clientSocket = new Socket( host, port );
				/* Create a buffer to hold the user's input */
				myHandler.inFromUser = new BufferedReader( new InputStreamReader( System.in ) ); 
				/* Create a writing buffer to the socket */
				myHandler.outToServer = new PrintStream( myHandler.clientSocket.getOutputStream(), true); 
				/* Create a reading buffer to the socket */
				myHandler.inFromServer = new BufferedReader(new InputStreamReader( myHandler.clientSocket.getInputStream()) ); 
            } catch (UnknownHostException e) {
				System.out.println("Can not locate host/port " + host + "/" + port);
				return;
            } catch (IOException e) {
				System.out.println("Could not establish connection to: " + host + "/" + port);
				return;
            }

            System.out.println("<-- Connection established  -->");
            try
            {
                /* Continue forever until user types 'exit' */
				/* Types sentences to the server which are returned capitalized */
				while ( true ) {
					
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
					        
					service.sendToServer(myHandler);
					service.receiveFromServer(myHandler);

					switch (myHandler.serverResponse){
						case "1":
							System.out.println("Please input a name:");
							service.sendToServer(myHandler);
							service.receiveFromServer(myHandler);

							System.out.println("Please enter a description for your item:");
							service.sendToServer(myHandler);
							service.receiveFromServer(myHandler);


							System.out.println("Please enter a starting price:");
							service.sendToServer(myHandler);
							service.receiveFromServer(myHandler);

							System.out.println
									(
											"Please enter a closing type from these options: " + "\n" +
													"Set Time" + "\n" +
													"Bid Time" + "\n "
									);
							service.sendToServer(myHandler);
							service.receiveFromServer(myHandler);
							break;

						case "2":

							service.receiveFromServer(myHandler);
							break;

						case "3":
							break;

						case "4":
							System.out.println("Please select a bidding price: ");
							service.sendToServer(myHandler);
							service.receiveFromServer(myHandler);
							break;

						case "5":
							break;

						case "6":
							System.out.println("Withdrawing from Auction");
							service.sendToServer(myHandler);
							service.receiveFromServer(myHandler);
							break;

					}

					/* Stop infinite loop if user wants to stop getting echos by typing exit */
					if ( myHandler.messageToServer.equals( "7" ) ){
						System.out.println("Goodbye");
						break;
					}


			
		
				}
				
                // Close all of our connections.
                myHandler.outToServer.close();
                myHandler.inFromServer.close();
                myHandler.clientSocket.close();
            } catch (IOException e) {
				System.out.println("I/O to socket failed: " + host);
            }
	}  /* End Connect Method */

	public static void main( String[] args )
	{
		/* Holds the server's name */
		String server;
		/* Holds the server's port number  */
		int port;
			
		/* The first argument is the server's name */
		server = "localhost";
		/* The second argument the port that the server accepts connections */
		port = 4579;
			
		/* Create a new instance of the client */
		MuTCPEchoClient myclient = new MuTCPEchoClient();

		/* Make a connection. It should not return until the client exits */
		myclient.connect(server, port);

		System.out.println("<-- Client has exited -->");
     } /* End main method */

} // MuTCPEchoClient

/*
 * Example:
 *   java TCPEchoClient machinename 4567
 * Output:
 *	 Connected to: machinename/IPaddress on port 4567
 *	 Type a message to send to server:
 *   Hallo server
 *   Server returned: HALLO SERVER
 */

/*** EXTRA INFORMATION ***/
/*
BufferedReader: it supports input buffering. It provides the readLine() method for reading an entire line at a
                time from a stream.
InputStreamReader: reads a stream. It is used to convert between byte streams and character streams. It provides
				   a bridge between byte-oriented and character-oriented input streams.
PrintStream: Prints to an output stream.
*/