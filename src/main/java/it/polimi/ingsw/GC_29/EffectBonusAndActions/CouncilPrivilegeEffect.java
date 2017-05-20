package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class CouncilPrivilegeEffect implements Effect {

    private int numberOfCouncilPrivileges;
    private CouncilPrivilegeType[] effectsChosen;

    public CouncilPrivilegeEffect(int numberOfCouncilPrivileges) {
        this.numberOfCouncilPrivileges = numberOfCouncilPrivileges;
        this.effectsChosen = new CouncilPrivilegeType[numberOfCouncilPrivileges];
    }

    @Override
    /**
     * A player can choose between three different effects in CouncilPrivilegeType.
     * After player's choice the collectRewards method creates a temporary goodSet in which
     * sums all the bonuses.
     * Then it updates the player's goodSet.
     */
    public void execute(PlayerStatus status) {

        System.out.println("You received a Council Privilege Effect!");

        boolean decisionDone = false;

        while (decisionDone == false) {
            whichPrivileges();
            decisionDone = checkDifferentPrivileges();
        }

        status.updateGoodSet(collectRewards());

    }


    public void whichPrivileges() {
        System.out.println("Choose " + numberOfCouncilPrivileges + " different priviliges between the following: ");
    }

    /**
     * This method checks if the player chose three different priviliges and returns true
     * if everything went well
     */
    public boolean checkDifferentPrivileges() {

        boolean duplicate = false;
        for (int i = 0; i < numberOfCouncilPrivileges; i++) {
            for (int k = i+1; k < numberOfCouncilPrivileges; k++) {
                if (k != i && effectsChosen[k] == effectsChosen[i]) duplicate = true;
                break;
            }
        }
        return !duplicate;
    }

    /**
     * collectRewards method adds to a temporaryGoodSet all the rewards of the effect
     */
    public GoodSet collectRewards() {

        GoodSet temporaryGoodSet = new GoodSet();

        for (CouncilPrivilegeType councilPrivilegeType : effectsChosen) {
            switch (councilPrivilegeType) {
                case ONEWOOD_ONESTONE:
                    temporaryGoodSet.addGoodSet(new GoodSet(1, 1, 0, 0, 0, 0, 0));
                    break;
                case TWOWORKERS:
                    temporaryGoodSet.addGoodSet(new GoodSet(0, 0, 0, 2, 0, 0, 0));
                    break;
                case TWOGOLDS:
                    temporaryGoodSet.addGoodSet(new GoodSet(0, 0, 2, 0, 0, 0, 0));
                    break;
                case TWOMILITARYPOINTS:
                    temporaryGoodSet.addGoodSet(new GoodSet(0, 0, 0, 0, 0, 2, 0));
                    break;
                case ONEFAITHPOINT:
                    temporaryGoodSet.addGoodSet(new GoodSet(0, 0, 0, 0, 0, 0, 1));
                    break;
            }
        }

        return temporaryGoodSet;
    }
}
