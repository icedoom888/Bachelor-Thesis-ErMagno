package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class BonusAndMalusOnGoods {
    private boolean whenObtained;
    private GoodSet GoodSetBonusMalus;

    public BonusAndMalusOnGoods(GoodSet goodSetBonusMalus, boolean whenObtained) {
        this.whenObtained = whenObtained;
        this.GoodSetBonusMalus = goodSetBonusMalus;
    }

    public boolean isWhenObtained() {
        return whenObtained;
    }

    public GoodSet getGoodSetBonusMalus() {
        return GoodSetBonusMalus;
    }
}
