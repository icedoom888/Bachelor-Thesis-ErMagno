package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Christian on 11/06/2017.
 */
public class Server extends UnicastRemoteObject implements ConnectionInterface {

    private List<ClientRemoteInterface> clientList;

    protected Server() throws RemoteException {
        super();
    }



    @Override
    public boolean login(String username, String Password) {
        return false;
    }

    @Override
    public void addClient(ClientRemoteInterface clientStub) {

        clientList.add(clientStub);
    }

    //Main class from the server side
    private final static int PORT = 29999;

    private final String NAME = "connection";

    private final static int RMI_PORT = 52365;


    private void startRMI() throws RemoteException, AlreadyBoundException {

        //create the registry to publish remote objects
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);
        System.out.println("Constructing the RMI registry");

        System.out.println("Binding the server implementation to the registry");
        registry.bind(NAME, this);


    }

    private void startSocket() throws IOException {

        // TODO: implementa socket
    }

    public static void main(String[] args) throws IOException, AlreadyBoundException {
        Server server = new Server();
        System.out.println("START RMI");
        server.startRMI();
        System.out.println("START SOCKET");
        server.startSocket();

        
    }

}

