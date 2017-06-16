package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Christian on 07/06/2017.
 */
public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote, Serializable {


    private Map<Integer, String> validActionList;

    private PlayerColor playerColor;

    PlayerState currentPlayerState;

    GameState currentGameState;

    private transient InputChecker inputChecker;

    private transient RMIViewRemote serverViewStub;

    private List<String> playerDevCard;

    private Map<CardColor, List<String>> towerCardsMap;


    protected ClientRMIView(PlayerColor playerColor, RMIViewRemote serverViewStub) throws RemoteException {
        super();

        this.serverViewStub = serverViewStub;

        inputChecker = new InputChecker();

        this.playerColor = playerColor;

        this.playerDevCard = new ArrayList<>();

        this.towerCardsMap = new EnumMap<>(CardColor.class);

    }

    public Map<Integer, String> getValidActionList() {
        return validActionList;
    }

    public void setValidActionList(Map<Integer, String> validActionList) {
        this.validActionList = validActionList;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public InputChecker getInputChecker() {
        return inputChecker;
    }


    @Override
    public void updateClient(Change c) throws RemoteException {
        // Just prints what was received from the server
        System.out.println(c);
        System.out.println(playerColor);

        if(c instanceof PlayerStateChange){

            currentPlayerState = ((PlayerStateChange)c).getNewPlayerState();

            handlePlayerState(currentPlayerState);

            System.out.println("if you want to see your valid input for this current state insert : help");
        }

        if(c instanceof GameChange){
            currentGameState = ((GameChange)c).getNewGameState();
            //TODO: if relation with the church chiedo se questo player è stato scomunicato passando dallo stub e poi printo quello che devo
        }
    }

    private void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        inputChecker.setCurrentPlayerState(currentPlayerState);

        switch (currentPlayerState){

            case DOACTION:
                inputChecker.setFamilyPawnAvailability(serverViewStub.getFamilyPawnAvailability());
                break;

            case CHOOSEACTION:
                validActionList = serverViewStub.getValidActionList();
                inputChecker.setValidActionList(validActionList);
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

        List<String> towerCards = serverViewStub.getTowertCards(inputChecker.getTowerCardColor());

        towerCardsMap.put(inputChecker.getTowerCardColor(), towerCards);

        for (String towerCard : towerCards) {
            System.out.println(towerCard);
        }
    }

}
