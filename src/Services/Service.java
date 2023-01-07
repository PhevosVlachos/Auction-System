package Services;

import ServerApplication.Auction;
import ServerApplication.Bid;

import java.util.List;

public interface Service {

    Auction createAuction();



    Bid placeBid() ;


    void auctionRegister();


    List<Auction> listAuctions();


    Bid checkHighest();


    void connectToServer();


    void runServer(int portNumber);

    





}
