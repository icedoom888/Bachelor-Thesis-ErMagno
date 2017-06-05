package it.polimi.ingsw.GC_29.Distribution;

import java.rmi.Remote;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public interface DistributionRmiServerAdapter extends Remote {

    void doAction(); //vuoi fare l'azione?
    void choosePawn(int i);
    void doLeaderAction(); // vuoi fare azione leader?
    void chooseLeaderCard(int i);
    void useLeaderCard(int i); // scarta o attiva effetto
    String showValidAction();
    void chooseAction(int i);
    void payCard(int i); // quale costo vuoi pagare? TowerAction
    void chooseWorkers(int i); // quanti workers vuoi usare? WorkAction
    void chooseBonus(int i); // quali pergamene vuoi? CouncilPrivilegeEffect

}
