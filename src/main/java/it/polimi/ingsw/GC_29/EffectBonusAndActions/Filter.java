package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by Christian on 21/05/2017.
 */

/**
 * The class Filter mimics the behavior of a static class
 */
public final class Filter {

    private Filter(){ // private in order to deny the instantiation

    }

    /**
     *
     * @param currentPLayerBonusMalusOnGoods BonusMalus on Goods list of the player
     * @param goodsObtained GoodSet to be filtered through the bonusMalusOnGoodsList
     */
    public static void apply(ArrayList<BonusAndMalusOnGoods> currentPLayerBonusMalusOnGoods, GoodSet goodsObtained){

        for (BonusAndMalusOnGoods playerBonusMalus : currentPLayerBonusMalusOnGoods) {

            playerBonusMalus.filter(goodsObtained);
        }
    }
}
