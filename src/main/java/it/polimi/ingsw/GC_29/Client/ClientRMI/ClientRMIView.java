package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Controllers.Change.*;
import it.polimi.ingsw.GC_29.Model.Era;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.*;

import static java.lang.System.exit;

/**
 * Created by Christian on 07/06/2017.
 *
 * ClientRMIView is the intermediary between the client and the server view.
 * It saves the stub of the serverRMIView as an attribute and handles the updates
 * of the client.
 */
public class ClientRMIView implements ClientViewRemote, Serializable {


    private transient InputChecker inputChecker;

    private transient RMIViewRemote serverViewStub;

    private transient static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());


    public ClientRMIView(){

    }


    public void connectWithServerView(RMIViewRemote serverViewStub, InputChecker inputChecker){

        this.serverViewStub = serverViewStub;

        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

        this.inputChecker = inputChecker;

    }


    /**
     * It is always called by the serverViewStub and has the goal to
     * understand which kind of change happened and then calls handlePlayerState if
     * it has been a playerStateChange, handle GameState if it has been a gameStateChange, ...
     * @param c
     * @throws RemoteException
     */
    @Override
    public void updateClient(Change c) throws RemoteException {
        // Just prints what was received from the server

        System.out.println((c));

        if(c instanceof PlayerStateChange){

            PlayerState currentPlayerState = ((PlayerStateChange)c).getNewPlayerState();

            handlePlayerState(currentPlayerState);

            System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){

            handleGameState((GameChange) c);

        }

        if(c instanceof ReconnectionChange){

            handleReconnection((ReconnectionChange)c);
        }

        if(c instanceof PlayerDisconnectedChange){

            handlePlayerDisconnected((PlayerDisconnectedChange)c);
        }

        if (c instanceof Excommunicated) {

            Era era = ((Excommunicated)c).getExcommunication();

            System.out.println("YOU HAVE BEEN EXCOMMUNICATED DURING THE " + era + "ERA");
        }
    }

    private void handlePlayerDisconnected(PlayerDisconnectedChange c) {

        List<String> usernames = c.getUsername();

        System.out.println("THE FOLLOWING PLAYERS ARE DISCONNECTED: ");

        for (String username : usernames) {

            System.out.println(username.toUpperCase());

        }

    }

    private void handleReconnection(ReconnectionChange c) {

        System.out.println("THE FOLLOWING PLAYERS ARE BACK IN THE GAME: ");

        for (String s : c.getReconnectedPlayerUsernames()) {

            System.out.println(s.toUpperCase());
        }

    }

    private void handleGameState(GameChange currentGameChange) {

        GameState currentGameState = currentGameChange.getNewGameState();

        inputChecker.setcurrentGameState(currentGameState);

        if(currentGameState == GameState.ENDED){

            inputChecker.setCurrentPlayerState(PlayerState.ENDGAME);

            String winner = ((EndGame)currentGameChange).getWinner();

            System.out.println("THE GAME IS ENDED. THE WINNER IS " + winner + "!");

            System.out.println("IN A FEW SECONDS THE GAME WILL BE TERMINATED");

            try {
                serverViewStub.endGame();
            } catch (RemoteException e) {
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    System.out.println("I AM THE CLIENT VIEW AND I AM CLOSING THE GAME");
                    exit(0);
                }
            }, (long) 10000);

        }

        if(currentGameChange instanceof EndMove){

            String username = ((EndMove)currentGameChange).getUsername();
            //System.out.println("MOVE ENDED PLAYER " + username);

        }

    }

    /**
     * When a player's state change, this method gets from the server the right objects it needs and
     * set them in the input checker.
     * @param currentPlayerState
     * @throws RemoteException
     */
    private void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        inputChecker.setCurrentPlayerState(currentPlayerState);

        switch (currentPlayerState){

            case DOACTION:
                inputChecker.setFamilyPawnAvailability(serverViewStub.getFamilyPawnAvailability());
                break;


            case CHOOSEACTION:
            case BONUSACTION:
                inputChecker.setValidActionList(serverViewStub.getValidActionList());
                inputChecker.printValidActionList();
                break;

            case CHOOSEWORKERS:
                inputChecker.setPossibleCardsWorkActionMap(serverViewStub.getCardsForWorkers());
                inputChecker.printPossibleCardsWorkAction();
                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:
                inputChecker.setPayToObtainCardsMap(serverViewStub.getPayToObtainCards());
                inputChecker.askActivateCard();
                break;

            case CHOOSECOST:
                inputChecker.setPossibleCosts(serverViewStub.getPossibleCosts());
                inputChecker.askWhichCost();
                break;

            case CHOOSE_COUNCIL_PRIVILEGE:
                inputChecker.setCouncilPrivilegeEffectList(serverViewStub.getCouncilPrivileges());
                inputChecker.nextPrivilegeEffect();
                inputChecker.askWhichPrivilege();
                break;

            case CHOOSE_BONUS_TILE:
                inputChecker.setBonusTileMap(serverViewStub.getBonusTileList());
                inputChecker.askWhichBonusTile();
                break;

            case PRAY:
                System.out.println("you have to decide whether to swear fidelity to the pope or not \n the valid input is : pray / do not pray");
                break;

            default:
                break;
        }
    }

    public InputChecker getInputChecker() {
        return inputChecker;
    }

    public void setInputChecker(InputChecker inputChecker) {
        this.inputChecker = inputChecker;
    }
}
