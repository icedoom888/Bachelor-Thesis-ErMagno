package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 01/07/2017.
 */
public class ClientRMIViewGUI implements ClientViewRemote, Serializable {

    private transient InputChecker inputChecker;

    private transient RMIViewRemote serverViewStub;

    private List<String> playerDevCard;

    private Map<CardColor, List<String>> towerCardsMap;


    protected ClientRMIViewGUI(PlayerColor playerColor, RMIViewRemote serverViewStub) throws RemoteException {

        this.serverViewStub = serverViewStub;

        inputChecker = new InputChecker();

        this.playerDevCard = new ArrayList<>();

        this.towerCardsMap = new EnumMap<>(CardColor.class);

        inputChecker.setPlayerColor(playerColor);

        //rende remota questa classe
        UnicastRemoteObject.exportObject(this, 0);

    }


    @Override
    public void updateClient(Change c) throws RemoteException {
        // Just prints what was received from the server
        System.out.println(c);

        if(c instanceof PlayerStateChange){

            PlayerState currentPlayerState = ((PlayerStateChange)c).getNewPlayerState();

            handlePlayerState(currentPlayerState);

        }

        if(c instanceof GameChange){

            GameState currentGameState = ((GameChange)c).getNewGameState();

            inputChecker.setcurrentGameState(currentGameState);
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

}
