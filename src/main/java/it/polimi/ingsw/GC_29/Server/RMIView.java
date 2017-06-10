package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientViewRemote;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Christian on 07/06/2017.
 */
public class RMIView extends View implements RMIViewRemote {


    private ClientViewRemote clientView;

    private GetValidActions validActionQuery;

    private GameStatus gameStatus = GameStatus.getInstance();


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
    public void skipAction() throws RemoteException {
        notifyObserver(new SkipAction());
    }

    @Override
    public void usePawnChosen(FamilyPawnType familyPawnType) throws RemoteException {
        notifyObserver(new UsePawnChosen(familyPawnType));

    }

    @Override
    public void endTurn() {
        // TODO: notifyObserver(new EndTurn())
    }

    @Override
    public ArrayList<Action> getValidActionList() throws RemoteException {

        return validActionQuery.perform(gameStatus);
    }

    @Override
    public void doAction(int index) throws RemoteException {

        notifyObserver(new ExecuteAction(index));

    }

    @Override
    public Map<FamilyPawnType, Boolean> getFamilyPawnAvailability() {
        return gameStatus.getCurrentPlayer().getFamilyPawnAvailability();
    }


    @Override
    public void update() {

    }
}
