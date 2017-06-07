package it.polimi.ingsw.GC_29.Client;

import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Christian on 07/06/2017.
 */
public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote, Serializable {


    private FamilyPawnType familyPawnChosen;

    private int actionIndex;

    private ArrayList<Action> validActionList;

    private PlayerState currentPlayerState;

    private GameEvent currentGameEvent;


    protected ClientRMIView() throws RemoteException {
        super();
    }

    /**
     *
     */
    //private static final long serialVersionUID = 6111979881550001331L;



    @Override
    public void updateClient(Change c) throws RemoteException {
        // Just prints what was received from the server
        System.out.println(c);

        if(c instanceof PlayerStateChange){

            currentPlayerState = ((PlayerStateChange)c).getNewPlayerState();
        }

        if(c instanceof GameChange){
            currentGameEvent = ((GameChange)c).getNewGameEvent();
        }
    }

    public FamilyPawnType getFamilyPawnChosen() {
        return familyPawnChosen;
    }

    public void setFamilyPawnChosen(FamilyPawnType familyPawnChosen) {
        this.familyPawnChosen = familyPawnChosen;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(int actionIndex) {
        this.actionIndex = actionIndex;
    }

    public ArrayList<Action> getValidActionList() {
        return validActionList;
    }

    public void setValidActionList(ArrayList<Action> validActionList) {
        this.validActionList = validActionList;
    }

    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setCurrentPlayerState(PlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;
    }

    public GameEvent getGameEvent() {
        return currentGameEvent;
    }

    public void setGameEvent(GameEvent gameEvent) {
        this.currentGameEvent = gameEvent;
    }
}
