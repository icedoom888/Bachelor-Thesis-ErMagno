package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Christian on 18/05/2017.
 * Upgraded by Icedoom on 19/05/2017.
 */
public class ObtainEffect implements Effect {

    protected GoodSet goodsObtained;

    public ObtainEffect(GoodSet goodSetObtained) {

        this.goodsObtained = goodSetObtained;
    }

    public ObtainEffect(int wood, int stone, int coins, int workers, int victoryPoints, int militaryPoints, int faithPoints) {

        this.goodsObtained = new GoodSet(wood, stone, coins, workers, victoryPoints, militaryPoints, faithPoints);
    }

    public ObtainEffect(){
        this.goodsObtained = new GoodSet();
    }

    public GoodSet getGoodsObtained() {
        return new GoodSet(goodsObtained);
    }

    @Override
    public void execute(PlayerStatus status) {
        GoodSet newGoodsObtained = activateBonusMalusOnGoods(status,goodsObtained);
        status.updateGoodSet(newGoodsObtained);
    }

    /**
     *
     * @param goodset goods obtained from the effect, these goods must be filtered in the player's bonusMalus on goods list
     * @return for each bonusMalus on goods in the list the method check if one specific good is obtained and if it has a BonusMalus,
     * this is done by the if condition (example: BonusMalus A, it has -2 on wood and -2 on stone, the goodset obtained has 3 wood and 1 coin,
     * the if condition is true for the wood, false for the coin and the stone)
     */
    protected GoodSet activateBonusMalusOnGoods(PlayerStatus playerStatus,GoodSet goodset) { // questo sarà il metodo filter

        GoodSet filteredGoodSet = new GoodSet(goodset);

        Filter.apply(playerStatus, filteredGoodSet);

        return filteredGoodSet;
    }
}
