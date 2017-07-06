package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class CouncilPrivilegeEffect implements Effect {

    private int numberOfCouncilPrivileges;

    private List<CouncilPrivilege> parchmentList;

    public CouncilPrivilegeEffect(int numberOfCouncilPrivileges) {

        this.numberOfCouncilPrivileges = numberOfCouncilPrivileges;

        this.parchmentList = new ArrayList<>();
        for(int i = 0; i < numberOfCouncilPrivileges; i++){

            parchmentList.add(new CouncilPrivilege());

        }
    }

    public int getNumberOfCouncilPrivileges() {
        return numberOfCouncilPrivileges;
    }

    public List<CouncilPrivilege> getParchmentList() {
        return parchmentList;
    }

    @Override
    /**
     * A player can choose between three different effects in CouncilPrivilege.
     * After player's choice the collectRewards method creates a temporary goodSet in which
     * sums all the bonuses.
     * Then it updates the player's goodSet.
     */
    public void execute(Player status) {

        status.getCouncilPrivilegeEffectList().add(this);

    }


    @Override
    public String toString() {
          return "CouncilPrivilegeEffect { "
                + "numberOfCouncilPrivileges = " + numberOfCouncilPrivileges + "\n"
                + '}';
    }

}
