package Services;

import ClientApplication.Client;
import ServerApplication.Auction;
import ServerApplication.Bid;
import ServerApplication.Handler;
import ServerApplication.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.List;

public interface Service {




    Bid placeBid() ;




    List<Auction> listAuctions();


    Double checkHighest(Auction auction);


    void connectToServer(Client myClient, String serverMachine, int port) throws Exception;


    void runServer(Server myServer, int port) throws Exception;

    void acceptConnections(Server myServer) throws Exception;

    void receiveFromClient(Handler myHandler) throws Exception;

    void sendToClient(Handler myHandler)  throws Exception;
    
    void sendToServer(Client myClient) throws Exception;
    
    void receiveFromServer(Client myClient) throws Exception;




}
