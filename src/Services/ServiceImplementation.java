package Services;

import ServerApplication.Auction;
import ServerApplication.Bid;

import java.util.List;

public class ServiceImplementation implements Service {


    @Override
    public Auction createAuction() {
        return null;
    }

    @Override
    public Bid placeBid() {
        return null;
    }

    @Override
    public void auctionRegister() {

    }

    @Override
    public List<Auction> listAuctions() {
        return null;
    }

    @Override
    public Bid checkHighest() {
        return null;
    }

    @Override
    public void connectToServer() {

    }

    @Override
    public void runServer(int portNumber) {
        ServerSocket welcomeSocket = new ServerSocket( portNumber );

    }
}
