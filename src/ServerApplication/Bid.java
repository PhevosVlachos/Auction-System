package ServerApplication;


import java.time.LocalTime;

public class Bid {

    double price;
    LocalTime serverTime;
    
    Auction auction;

    public Bid(double price, LocalTime serverTime, Auction auction) {
        this.price = price;
        this.serverTime = serverTime;
        this.auction = auction;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "price=" + price +
                ", serverTime=" + serverTime +
                ", auction=" + auction +
                '}';
    }
}
