package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientViewRemote;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Christian on 07/06/2017.
 */
public interface RMIViewRemote extends Remote {

    void registerClient(
            ClientViewRemote clientStub)
            throws RemoteException;

    void skipAction() throws Exception;

    void usePawnChosen(FamilyPawnType familyPawnType) throws Exception;


    /**
     * da CLI basterebbe avere metodo printValidActionList (come nell'esempio printModel, lo stub printa direttamente
     * @return
     * @throws RemoteException
     */
    ArrayList<Action> getValidActionList() throws RemoteException;

    void doAction(int index) throws Exception;

    void endTurn() throws Exception;

    void pray(boolean b, PlayerColor playerColor) throws Exception;
}
