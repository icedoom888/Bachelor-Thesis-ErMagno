package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.ConnectionInterface;
import it.polimi.ingsw.GC_29.Server.Query.Query;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Christian on 07/06/2017.
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

    public ClientRMI() throws RemoteException{
        super();
    }


    ////////////////////////////////////////////////////////


    public void executeRMI() {

        try {
            connectServerRMI();
            loginRMI();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            // Always close it:
            //TODO: chiudi connessione
        }

    }


    private void connectServerRMI() throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry(HOST, PORT);
        connectionStub = (ConnectionInterface)reg.lookup(NAME);

    }


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

        System.out.println(" login successful");

        connectionStub.addClient(this);

    }

    @Override
    public void initializeNewGame(RMIViewRemote serverViewStub) {

        System.out.println("GAME BEGUN TRUE");
        this.serverViewStub = serverViewStub;
        gameRMI = new GameRMI(playerColor, serverViewStub);
        executor.submit(gameRMI);
        System.out.println("THREAD LANCIATO");
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

}
