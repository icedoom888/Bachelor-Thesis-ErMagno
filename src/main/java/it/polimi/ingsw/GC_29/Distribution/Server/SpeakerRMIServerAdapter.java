package it.polimi.ingsw.GC_29.Distribution.Server;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Distribution.Common.DistributionAdapter;
import it.polimi.ingsw.GC_29.Distribution.Common.RegisterClient;

import javax.management.remote.rmi.RMIServer;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public class SpeakerRMIServerAdapter extends UnicastRemoteObject implements DistributionAdapter {

    private DistributionAdapter clientStub;

    protected SpeakerRMIServerAdapter(DistributionAdapter clientStub) throws RemoteException, AlreadyBoundException {

        this.clientStub = clientStub;
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("Speaker", this);
    }


    @Override
    public boolean doAction() {

        return clientStub.doAction();

    }

    @Override
    public FamilyPawn choosePawn(String familyPawns) {

        return clientStub.choosePawn(familyPawns);

    }

    @Override
    public void doLeaderAction() {

    }

    @Override
    public void chooseLeaderCard(int i) {

    }

    @Override
    public void useLeaderCard(int i) {

    }

    @Override
    public String showValidActions() {
        return null;
    }

    @Override
    public void askWhichAction() {

    }



    @Override
    public void payCard(int i) {

    }

    @Override
    public void chooseWorkers(int i) {

    }

    @Override
    public void chooseBonus(int i) {

    }

    @Override
    public void askForPray() {

    }
}
