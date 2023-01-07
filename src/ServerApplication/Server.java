package ServerApplication;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int port;

    Socket clientSocket = null;
    ServerSocket serverSocket = null;

    //     Input output streams
    BufferedReader inFromClient;
    PrintStream outToClient;

    //    Ingoing outgoing messages
    String clientSentence;
    String response;

    public Server() {
    }

    public Server(int port, Socket clientSocket, ServerSocket serverSocket, BufferedReader inFromClient, PrintStream outToClient) {
        this.port = port;
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket connectionSocket) {
        this.clientSocket = connectionSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket welcomeSocket) {
        this.serverSocket = welcomeSocket;
    }

    public BufferedReader getInFromClient() {
        return inFromClient;
    }

    public void setInFromClient(BufferedReader inFromClient) {
        this.inFromClient = inFromClient;
    }

    public PrintStream getOutToClient() {
        return outToClient;
    }

    public void setOutToClient(PrintStream outToClient) {
        this.outToClient = outToClient;
    }

    public String getClientSentence() {
        return clientSentence;
    }

    public void setClientSentence(String clientSentence) {
        this.clientSentence = clientSentence;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
