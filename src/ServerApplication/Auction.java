package ServerApplication;

import ClientApplication.Client;

import java.net.InetAddress;
import java.util.*;

public class Auction {

    int auctionID;
    String name;
    double startingPrice;
    double highestBid;
    String auctionType;

    List<Bid> allBids;
    List<InetAddress> clientID = new ArrayList<>();


    public Auction(String name, double startingPrice, String auctionType, List<Bid> bids) {
        this.name = name;
        this.startingPrice = startingPrice;
        //this.highestBid = highestBid;
        this.auctionType = auctionType;
        this.allBids = bids;
    }

    public void setAllBids(List<Bid> allBids) {
        this.allBids = allBids;
    }

    public List<InetAddress> getClientID() {
        return clientID;
    }

    public void setClientID(List<InetAddress> clientID) {
        this.clientID = clientID;
    }

    public List<Bid> getAllBids() {
        return allBids;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "auctionID='" + auctionID + '\'' +
                ", name='" + name + '\'' +
                ", startingPrice=" + startingPrice +
                ", highestBid=" + highestBid +
                ", auctionType='" + auctionType + '\'' +
                '}';
    }
}
