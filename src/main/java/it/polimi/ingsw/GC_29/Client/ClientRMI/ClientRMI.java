package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.Client;
import it.polimi.ingsw.GC_29.Client.Distribution;
import it.polimi.ingsw.GC_29.Client.Instruction;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.ConnectionInterface;
import it.polimi.ingsw.GC_29.Server.Query.Query;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Christian on 07/06/2017.
 */
public class ClientRMI {

    //private final static int RMI_PORT = 52365;


    /*private final static String HOST = "127.0.0.1";

    private final static int PORT = 52365;

    private static final String NAME = "connection";*/

    private ConnectionInterface connectionStub;

    private ClientRemoteInterfaceImpl clientRemote;

    private PlayerColor playerColor;

    private RMIViewRemote serverViewStub;

    public ClientRMI(PlayerColor playerColor, RMIViewRemote serverViewStub){

        this.playerColor = playerColor;

        this.serverViewStub = serverViewStub;
    }



    public void run() throws RemoteException, NotBoundException, AlreadyBoundException {

        //get the stub (local object) of the remote view

        ClientRMIView rmiView=new ClientRMIView(playerColor, serverViewStub);

        // register the client view in the server side (to receive messages from the server)
        serverViewStub.registerClient(rmiView);

        serverViewStub.initialize(playerColor);


        Scanner stdIn = new Scanner(System.in);

        Boolean b = true;

        while (b) {
            //Capture input from user
            String inputLine = stdIn.nextLine();
            System.out.println("SENDING "+inputLine);
            Input input;
            Query query;

            try {


                //vedi il commento nel metodo inputParser
                inputLine = rmiView.getInputChecker().checkInput(inputLine);

                System.out.println(inputLine);

                // Call the appropriate method in the server
                switch (inputLine) {
                    case "throw dices":
                        serverViewStub.throwDices();
                        break;
                    case "skip action":
                        serverViewStub.skipAction();
                        break;
                    case "end turn":
                        serverViewStub.endTurn();
                        break;
                    case "use family pawn":
                        System.out.println("colore: " + rmiView.getInputChecker().getFamilyPawnChosen());
                        serverViewStub.usePawnChosen(rmiView.getInputChecker().getFamilyPawnChosen());
                        break;
                    case "see valid action list":
                        rmiView.getInputChecker().printValidActionList();
                        break;
                    case "execute action":
                        serverViewStub.doAction(rmiView.getInputChecker().getActionIndex());
                        break;
                    case "I want to pray":
                        serverViewStub.pray(true, rmiView.getInputChecker().getPlayerColor());
                        break;
                    case "I don't want to pray":
                        serverViewStub.pray(false, rmiView.getInputChecker().getPlayerColor());
                        break;

                    case "see development card":
                        rmiView.getPlayerDevCard();
                        break;

                    case "see tower card":
                        rmiView.getTowerCard();
                        break;

                    case "help":
                        rmiView.getInputChecker().handleHelp();
                        break;

                    default:
                        System.out.println(inputLine);
                        System.out.println("if you want to see your valid input for this current state insert : help");
                        break;
                }

            } catch (IOException e1) {

                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // TODO: gestione client disconnesso!
        }
    }


    ////////////////////////////////////////////////////////


    /*public void executeRMI() {

        try {
            connectServerRMI();
            loginRMI();
            playNewGameRMI();
            run();
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

        this.playerColor = clientRemote.getPlayerColor();

        this.serverViewStub = clientRemote.getServerViewStub();

    }*/

}
