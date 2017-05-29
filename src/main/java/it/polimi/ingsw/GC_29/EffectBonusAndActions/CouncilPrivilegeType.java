package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 20/05/17.
 */

public enum CouncilPrivilegeType {
    ONEWOOD_ONESTONE(1,1,0,0,0,0,0),
    TWOWORKERS(0,0,0,2,0,0,0),
    TWOGOLDS(0,0,2,0,0,0,0),
    TWOMILITARYPOINTS(0,0,0,0,0,2,0),
    ONEFAITHPOINT(0,0,0,0,0,0,1);

    private GoodSet goodSet;

    CouncilPrivilegeType(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {
        goodSet = new GoodSet(wood,stone,coins,workers,victoryPoints,militaryPoints,faithPoints);
    }

    public GoodSet getGoodSet() {
        return goodSet;
    }
}
