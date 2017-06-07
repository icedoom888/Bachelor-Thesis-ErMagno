package it.polimi.ingsw.GC_29.Distribution.Client;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Distribution.Common.DistributionAdapter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public class SpeakerRMIClientAdapter implements DistributionAdapter {

    private DistributionAdapter speaker;

    public SpeakerRMIClientAdapter(String nameRemoteObject) throws RemoteException, NotBoundException {
        connect(nameRemoteObject);
    }

    private void connect(String nameRemoteObject) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();

        this.speaker = (DistributionAdapter) registry.lookup(nameRemoteObject);
    }

    @Override
    public boolean doAction() {

        Boolean answer = null;
        while (answer == null) {
            System.out.println("Do you want to do an action? (true/false)");
            Scanner in = new Scanner(System.in);
            answer = in.nextBoolean();
        }

        return answer;

    }


    @Override
    public FamilyPawn choosePawn(String familyPawns) {

        String answer = null;
        System.out.println("These are your pawns.");
        System.out.println(familyPawns);
        System.out.println("Choose the type of the one that you want");
        while (answer != "red" && answer != "orange" && answer != "black" && answer != "neutral") {
            Scanner in = new Scanner(System.in);
            answer = in.nextLine();
        }

        //return answer;
        return null;

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
        return speaker.showValidActions();

    }

    @Override
    public void askWhichAction() { // SBAGLIATO riceve un intero, non ha senso

        Scanner in = new Scanner(System.in);
        int answer = in.nextInt();



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
