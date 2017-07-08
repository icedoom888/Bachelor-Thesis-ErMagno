package it.polimi.ingsw.GC_29.Model;

import java.util.Map;

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

    public void filter(GoodSet goodsObtained) {

        Map<GoodType, Integer> temporaryHashMapGoodSet = goodsObtained.getMapGoodSet();

        for (GoodType type : GoodType.values()) {

            int goodObtainedAmount = temporaryHashMapGoodSet.get(type);

            int bonusMalusOnGoodAmount = goodSetBonusMalus.getMapGoodSet().get(type);

            if (goodObtainedAmount * bonusMalusOnGoodAmount != 0) {

                int temporaryAmount = goodObtainedAmount + bonusMalusOnGoodAmount;

                //Questo if else non esisteva, c'era la riga commentata sotto

                if (temporaryAmount >= 0) {

                    temporaryHashMapGoodSet.put(type, temporaryAmount);
                }
                else {
                    temporaryHashMapGoodSet.put(type, 0);
                }

                //temporaryHashMapGoodSet.put(type, temporaryAmount);
            }
        }
    }

    @Override
    public String toString() {

        if (goodSetBonusMalus.areAllZeroValues()) {
            return "";
        }
        else {


            return "BonusAndMalusOnGoods { " + "\n"
                    + "goodSetBonusMalus = " + goodSetBonusMalus + "\n"
                    + '}';

        }

    }
}
