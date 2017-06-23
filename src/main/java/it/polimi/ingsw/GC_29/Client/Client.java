package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMI;
import it.polimi.ingsw.GC_29.Client.ClientSocket.ClientSocketCLI;
import it.polimi.ingsw.GC_29.Client.GUI.FXMLMain;
import javafx.application.Application;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Christian on 11/06/2017.
 */
public class Client{

    private Distribution distribution;

    private EnumInterface enumInterface;

    private boolean connectionChosen;

    private boolean interfaceChosen;

    private ClientRMI gameRMI;

    public Client() throws RemoteException {
        super();

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
            e.printStackTrace();
        }
        finally {
            // TODO: chiusura canale rmi e canale socket
        }
    }

    /*private void askWhichConnectionGUI() {



        LoginController loginController = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LoginGUI.fxml"));
        loader.setController(loginController);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        SplitPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setHeight(400);
        stage.setWidth(500);
        stage.centerOnScreen();
        stage.show();
    }*/

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

    private void executeCLI() throws Exception {

        switch (distribution) {

            case RMI:
                gameRMI = new ClientRMI();
                gameRMI.executeRMI();
                break;

            case SOCKET:
                executeSocket();
                break;

            default:
                throw new Exception("Illegal distribution type: " + distribution);

        }

    }

    private void executeSocket() {

        try {

            ClientSocketCLI clientSocketCLI = new ClientSocketCLI();
            clientSocketCLI.startClientCLI();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void close() {

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
    }
}


