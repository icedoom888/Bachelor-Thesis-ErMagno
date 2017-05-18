package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class BonusAndMalusOnGoodsObtained {
    private GoodSet GoodSetBonusMalus;

    public BonusAndMalusOnGoodsObtained(GoodSet goodSetBonusMalus) {
        GoodSetBonusMalus = goodSetBonusMalus;
    }

    public GoodSet getGoodSetBonusMalus() {
        return GoodSetBonusMalus;
    }
}
