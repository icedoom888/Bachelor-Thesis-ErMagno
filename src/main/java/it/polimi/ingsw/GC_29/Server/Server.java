package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Server.RMI.ConnectionInterfaceImpl;
import it.polimi.ingsw.GC_29.Server.Socket.LoginSocket;
import it.polimi.ingsw.GC_29.Server.Socket.PlayerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Christian on 11/06/2017.
 */
public class Server {

    //Main class from the server side
    private final static int PORT = 29999;

    private final String NAME = "connection";

    private final static int RMI_PORT = 52365;

    private ExecutorService executor = Executors.newCachedThreadPool();

    private GameMatchHandler gameMatchHandler;

    private final int maxNumberOfPlayers = 2;

    private ConnectionInterfaceImpl connectionServer;

    private Boolean lobbyCreated = false;

    private Boolean minClientNumberReached = false;

    private long startTime;


    private final long elapsedTime = 300000;


    protected Server() throws AlreadyBoundException, IOException {


        gameMatchHandler = new GameMatchHandler();

    }

    private void execute() throws AlreadyBoundException, IOException {

        System.out.println("START RMI");
        startRMI();

        System.out.println("START SOCKET");
        startSocket();


    }

    private void startSocket() throws IOException {

        //creats the socket
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("SERVER SOCKET READY ON PORT: " + PORT);

        Boolean b = true;

        while (b) {
            //Waits for a new client to connect
            Socket socket = serverSocket.accept();

            PlayerSocket playerSocket = new PlayerSocket(socket);

            LoginSocket loginSocket = new LoginSocket(playerSocket, gameMatchHandler);

            String username = loginSocket.login();

            if (!(loginSocket.getEnumInterface() == EnumInterface.GUI && !loginSocket.isLogged())) {

                gameMatchHandler.addClient(username, playerSocket);

            }

            else socket.close();


        }
    }

    public static void main(String[] args) throws IOException, AlreadyBoundException {
        Server server = new Server();
        server.execute();
    }




    private void startRMI() throws RemoteException, AlreadyBoundException {

        //create the registry to publish remote objects
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);
        System.out.println("Constructing the RMI registry");

        connectionServer = new ConnectionInterfaceImpl(gameMatchHandler);

        System.out.println("Binding the server implementation to the registry");
        registry.bind(NAME, connectionServer);


    }


}

