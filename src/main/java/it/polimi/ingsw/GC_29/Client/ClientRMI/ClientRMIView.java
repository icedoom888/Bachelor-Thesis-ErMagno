package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;
import sun.nio.cs.US_ASCII;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Christian on 07/06/2017.
 */
public class ClientRMIView implements ClientViewRemote, Serializable {


    private transient InputChecker inputChecker;

    private transient RMIViewRemote serverViewStub;

    private List<String> playerDevCard;

    private Map<CardColor, List<String>> towerCardsMap;


    public ClientRMIView(){

        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public void connectWithServerView(RMIViewRemote serverViewStub, InputChecker inputChecker){

        this.serverViewStub = serverViewStub;

        this.inputChecker = inputChecker;

    }


    @Override
    public void updateClient(Change c) throws RemoteException {
        // Just prints what was received from the server
        System.out.println(c);

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

            System.out.println("THE GAME IS ENDED. THE WINNER IS " + winner + "!\n");

            System.out.println("IN A FEW SECONDS THE GAME WILL BE TERMINATED");

            try {
                serverViewStub.endGame();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    System.out.println("I AM THE CLIENT VIEW AND I AM CLOSING THE GAME");
                    System.exit(0);
                }
            }, (long) 10000);

        }

        if(currentGameChange instanceof NextTurn){

            String username = ((NextTurn)currentGameChange).getUsername();

            System.out.println("NOW IS THE TURN OF THE PLAYER: " + username);

        }

    }

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

            //TODO: inserire gestione altri stati se necessario
        }
    }


    public void getPlayerDevCard() throws RemoteException {

        playerDevCard = serverViewStub.getDevelopmentCard(inputChecker.getPlayerCardColor());

        for (String s : playerDevCard) {
            System.out.println(s);
        }
    }

    public void getTowerCard() throws RemoteException {

        List<String> towerCards = serverViewStub.getTowerCards(inputChecker.getTowerCardColor());

        towerCardsMap.put(inputChecker.getTowerCardColor(), towerCards);

        for (String towerCard : towerCards) {
            System.out.println(towerCard);
        }
    }

    public InputChecker getInputChecker() {
        return inputChecker;
    }

    public void setInputChecker(InputChecker inputChecker) {
        this.inputChecker = inputChecker;
    }
}
