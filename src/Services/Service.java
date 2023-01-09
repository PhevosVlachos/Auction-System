package Services;

import ClientApplication.Client;
import ServerApplication.Auction;
import ServerApplication.Bid;
import ServerApplication.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public interface Service {

    Auction createAuction();



    Bid placeBid() ;




    List<Auction> listAuctions();


    Double checkHighest(Auction auction);


    void connectToServer(Client myClient, String serverMachine, int port) throws Exception;


    void runServer(Server myServer, int port) throws Exception;

    void acceptConnections(Server myServer) throws Exception;

    void receiveFromClient(Server myServer) throws Exception;

    void sendToClient(Server myServer)  throws Exception;
    
    void sendToServer(Client myClient) throws Exception;
    
    void receiveFromServer(Client myClient) throws Exception;




}
