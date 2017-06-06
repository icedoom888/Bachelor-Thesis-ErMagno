package it.polimi.ingsw.GC_29.Distribution.Common;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public interface DistributionAdapter {

    boolean doAction(); //vuoi fare l'azione?
    FamilyPawn choosePawn(String familyPawns);
    void doLeaderAction(); // vuoi fare azione leader?
    void chooseLeaderCard(int i);
    void useLeaderCard(int i); // scarta o attiva effetto
    String showValidActions(); // mostra le azioni possibili
    void askWhichAction();
    void payCard(int i); // quale costo vuoi pagare? TowerAction
    void chooseWorkers(int i); // quanti workers vuoi usare? WorkAction
    void chooseBonus(int i); // quali pergamene vuoi? CouncilPrivilegeEffect
    void askForPray();

}
