package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 09/07/17.
 */
public class BonusAndMalusOnGoodsTest {
    @Test
    public void testFilter() throws Exception {

        GoodSet goodSetObtained1 = new GoodSet(1,1,1,1,1,1,1);
        GoodSet goodSetObtained2 = new GoodSet(1,1,1,1,1,1,1);
        GoodSet goodSetObtained3 = new GoodSet(1,1,1,1,1,1,1);

        BonusAndMalusOnGoods bonusAndMalusOnGoods1 = new BonusAndMalusOnGoods(new GoodSet(1,1,1,1,1,1,1));
        BonusAndMalusOnGoods bonusAndMalusOnGoods2 = new BonusAndMalusOnGoods(new GoodSet(-1,-1,-1,-1,-1,-1,-1));
        BonusAndMalusOnGoods bonusAndMalusOnGoods3 = new BonusAndMalusOnGoods(new GoodSet(-5,-5,-5,-5,-5,-5,-5));

        bonusAndMalusOnGoods1.filter(goodSetObtained1);
        bonusAndMalusOnGoods2.filter(goodSetObtained2);
        bonusAndMalusOnGoods3.filter(goodSetObtained3);

        assertTrue(goodSetObtained1.equals(new GoodSet(2,2,2,2,2,2,2)));
        assertTrue(goodSetObtained2.equals(new GoodSet()));
        assertTrue(goodSetObtained3.equals(new GoodSet()));


    }

}