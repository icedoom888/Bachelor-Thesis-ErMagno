package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class BonusAndMalusOnGoods {
    private GoodSet GoodSetBonusMalus;

    public BonusAndMalusOnGoods(GoodSet goodSetBonusMalus) {
        this.GoodSetBonusMalus = goodSetBonusMalus;
    }

    public GoodSet getGoodSetBonusMalus() {
        return GoodSetBonusMalus;
    }
}
