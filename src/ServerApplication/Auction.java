package ServerApplication;

public class Auction {

    String auctionID;
    String name;
    double startingPrice;
    double highestBid;
    String auctionType;

    public Auction(String auctionID, String name, double startingPrice, double highestBid, String auctionType) {
        this.auctionID = auctionID;
        this.name = name;
        this.startingPrice = startingPrice;
        this.highestBid = highestBid;
        this.auctionType = auctionType;
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
