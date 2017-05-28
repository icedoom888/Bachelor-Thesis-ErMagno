package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;

import java.util.HashMap;

import static java.lang.Math.max;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class BonusAndMalusOnGoods {
    private GoodSet goodSetBonusMalus;

    public BonusAndMalusOnGoods(GoodSet goodSetBonusMalus) {
        this.goodSetBonusMalus = goodSetBonusMalus;
    }

    public GoodSet getGoodSetBonusMalus() {
        return goodSetBonusMalus;
    }

    public void filter(GoodSet goodsObtained){

        HashMap<GoodType, Integer> temporaryHashMapGoodSet = goodsObtained.getHashMapGoodSet();

        for(GoodType type : GoodType.values()) { // il doppio ciclo for mi sta bene poiché la dimensione del secondo for è costante, dunque complessità O(n)

            int goodObtainedAmount = temporaryHashMapGoodSet.get(type);

            int BonusMalusOnGoodAmount = goodSetBonusMalus.getHashMapGoodSet().get(type);

            if(goodObtainedAmount*BonusMalusOnGoodAmount !=0) {

                int temporaryAmount = max(0,goodObtainedAmount + BonusMalusOnGoodAmount);

                temporaryHashMapGoodSet.put(type, temporaryAmount);
            }
        }
    }

    @Override
    public String toString() {
        return "BonusAndMalusOnGoods{" + "goodSetBonusMalus=" + goodSetBonusMalus + '}';
    }
}
