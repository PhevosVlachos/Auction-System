package ServerApplication;

import ClientApplication.Client;

import java.net.InetAddress;
import java.util.*;

public class Auction {

    int auctionID;
    String name;

    String description;
    double startingPrice;
    double highestBid;
    String auctionType;

    InetAddress sellerIP;

    List<Bid> allBids;
    List<InetAddress> clientID = new ArrayList<>();


    public Auction(String name, String description, double startingPrice, String auctionType,  InetAddress sellerIP , List<Bid> bids) {
        this.name = name;
        this.startingPrice = startingPrice;
        this.auctionType = auctionType;
        this.allBids = bids;
        this.description = description;
        this.sellerIP = sellerIP;
    }

    public InetAddress getSellerIP() {
        return sellerIP;
    }

    public void setSellerIP(InetAddress sellerIP) {
        this.sellerIP = sellerIP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Auction{" +
                "auctionID=" + auctionID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startingPrice=" + startingPrice +
                ", highestBid=" + highestBid +
                ", auctionType='" + auctionType + '\'' +
                ", sellerIP=" + sellerIP +
                '}';
    }
}
