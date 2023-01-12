package ServerApplication;

import java.io.BufferedReader;
import java.io.PrintStream;

public class Handler {

    // Holds messages we get from client
    String clientSentence = "";
    // Holds messages we send to client
    String response;
    // Input object
    BufferedReader inFromClient;
    // Output object
    PrintStream outToClient;
    // Client's name
    String peerName;


    public Handler() {
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

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName;
    }
}
