package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Client.ClientViewRemote;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Christian on 07/06/2017.
 */
public interface RMIViewRemote extends Remote {

    public void registerClient(
            ClientViewRemote clientStub)
            throws RemoteException;

    public void skipAction() throws RemoteException;

    public void usePawnChosen(FamilyPawnType familyPawnType) throws RemoteException;


    /**
     * da CLI basterebbe avere metodo printValidActionList (come nell'esempio printModel, lo stub printa direttamente
     * @return
     * @throws RemoteException
     */
    public ArrayList<Action> getValidActionList() throws RemoteException;

    public void doAction(int index) throws RemoteException;

}
