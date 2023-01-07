package ServerApplication;

import java.sql.Time;
import java.time.LocalTime;

public class Bid {

    double price;
    Time serverTime;

    public Bid(double price, Time serverTime) {
        this.price = price;
        this.serverTime = serverTime;
    }
}
