package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.ConnectionInterface;
import org.w3c.dom.ranges.RangeException;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Created by Christian on 11/06/2017.
 */
public class Client {


    private final static String HOST = "127.0.0.1";

    private final static int PORT = 52365;

    private static final String NAME = "connection";

    private Distribution distribution;
    private boolean connectionChosen;

    private ClientRemoteInterfaceImpl clientRemote;

    private ClientRMI gameRMI;

    private ConnectionInterface connectionStub;

    public Client() throws RemoteException {
        super();

        askWichConnection();

        try {
            execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: chiusura canale rmi e canale socket
        }
    }

    private void askWichConnection() {

        Scanner stdIn = new Scanner(System.in);

        while(!connectionChosen){

            System.out.println("which connection do you want to use?");
            System.out.println("rmi --> rmi connection");
            System.out.println("socket --> socket connection");

            String input = stdIn.nextLine();

            switch (input){

                case "rmi":
                    distribution = Distribution.RMI;
                    connectionChosen = true;
                    break;

                case  "socket":
                    distribution = Distribution.SOCKET;
                    connectionChosen = true;
                    break;

                default:
                    System.out.println("wrong input!");
                    System.out.println("");
                    break;

            }
        }
    }

    private void execute() throws Exception {

        switch (distribution) {

            case RMI:
                executeRMI();
                break;

            case SOCKET:
                executeSocket();
                break;

            default:
                throw new Exception("Illegal distribution type: " + distribution);

        }

    }

    private void executeSocket() {

        //TODO: implementazione su falsa riga di rmi
    }

    private void executeRMI() {

        try {
            connectServerRMI();
            loginRMI();
            playNewGameRMI();
            close();
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

        /* method calls
        Scanner in = new Scanner(System.in);
        System.out.println("Provide username please:");
        UserImpl u = new UserImpl(in.nextLine());

        srv.join(u);*/

    }


    private void loginRMI() throws RemoteException {

        Scanner stdIn = new Scanner(System.in);

        Boolean logged = false;

        String userName = "";

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

        clientRemote = new ClientRemoteInterfaceImpl();

        clientRemote.setUsername(userName);

        connectionStub.addClient(clientRemote);

    }


    private void playNewGameRMI() throws RemoteException, NotBoundException, AlreadyBoundException {

        long i = 0;

        long var = 213999999;

        while (!clientRemote.getGameBegun()){

            if(i % var == 0){

                System.out.println("sono dentro PLAY" + i);
            }

            i++;
            if(i == 1283999994){
                i=0;
            }
        } // aspetta che il server lanci una nuova partita

        gameRMI = new ClientRMI();

        gameRMI.run();

    }

    private void connectServerSocket() {


    }

    private void close() {

    }




    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
    }
}


