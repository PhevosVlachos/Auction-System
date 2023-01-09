package ServerApplication;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Server {
    int port;
    int maxLength = 255;
    byte[] buffer = new byte[maxLength];

    DatagramSocket socket = null;

    //     Input output streams
    DatagramPacket inFromClient;
    DatagramPacket outToClient;

    //    Ingoing outgoing messages
    String clientSentence;
    byte[] response;



    public Server() {
    }

    public Server(int port, int maxLength, byte[] buffer, DatagramSocket socket, DatagramPacket inFromClient, DatagramPacket outToClient, String clientSentence, byte[] response) {
        this.port = port;
        this.maxLength = maxLength;
        this.buffer = buffer;
        this.socket = socket;
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
        this.clientSentence = clientSentence;
        this.response = response;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket welcomeSocket) {
        this.socket = welcomeSocket;
    }

    public DatagramPacket getInFromClient() {
        return inFromClient;
    }

    public void setInFromClient(DatagramPacket inFromClient) {
        this.inFromClient = inFromClient;
    }

    public DatagramPacket getOutToClient() {
        return outToClient;
    }

    public void setOutToClient(DatagramPacket outToClient) {
        this.outToClient = outToClient;
    }

    public String getClientSentence() {
        return clientSentence;
    }

    public void setClientSentence(String clientSentence) {
        this.clientSentence = clientSentence;
    }

    public byte[] getResponse() {
        return response;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }
}
