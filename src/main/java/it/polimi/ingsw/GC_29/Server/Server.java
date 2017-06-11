package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.Client;
import it.polimi.ingsw.GC_29.Client.ClientRemoteInterface;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Christian on 11/06/2017.
 */
public class Server extends UnicastRemoteObject implements ConnectionInterface {

    //Main class from the server side
    private final static int PORT = 29999;

    private final String NAME = "connection";

    private final static int RMI_PORT = 52365;

    private Boolean minClientNumberReached = false;

    private long elapsedTime = 300000;

    private long startTime;

    private List<ClientRemoteInterface> clientList;

    private final int maxNumberOfPlayers = 4;

    private ExecutorService executor = Executors.newCachedThreadPool();

    private ServerNewGame newGame;

    private Boolean lobbyCreated = false;


    protected Server() throws RemoteException {
        super();
    }



    @Override
    public boolean login(String username, String Password) {
        return false;
    }

    @Override
    public synchronized void addClient(ClientRemoteInterface clientStub) {


        if(!lobbyCreated){

            lobbyCreated = true;

            newGame = new ServerNewGame(clientStub);
        }

        else if(newGame.clientList.size() < newGame.maxNumberOfPlayers){

            newGame.addClient(clientStub);
        }

        if(clientList.size() < maxNumberOfPlayers){

            clientList.add(clientStub);

        }
    }


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

        while (true){ // TODO: rifare rispetto al runnable

            if(!server.minClientNumberReached && server.clientList.size() >= 2){

                server.minClientNumberReached = true;

                server.startTime = System.currentTimeMillis();

            }

            if(server.evaluateConditionNewGame()){

               server.executor.submit(server.newGame);

            }

            break;
        }


    }


    private boolean evaluateConditionNewGame() {

        return (System.currentTimeMillis() - startTime >= elapsedTime || clientList.size() == maxNumberOfPlayers);
    }



    private static class ServerNewGame implements Runnable {

        private ArrayList<ClientRemoteInterface> clientList;

        private Boolean minClientNumberReached = false;

        private long elapsedTime = 300000;

        private long startTime;

        private final int maxNumberOfPlayers = 4;


        public ServerNewGame(ClientRemoteInterface client) {

            clientList = new ArrayList<>();

            clientList.add(client);
        }

        @Override
        public void run() {

        }

        public void addClient(ClientRemoteInterface clientStub) {

            clientList.add(clientStub);
        }
    }
}

