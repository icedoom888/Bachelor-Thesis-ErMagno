package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;

import static java.lang.Math.max;

/**
 * Created by Lorenzotara on 17/05/17.
 */

public class BonusAndMalusOnAction {

    private ZoneType zoneType;
    private int diceIncrementOrReduction;

    public BonusAndMalusOnAction(
            ZoneType zoneType,
            int diceIncrementOrReduction) {

        this.zoneType = zoneType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
    }



    public ZoneType getZoneType() {
        return zoneType;
    }

    public int getDiceIncrementOrReduction() {
        return diceIncrementOrReduction;
    }


    /**
     *
     * this method apply the bonusMalus to the value of the familyPawn if the zoneType of the bonusMalus matches
     * with the ZoneType passed to the method
     */
    public void filter(FamilyPawn familyPawn, ZoneType zoneType){

        if(zoneType == this.zoneType){

            int newActionValue = familyPawn.getActualValue() + diceIncrementOrReduction;

            familyPawn.setActualValue(newActionValue);

        }
    }

    @Override
    public String toString() {
        return "BonusAndMalusOnAction{"
                + "zoneType=" + zoneType
                + ", diceIncrementOrReduction=" + diceIncrementOrReduction
                + '}';
    }
}
