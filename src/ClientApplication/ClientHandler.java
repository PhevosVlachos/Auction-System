package ClientApplication;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler {

    /* Our socket end */
    Socket clientSocket;
    /* For writing to socket */
    PrintStream outToServer;
    // For reading from socket */
    BufferedReader inFromServer;
    /* For reading from user */
    BufferedReader inFromUser;
    /* Hold user input */
    String messageToServer = null, serverResponse = null;

    public ClientHandler() {

    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public PrintStream getOutToServer() {
        return outToServer;
    }

    public void setOutToServer(PrintStream outToServer) {
        this.outToServer = outToServer;
    }

    public BufferedReader getInFromServer() {
        return inFromServer;
    }

    public void setInFromServer(BufferedReader inFromServer) {
        this.inFromServer = inFromServer;
    }

    public BufferedReader getInFromUser() {
        return inFromUser;
    }

    public void setInFromUser(BufferedReader inFromUser) {
        this.inFromUser = inFromUser;
    }

    public String getMessageToServer() {
        return messageToServer;
    }

    public void setMessageToServer(String messageToServer) {
        this.messageToServer = messageToServer;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
    }
}
