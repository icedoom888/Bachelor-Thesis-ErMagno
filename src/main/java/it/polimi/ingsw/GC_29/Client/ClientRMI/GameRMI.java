package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.EnumInterface;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import it.polimi.ingsw.GC_29.Query.Query;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Christian on 18/06/2017.
 */
public class GameRMI extends CommonOutRMI implements Runnable {


    ClientRMIView rmiView;

    ClientRMIViewGUI clientRMIViewGUI;

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
            Input input;
            Query query;

            sendInput(inputLine);

            /*try {

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
                    //TODO: le prossime due istruizioni sono per provare, bisogna gestirle in altro modo
                    case "see my goodset":
                        System.out.println(serverViewStub.getPlayerGoodset());
                        break;
                    case "see my family pawns":
                        System.out.println(serverViewStub.getPlayerPawns());
                        break;
                    case "execute action":
                        serverViewStub.doAction(rmiView.getInputChecker().getActionIndex());
                        break;
                    case "pray":
                        serverViewStub.pray(true, rmiView.getInputChecker().getPlayerColor());
                        break;
                    case "do not pray":
                        serverViewStub.pray(false, rmiView.getInputChecker().getPlayerColor());
                        break;

                    case "see development card":
                        rmiView.getPlayerDevCard();
                        break;

                    case "see tower card":
                        rmiView.getTowerCard();
                        break;

                    case "use workers":
                        serverViewStub.activateCards(rmiView.getInputChecker().getWorkersChosen());
                        break;

                    case "activate card":

                        if(rmiView.getInputChecker().handleCardDecision()){

                            if(rmiView.getInputChecker().nextCard()){

                                rmiView.getInputChecker().askActivateCard();

                            }
                            else {
                                serverViewStub.payToObtainCardChosen(rmiView.getInputChecker().getActivatedCardMap());
                            }

                        }
                        break;

                    case "effect chosen":
                        rmiView.getInputChecker().addCard();
                        if(rmiView.getInputChecker().nextCard()){

                            rmiView.getInputChecker().askActivateCard();

                        }
                        else {
                            serverViewStub.payToObtainCardChosen(rmiView.getInputChecker().getActivatedCardMap());
                        }
                        break;

                    case "privilege":
                        if(rmiView.getInputChecker().nextParchment()){
                            rmiView.getInputChecker().askWhichPrivilege();
                        }
                        else if(rmiView.getInputChecker().nextPrivilegeEffect()){
                            rmiView.getInputChecker().askWhichPrivilege();
                        }
                        else {
                            serverViewStub.privilegesChosen(rmiView.getInputChecker().getCouncilPrivilegeEffectChosenList());
                        }
                        break;

                    case "cost chosen":
                        serverViewStub.chooseCost(rmiView.getInputChecker().getCostChosen());
                        break;

                    case "bonus tile chosen":
                        serverViewStub.bonusTileChosen(rmiView.getInputChecker().getBonusTileChosen());
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
            }*/
            // TODO: gestione client disconnesso!
        }
    }

    public void initialize() {

        try {
            serverViewStub.initialize(playerColor);
        } catch (RemoteException e) {
            e.printStackTrace();
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
                    e.printStackTrace();
                }
                break;

            case GUI:
                clientRMIViewGUI.connectWithServerView(serverViewStub, getInputChecker());

                try {
                    serverViewStub.registerClient(clientRMIViewGUI);
                } catch (RemoteException e) {
                    e.printStackTrace();
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
