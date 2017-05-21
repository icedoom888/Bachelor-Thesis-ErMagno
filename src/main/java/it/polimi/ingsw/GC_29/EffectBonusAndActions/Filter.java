package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.CardCost;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.Player;
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
     * @param goodsObtained GoodSet to be filtered through the bonusMalusOnGoodsList
     */
    public static void apply(PlayerStatus playerStatus, GoodSet goodsObtained){

        ArrayList<BonusAndMalusOnGoods> currentPLayerBonusMalusOnGoods = playerStatus.getBonusAndMalusOnGoods();

        for (BonusAndMalusOnGoods playerBonusMalus : currentPLayerBonusMalusOnGoods) {

            playerBonusMalus.filter(goodsObtained);
        }
    }

    public static void apply(PlayerStatus playerStatus, CardCost cardCost) {

    }
}
