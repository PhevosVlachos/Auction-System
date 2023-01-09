package ClientApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class Client {

    int port;
    int maxLength = 255;


    String serverMachine;
    String messageToServer;
    String serverResponse;

   InetAddress serverAdress;

   DatagramSocket socket;
   DatagramPacket inFromServer;
   DatagramPacket outToServer;


    public Client() throws SocketException {}

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
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



    public DatagramPacket getInFromServer() {
        return inFromServer;
    }

    public void setInFromServer(DatagramPacket inFromServer) {
        this.inFromServer = inFromServer;
    }

    public DatagramPacket getOutToServer() {
        return outToServer;
    }

    public void setOutToServer(DatagramPacket outToServer) {
        this.outToServer = outToServer;
    }

    public InetAddress getServerAdress() {
        return serverAdress;
    }

    public void setServerAdress(InetAddress serverAdress) {
        this.serverAdress = serverAdress;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }


    @Override
    public String toString() {
        return "Client{" +
                "port=" + port +
                ", serverMachine='" + serverMachine + '\'' +
                ", messageToServer='" + messageToServer + '\'' +
                ", serverResponse='" + serverResponse + '\'' +
                ", inFromServer=" + inFromServer +
                ", outToServer=" + outToServer +
                '}';
    }
}
