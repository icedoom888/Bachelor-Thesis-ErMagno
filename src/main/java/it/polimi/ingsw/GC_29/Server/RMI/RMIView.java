package it.polimi.ingsw.GC_29.Server.RMI;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientViewRemote;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.GetValidActions;
import it.polimi.ingsw.GC_29.Server.View;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian on 07/06/2017.
 */
public class RMIView extends View implements RMIViewRemote {


    private ClientViewRemote clientView;

    private GetValidActions validActionQuery;

    private GameStatus gameStatus;


    public RMIView(GameStatus gameStatus){

        this.gameStatus = gameStatus;

        this.validActionQuery = new GetValidActions();
    }


    @Override
    public void registerClient(ClientViewRemote clientStub) throws RemoteException {
        System.out.println("CLIENT REGISTERED");
        clientView = clientStub;
    }

    @Override
    public void update(Change o) throws RemoteException {
        clientView.updateClient(o);
    }

    @Override
    public void skipAction() throws RemoteException{
        try {
            notifyObserver(new SkipAction());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usePawnChosen(FamilyPawnType familyPawnType) throws RemoteException{
        try {
            notifyObserver(new UsePawnChosen(familyPawnType));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endTurn() throws RemoteException{
        try {
            notifyObserver(new EndTurn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pray(boolean b, PlayerColor playerColor) throws RemoteException{
        try {
            notifyObserver(new Pray(b, playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(PlayerColor playerColor) throws RemoteException {
        try {
            notifyObserver(new Initialize(playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public HashMap<Integer, String> getValidActionList() throws RemoteException {

        return validActionQuery.perform(gameStatus);
    }

    @Override
    public void doAction(int index) throws RemoteException {

        try {
            notifyObserver(new ExecuteAction(index));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<FamilyPawnType, Boolean> getFamilyPawnAvailability() throws RemoteException{
        return gameStatus.getCurrentPlayer().getFamilyPawnAvailability();
    }


    @Override
    public void update() {

    }
}
