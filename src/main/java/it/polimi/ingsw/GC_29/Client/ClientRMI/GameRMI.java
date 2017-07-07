package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Controllers.Input.Input;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Query.Query;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Christian on 18/06/2017.
 */
public class GameRMI extends CommonOutRMI implements Runnable {


    ClientRMIView rmiView;

    ClientRMIViewGUI clientRMIViewGUI;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());



    public GameRMI(){
        super();

        rmiView = new ClientRMIView();

        clientRMIViewGUI = new ClientRMIViewGUI();
    }


    @Override
    public void run() {
    //get the stub (local object) of the remote view

        Scanner stdIn = new Scanner(System.in);

        Boolean b = true;

        while (b) {
            //Capture input from user
            String inputLine = stdIn.nextLine();
            System.out.println("SENDING "+inputLine);

            sendInput(inputLine);
        }
    }

    public void initialize() {

        try {
            serverViewStub.initialize(playerColor);
        } catch (RemoteException e) {
            LOGGER.info((Supplier<String>) e);
        }

    }

    public void connectWithServerView(EnumInterface gameInterface, PlayerColor playerColor, RMIViewRemote serverViewStub){
        
        super.connectWithServerView(playerColor, serverViewStub);

        switch (gameInterface){

            case CLI:

                rmiView.connectWithServerView(serverViewStub, getInputChecker());

                try {
                    serverViewStub.registerClient(rmiView);
                } catch (RemoteException e) {
                    LOGGER.info((Supplier<String>) e);
                }
                break;

            case GUI:
                clientRMIViewGUI.connectWithServerView(serverViewStub, getInputChecker());

                try {
                    serverViewStub.registerClient(clientRMIViewGUI);
                } catch (RemoteException e) {
                    LOGGER.info((Supplier<String>) e);
                }
                break;

        }

    }

    public ClientRMIViewGUI getClientRMIViewGUI() {
        return clientRMIViewGUI;
    }

    public void joinGame() throws RemoteException {

        serverViewStub.joinGame(playerColor);

    }
}
