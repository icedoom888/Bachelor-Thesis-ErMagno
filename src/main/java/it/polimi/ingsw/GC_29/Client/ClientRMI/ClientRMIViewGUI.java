package it.polimi.ingsw.GC_29.Client.ClientRMI;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Client.GuiChangeHandler;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.Controllers.Change.*;
import it.polimi.ingsw.GC_29.Server.RMI.RMIViewRemote;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.*;

import static java.lang.System.exit;

/**
 * Created by Christian on 01/07/2017.
 *
 * As the ClientRMIView, this class's duty is to be the intermediary between the client and the server view.
 * It saves the stub of the serverRMIView as an attribute and handles the updates
 * of the client. It also extends the GuiChangeHandler class, that is used to communicate with the GUI.
 */
public class ClientRMIViewGUI extends GuiChangeHandler implements ClientViewRemote, Serializable {

    private transient RMIViewRemote serverViewStub;

    private transient static final Logger LOGGER = Logger.getLogger(ClientRMIView.class.getName());


    public ClientRMIViewGUI(){

        //rende remota questa classe
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);

        }
    }

    public void connectWithServerView(RMIViewRemote serverViewStub, InputChecker inputChecker){

        this.serverViewStub = serverViewStub;

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

            inputChecker.setCurrentPlayerState(((PlayerStateChange)c).getNewPlayerState());

            handlePlayerState();

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

            for (GuiChangeListener listener : listeners) {
                listener.excommunicate();
            }

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

    private void handlePlayerDisconnected(PlayerDisconnectedChange c) {

        for (GuiChangeListener listener : listeners) {

            listener.showDisconnectedPlayer(c.getUsername());
        }
    }

    private void handleReconnection(ReconnectionChange c) {

        for (GuiChangeListener listener : listeners) {

            listener.showReconnectedPlayers(c.getReconnectedPlayerUsernames());
        }
    }


    /**
     * When the gameState is set to Ended, this method calls the endGame() to update the GUI and then
     * calls the endGame method of the stub and after ten seconds it exits.
     * @param currentGameChange
     * @throws RemoteException
     */
    private void handleGameState(GameChange currentGameChange) throws RemoteException {

        GameState currentGameState = currentGameChange.getNewGameState();

        inputChecker.setcurrentGameState(currentGameState);

        if(currentGameState == GameState.ENDED){

            inputChecker.setCurrentPlayerState(PlayerState.ENDGAME);

            String winner = ((EndGame)currentGameChange).getWinner();

            //Lancia schermata
            endGame(winner);

            serverViewStub.endGame();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    exit(0);
                }
            }, (long) 10000);

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

    /**
     * When a player's state change, this method fires the change to the gui, gets from the server the right objects it needs and
     * calls the super class methods to update the gui.
     * @throws RemoteException
     */
    private void handlePlayerState() throws RemoteException {


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
                getExcommunicationTileUrl(serverViewStub.getExcommunicationTileUrl());
                break;

            default:
                break;
        }
    }




}
