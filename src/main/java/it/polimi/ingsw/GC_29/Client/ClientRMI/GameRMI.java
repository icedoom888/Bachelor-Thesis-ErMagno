package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.Query;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Christian on 18/06/2017.
 */
public class GameRMI implements Runnable {

    private final PlayerColor playerColor;
    private final RMIViewRemote serverViewStub;

    public GameRMI(PlayerColor playerColor, RMIViewRemote serverViewStub){

        this.playerColor = playerColor;

        this.serverViewStub = serverViewStub;
    }

    @Override
    public void run() {
    //get the stub (local object) of the remote view

        System.out.println(playerColor);
        System.out.println(serverViewStub);

        ClientRMIView rmiView = null;
        try {
            rmiView = new ClientRMIView(playerColor, serverViewStub);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // register the client view in the server side (to receive messages from the server)
        try {
            serverViewStub.registerClient(rmiView);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            serverViewStub.initialize(playerColor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


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
            }
            // TODO: gestione client disconnesso!
        }
    }
}
