package ServerApplication;

import Services.ServiceImplementation;


public class TCPServer {

    public static void main(String[] args) throws Exception {
        ServiceImplementation service = new ServiceImplementation();
        Server myServer = new Server();


        service.runServer(myServer, 4567);

        while(true){
            service.acceptConnections(myServer);
        }










    }
}
