package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class GoodSetRequirement extends Requirement {
    private GoodSet goodSet;

    @Override
    public boolean check(Player status) {
        for (GoodType type : GoodType.values()) {
            if (status.getActualGoodSet().getGoodAmount(type) >= goodSet.getGoodAmount(type)) {
            } else {
                return false;
            }
        }
        return true;
    }
}
