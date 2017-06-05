package it.polimi.ingsw.GC_29.Distribution;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public class SpeakerRMIServerAdapter extends UnicastRemoteObject implements DistributionRmiServerAdapter{

    private DistributionAdapter speaker;

    protected SpeakerRMIServerAdapter(DistributionAdapter speaker) throws RemoteException, AlreadyBoundException {

        this.speaker = speaker;
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("Speaker", this);
    }

    @Override
    public void doAction() {

    }

    @Override
    public void choosePawn(int i) {

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
    public String showValidAction() {

        return speaker.showValidActions();

    }

    @Override
    public void chooseAction(int i) {

        speaker.chooseAction(i);

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
}
