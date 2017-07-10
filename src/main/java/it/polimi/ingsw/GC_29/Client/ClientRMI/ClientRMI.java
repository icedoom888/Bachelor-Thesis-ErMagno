package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.ConnectionInterface;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Christian on 07/06/2017.
 *
 * ClientRMI handles the login and the RMI connection with the server.
 */
public class ClientRMI extends UnicastRemoteObject implements ClientRemoteInterface{


    private final static String HOST = "127.0.0.1";

    private final static int PORT = 52365;

    private static final String NAME = "connection";

    private transient ConnectionInterface connectionStub;

    private transient ExecutorService executor = Executors.newCachedThreadPool();

    private transient GameRMI gameRMI;

    private PlayerColor playerColor;

    private String userName;

    private transient RMIViewRemote serverViewStub;

    private final Distribution distribution = Distribution.RMI;

    private EnumInterface gameInterface;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public ClientRMI(EnumInterface gameInterface) throws RemoteException{

        super();

        this.gameInterface = gameInterface;

    }


    ////////////////////////////////////////////////////////


    public void executeRMI() {

        try {
            connectServerRMI();
            loginRMI();

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

    }


    /**
     * connectServerRMI finds the registry and saves the stub in connectionStub.
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void connectServerRMI() throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry(HOST, PORT);
        connectionStub = (ConnectionInterface)reg.lookup(NAME);

    }

    /**
     * loginGUI is called after the client has inserted username, password and kind of connection in GUI.
     * It calls the login method of the stub that returns a boolean value: true if the
     * login has been successful, false otherwise.
     * @param userName
     * @param password
     * @return
     * @throws RemoteException
     */
    public Boolean loginGUI(String userName, String password) throws RemoteException {

        Boolean logged;

        this.userName = userName;

        logged = connectionStub.login(userName, password);

        if(logged){

            gameRMI = new GameRMI();

            connectionStub.addClient(this);

        }

        return logged;

    }


    /**
     * loginRMI is called when a client, playing in CLI, tries to login.
     * It calls the stub to verify if the login has been successful or not.
     * Finally it calls a new GameRMI() and it adds the client to the connectionStub.
     * @throws RemoteException
     */
    private void loginRMI() throws RemoteException {

        Scanner stdIn = new Scanner(System.in);

        Boolean logged = false;

        userName = "";

        String password;

        while (!logged){

            System.out.println("Insert your username");
            userName = stdIn.nextLine();

            System.out.println("Insert your password");
            password = stdIn.nextLine();

            logged = connectionStub.login(userName, password);

            if (!logged) {

                System.out.println(" login failed!");

            }

        }

        gameRMI = new GameRMI();

        System.out.println(" login successful");

        connectionStub.addClient(this);


    }

    @Override
    public void runNewGame(RMIViewRemote serverViewStub) throws  RemoteException{

        System.out.println("GAME BEGUN TRUE");
        this.serverViewStub = serverViewStub;

        gameRMI.connectWithServerView(gameInterface, playerColor, serverViewStub);

    }

    @Override
    public void initialize() throws RemoteException{

        gameRMI.initialize();

        if(gameInterface == EnumInterface.CLI){

            executor.submit(gameRMI);
        }
    }


    @Override
    public void setPlayerColor(PlayerColor playerColor) throws RemoteException {

        this.playerColor = playerColor;

    }


    @Override
    public PlayerColor getPlayerColor() throws RemoteException {

        return this.playerColor;
    }

    @Override
    public String getUserName() throws RemoteException {

        return this.userName;
    }


    public RMIViewRemote getServerViewStub() {
        return serverViewStub;
    }

    @Override
    public Distribution getDistribution() throws RemoteException {
        return distribution;
    }

    @Override
    public void joinGame() throws RemoteException {

        gameRMI.joinGame();

        if(gameInterface == EnumInterface.CLI){

            executor.submit(gameRMI);
        }
    }

    public GameRMI getGameRMI() {
        return gameRMI;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
