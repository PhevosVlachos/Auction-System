package Services;

import ClientApplication.Client;
import ClientApplication.ClientHandler;
import ServerApplication.Auction;
import ServerApplication.Bid;
import ServerApplication.Handler;

import java.io.IOException;
import java.util.List;

public interface Service {




    Bid placeBid() ;




    List<Auction> listAuctions();


    Double checkHighest(Auction auction);


//    void connectToServer(Client myClient, String serverMachine, int port) throws Exception;






    void receiveFromClient(Handler myHandler) throws IOException;

    void sendToClient(Handler myHandler)  ;
    
    void sendToServer(ClientHandler myClientHandler) throws IOException;
    
    void receiveFromServer(ClientHandler myClientHandler) throws IOException;




}
