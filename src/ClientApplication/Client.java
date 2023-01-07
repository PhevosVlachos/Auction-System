package ClientApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    int port;
    String serverMachine;
    String messageToServer = null;
    String serverResponse = null;

    Socket clientSocket;

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader inFromServer;

    PrintStream outToServer;


    public Client(){}

    public Client(int port, String serverMachine, String messageToServer, String serverResponse, Socket clientSocket, BufferedReader inFromUser, BufferedReader inFromServer, PrintStream outToServer) {
        this.port = port;
        this.serverMachine = serverMachine;
        this.messageToServer = messageToServer;
        this.serverResponse = serverResponse;
        this.clientSocket = clientSocket;
        this.inFromUser = inFromUser;
        this.inFromServer = inFromServer;
        this.outToServer = outToServer;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerMachine() {
        return serverMachine;
    }

    public void setServerMachine(String serverMachine) {
        this.serverMachine = serverMachine;
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

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public BufferedReader getInFromUser() {
        return inFromUser;
    }

    public void setInFromUser(BufferedReader inFromUser) {
        this.inFromUser = inFromUser;
    }

    public BufferedReader getInFromServer() {
        return inFromServer;
    }

    public void setInFromServer(BufferedReader inFromServer) {
        this.inFromServer = inFromServer;
    }

    public PrintStream getOutToServer() {
        return outToServer;
    }

    public void setOutToServer(PrintStream outToServer) {
        this.outToServer = outToServer;
    }

    @Override
    public String toString() {
        return "Client{" +
                "port=" + port +
                ", serverMachine='" + serverMachine + '\'' +
                ", messageToServer='" + messageToServer + '\'' +
                ", serverResponse='" + serverResponse + '\'' +
                ", clientSocket=" + clientSocket +
                ", inFromUser=" + inFromUser +
                ", inFromServer=" + inFromServer +
                ", outToServer=" + outToServer +
                '}';
    }
}
