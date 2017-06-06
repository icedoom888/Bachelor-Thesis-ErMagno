package it.polimi.ingsw.GC_29.Distribution.Client;

import it.polimi.ingsw.GC_29.Distribution.Common.DistributionAdapter;
import it.polimi.ingsw.GC_29.Distribution.Server.DistributionRmiServerAdapter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public class SpeakerRMIClientAdapter implements DistributionAdapter {

    private DistributionRmiServerAdapter speaker;

    public SpeakerRMIClientAdapter(String nameRemoteObject) throws RemoteException, NotBoundException {
        connect(nameRemoteObject);
    }

    private void connect(String nameRemoteObject) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();

        this.speaker = (DistributionRmiServerAdapter) registry.lookup(nameRemoteObject);
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
    public String showValidActions() {
        return speaker.showValidAction();

    }

    @Override
    public void chooseAction(int i) {

        Scanner in = new Scanner(System.in);
        int answer = in.nextInt();

        speaker.chooseAction(answer);


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
