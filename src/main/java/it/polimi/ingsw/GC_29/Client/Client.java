package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMI;
import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Client.ClientSocket.ClientSocketCLI;
import it.polimi.ingsw.GC_29.Client.GUI.FXMLMain;
import javafx.application.Application;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Christian on 11/06/2017.
 *
 * Main class of the client. When it runs it asks the client which interface he wants to use
 * and then which kind of connection if he decided CLI, otherwise it launches FXMLMain.
 */
public class Client {

    private Distribution distribution;

    private EnumInterface enumInterface;

    private boolean connectionChosen;

    private boolean interfaceChosen;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    private ClientRMI gameRMI;

    public Client() {

    }

    private void start(){

        askWhichInterface();

        switch (enumInterface){
            case CLI:
                clientCLI();

                break;
            case GUI:
                Application.launch(FXMLMain.class);
                break;
        }
    }


    private void clientCLI() {
        askWhichConnectionCLI();
        try {
            executeCLI();
        }
        catch (Exception e){
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

    }


    private void askWhichInterface() {
        Scanner stdIn = new Scanner(System.in);

        while (!interfaceChosen) {

            System.out.println("which interface do you want to use?");
            System.out.println("gui --> gui interface");
            System.out.println("cli --> cli interface");

            String input = stdIn.nextLine();

            switch (input){

                case "gui":
                    enumInterface = EnumInterface.GUI;
                    interfaceChosen = true;
                    break;

                case  "cli":
                    enumInterface = EnumInterface.CLI;
                    interfaceChosen = true;
                    break;

                default:
                    System.out.println("wrong input!");
                    System.out.println("");
                    break;

            }
        }
    }

    /**
     * Until the client has not given a valid input,
     * this method continues to ask the client which connection
     * he wants to use
     */
    private void askWhichConnectionCLI() {

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

    /**
     * After the player's chosen which connection he will be using,
     * executeCLI switch his decision and creates a new ClientRMI or
     * calls executeSocket.
     */
    private void executeCLI() {

        switch (distribution) {

            case RMI:
                try {
                    gameRMI = new ClientRMI(EnumInterface.CLI);
                } catch (RemoteException e) {
                    LOGGER.log(Level.INFO, e.getMessage(), e);
                }
                gameRMI.executeRMI();
                break;

            case SOCKET:
                executeSocket();
                break;

            default:
                throw new IllegalArgumentException("Illegal distribution type: " + distribution);

        }

    }

    private void executeSocket() {

        try {

            ClientSocketCLI clientSocketCLI = new ClientSocketCLI();
            clientSocketCLI.startClientCLI();

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
    }



    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}


