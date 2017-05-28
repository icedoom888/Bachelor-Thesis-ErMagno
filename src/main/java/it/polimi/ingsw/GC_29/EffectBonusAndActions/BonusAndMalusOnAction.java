package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 17/05/17.
 */

public class BonusAndMalusOnAction {
    private ZoneType zoneType;
    private int diceIncrementOrReduction;
    private boolean actionAllowed;


    public BonusAndMalusOnAction(
            ZoneType zoneType,
            int diceIncrementOrReduction) {

        this.zoneType = zoneType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.actionAllowed = true;
    }



    public BonusAndMalusOnAction(ZoneType zoneType,
                                 boolean actionAllowed) {

        this.zoneType = zoneType;
        this.actionAllowed = actionAllowed;
        this.diceIncrementOrReduction = 0;
    }

    public ZoneType getZoneType() {
        return zoneType;
    }

    public int getDiceIncrementOrReduction() {
        return diceIncrementOrReduction;
    }


    public boolean isActionAllowed() {
        return actionAllowed;
    }

    // Questo metodo era un boolean, ma non aveva tanto senso
    public void filter(int actionValue, GoodSet actualGoodset){

    }

    @Override
    public String toString() {
        return "BonusAndMalusOnAction{" + "zoneType=" + zoneType + ", diceIncrementOrReduction=" + diceIncrementOrReduction + ", actionAllowed=" + actionAllowed + '}';
    }
}
