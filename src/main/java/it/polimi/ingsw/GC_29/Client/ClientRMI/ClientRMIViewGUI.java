package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.GuiChangeHandler;
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
public class ClientRMIViewGUI extends GuiChangeHandler implements ClientViewRemote, Serializable {

    private transient RMIViewRemote serverViewStub;

    private List<String> playerDevCard;

    private Map<CardColor, List<String>> towerCardsMap;

    public ClientRMIViewGUI(){

        this.playerDevCard = new ArrayList<>();

        this.towerCardsMap = new EnumMap<>(CardColor.class);

        //rende remota questa classe
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

            //PlayerState currentPlayerState = ((PlayerStateChange)c).getNewPlayerState();

            inputChecker.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            handlePlayerState(inputChecker.getCurrentPlayerState());

        }

        if(c instanceof GameChange){

            inputChecker.setcurrentGameState(((GameChange)c).getNewGameState());
        }

        if (c instanceof GUIChange) {

            GUIChange guiChange = (GUIChange)c;
            if (guiChange instanceof LeadersAvailableGUI) {

                LeadersAvailableGUI leaderChange = (LeadersAvailableGUI)guiChange;

                handleLeaderCard(leaderChange);
            }
            else guiChange.perform(listeners);

        }
    }

    private void handleLeaderCard(LeadersAvailableGUI leaderChange) {

        Map<Integer, Boolean> leadersAvailable = leaderChange.getLeadersAvailable();

        if (!leadersAvailable.isEmpty()) {

            inputChecker.setLeaderCardMap(leadersAvailable);
            inputChecker.setCurrentPlayerState(PlayerState.LEADER);
            leaderChange.perform(listeners);
        }
    }

    private void handlePlayerState(PlayerState currentPlayerState) throws RemoteException {

        //inputChecker.setCurrentPlayerState(currentPlayerState);

        firePlayerState(inputChecker.getCurrentPlayerState());

        switch (inputChecker.getCurrentPlayerState()){

            case DOACTION:
                getFamilyPawnsAvailabilityGUI(serverViewStub.getFamilyPawns());
                break;


            case CHOOSEACTION:
            case BONUSACTION:
                validActionsGUI(serverViewStub.getValidActionList());
                break;

            case CHOOSEWORKERS:
                getCardsForWorkersGUI(serverViewStub.getCardsForWorkers());
                break;

            case ACTIVATE_PAY_TO_OBTAIN_CARDS:
                getPayToObtainCardsGUI(serverViewStub.getPayToObtainCardsGUI());
                break;

            case CHOOSECOST:
                getPossibleCostsGUI(serverViewStub.getPossibleCosts());
                break;

            case CHOOSE_COUNCIL_PRIVILEGE:
                getCouncilPrivilegesGUI(serverViewStub.getCouncilPrivileges());
                break;

            case CHOOSE_BONUS_TILE:
                getBonusTilesGUI(serverViewStub.getBonusTileList());
                break;

            case PRAY:
                getExcommunicationTileUrl("you have to decide whether to swear fidelity to the pope or not \n the valid input is : pray / do not pray");
                break;

            //TODO: inserire gestione altri stati se necessario
        }
    }


    /*public void getPlayerDevCard() throws RemoteException {

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
    }*/

}
