package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;

import static java.lang.Math.max;

/**
 * Created by Lorenzotara on 17/05/17.
 */

public class BonusAndMalusOnAction {

    private ZoneType zoneType;
    private int diceIncrementOrReduction;
    private FamilyPawnType familyPawnType;

    public BonusAndMalusOnAction(
            ZoneType zoneType,
            int diceIncrementOrReduction) {

        this.zoneType = zoneType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.familyPawnType = null;
    }

    public BonusAndMalusOnAction(int diceIncrementOrReduction) {
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.familyPawnType = null;
        this.zoneType = null;
    }

    public BonusAndMalusOnAction(int diceIncrementOrReduction, FamilyPawnType familyPawnType) {
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.familyPawnType = familyPawnType;
        this.zoneType = null;
    }

    public BonusAndMalusOnAction(ZoneType zoneType, int diceIncrementOrReduction, FamilyPawnType familyPawnType) {
        this.zoneType = zoneType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.familyPawnType = familyPawnType;
    }

    public ZoneType getZoneType() {
        return zoneType;
    }

    public int getDiceIncrementOrReduction() {
        return diceIncrementOrReduction;
    }

    public FamilyPawnType getFamilyPawnType() {
        return familyPawnType;
    }

    /**
     *
     * this method apply the bonusMalus to the value of the familyPawn if the zoneType of the bonusMalus matches
     * with the ZoneType passed to the method
     */
    public void filter(FamilyPawn familyPawn, ZoneType zoneType){

        /* if (this.zoneType == ZoneType.ANYZONE) {
            if (this.familyPawnType == FamilyPawnType.ANY) {

                int newActionValue = familyPawn.getActualValue() + diceIncrementOrReduction;

                familyPawn.setActualValue(newActionValue);
            }

            else if (this.familyPawnType == familyPawn.getType() || this.familyPawnType == null) {

                int newActionValue = familyPawn.getActualValue() + diceIncrementOrReduction;

                familyPawn.setActualValue(newActionValue);
            }
        } */

        if ((this.zoneType == ZoneType.ANYZONE
                    || (this.zoneType == ZoneType.ANYTOWER
                        && (zoneType == ZoneType.BLUETOWER
                            || zoneType == ZoneType.GREENTOWER
                            || zoneType == ZoneType.YELLOWTOWER
                            || zoneType == ZoneType.PURPLETOWER))
                    || this.zoneType == zoneType
                    || this.zoneType == null)

                && (this.familyPawnType == FamilyPawnType.ANY
                    || this.familyPawnType == familyPawn.getType()
                    || this.familyPawnType == null)) {

            int newActionValue = familyPawn.getActualValue() + diceIncrementOrReduction;
            familyPawn.setActualValue(newActionValue);
        }


    }

    @Override
    public String toString() {

        if(diceIncrementOrReduction != 0){
            return "BonusAndMalusOnAction { "
                    + "zoneType = " + zoneType + "\n"
                    + "diceIncrementOrReduction = " + diceIncrementOrReduction +"\n"
                    + '}';
        }
        else
            return "";
    }
}
