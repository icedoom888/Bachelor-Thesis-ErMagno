package it.polimi.ingsw.GC_29.EffectBonusAndActions;


import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.Player;


/**
 * Created by Christian on 18/05/2017.
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
    public void execute(Player status) {

        //GoodSet newGoodsObtained = activateBonusMalusOnGoods(status,goodsObtained);
        Filter.apply(status, goodsObtained);
        status.updateGoodSet(goodsObtained);
    }

    @Override
    public String toString() {
        return "ObtainEffect{" + "goodsObtained=" + goodsObtained + '}';
    }


}
