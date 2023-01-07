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


    void auctionRegister();


    List<Auction> listAuctions();


    Bid checkHighest();


    void connectToServer(Client myClient, String serverMachine, int port) throws Exception;


    void runServer(Server myServer, int port) throws Exception;

    void acceptConnections(Server myServer) throws Exception;





}
