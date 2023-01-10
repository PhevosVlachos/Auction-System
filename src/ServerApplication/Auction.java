package ServerApplication;

import ClientApplication.Client;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Auction {

    String auctionID;
    String name;
    double startingPrice;
    double highestBid;
    String auctionType;

    List<Double> allBids;
    List<Client> allClients; 


    public Auction(String auctionID, String name, double startingPrice, String auctionType, List<Double> bids) {
        this.auctionID = auctionID;
        this.name = name;
        this.startingPrice = startingPrice;
        //this.highestBid = highestBid;
        this.auctionType = auctionType;
        this.allBids = bids;
    }

    public void setAllBids(List<Double> allBids) {
        this.allBids = allBids;
    }

    public List<Client> getAllClients() {
        return allClients;
    }

    public void setAllClients(List<Client> allClients) {
        this.allClients = allClients;
    }

    public List<Double> getAllBids() {
        return allBids;
    }

    public String getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(String auctionID) {
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
