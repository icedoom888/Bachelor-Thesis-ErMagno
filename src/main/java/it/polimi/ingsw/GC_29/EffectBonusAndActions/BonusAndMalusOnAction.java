package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 17/05/17.
 */

public class BonusAndMalusOnAction {

    private ZoneType zoneType;
    private int diceIncrementOrReduction;
    private GoodSet goodSetDiscountOrIncrement;
    private boolean actionAllowed;


    public BonusAndMalusOnAction(
            ZoneType zoneType,
            int diceIncrementOrReduction,
            GoodSet goodSetDiscountOrIncrement) {

        this.zoneType = zoneType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.goodSetDiscountOrIncrement = goodSetDiscountOrIncrement;
        this.actionAllowed = true;
    }

    public BonusAndMalusOnAction(
            ZoneType zoneType,
            int diceIncrementOrReduction) {

        this.zoneType = zoneType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.goodSetDiscountOrIncrement = new GoodSet();
        this.actionAllowed = true;
    }


    public BonusAndMalusOnAction(ZoneType zoneType,
                                 boolean actionAllowed) {

        this.zoneType = zoneType;
        this.actionAllowed = actionAllowed;
        this.diceIncrementOrReduction = 0;
        this.goodSetDiscountOrIncrement = null;
    }

    public ZoneType getZoneType() {
        return zoneType;
    }

    public int getDiceIncrementOrReduction() {
        return diceIncrementOrReduction;
    }

    public GoodSet getGoodSetDiscountOrIncrement() {
        return goodSetDiscountOrIncrement;
    }

    public boolean isActionAllowed() {
        return actionAllowed;
    }

    // Questo metodo era un boolean, ma non aveva tanto senso
    public void filter(int actionValue, GoodSet actualGoodset){

    }

    @Override
    public String toString() {
        return "BonusAndMalusOnAction{" + "zoneType=" + zoneType + ", diceIncrementOrReduction=" + diceIncrementOrReduction + ", goodSetDiscountOrIncrement=" + goodSetDiscountOrIncrement + ", actionAllowed=" + actionAllowed + '}';
    }
}
