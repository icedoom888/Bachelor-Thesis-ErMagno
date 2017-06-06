package it.polimi.ingsw.GC_29.Distribution.Server;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Controllers.ActionChecker;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Distribution.Common.DistributionAdapter;

import java.util.Scanner;

/**
 * Created by Lorenzotara on 05/06/17.
 */
public class Speaker implements DistributionAdapter {

    private static Speaker instance;

    private Speaker() {
    }

    public static Speaker getInstance() {
        if (instance==null) {
            instance = new Speaker();
        }

        return instance;
    }

    @Override
    public boolean doAction() {

        return true;

    }

    @Override
    public FamilyPawn choosePawn(String string) {

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

        return ActionChecker.getInstance().getActionList().toString();

    }

    @Override
    public void askWhichAction() {

        GameStatus.getInstance().getCurrentPlayer().setCurrentAction(ActionChecker.getInstance().getActionList().get(0));

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
