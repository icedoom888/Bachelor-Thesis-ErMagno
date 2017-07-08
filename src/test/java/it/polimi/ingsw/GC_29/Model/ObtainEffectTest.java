package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Model.*;
import org.testng.Assert;
import org.testng.annotations.Test;



/**
 * Created by Christian on 20/05/2017.
 */
public class ObtainEffectTest {

    @Test
    public void testExecute() throws Exception {
        GoodSet statusGoodset = new GoodSet(2,0,1,0,4,1,3);
        ObtainEffect testEffect = new ObtainEffect(4,2,0,0,5,0,1);
        BonusAndMalusOnGoods bonus = new BonusAndMalusOnGoods(new GoodSet(3,0,6,0,0,0,0));
        BonusAndMalusOnGoods malus = new BonusAndMalusOnGoods(new GoodSet(-1,0,-5,0,-4,0,-1000));
        Player status = new Player(null, null, new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6));
        status.getActualGoodSet().addGoodSet(statusGoodset);
        status.getBonusAndMalusOnGoods().add(bonus);
        status.getBonusAndMalusOnGoods().add(malus);

        testEffect.execute(status);
        System.out.println(status.getActualGoodSet());

        GoodSet expectedGoodset = new GoodSet(8,2,1,0,5,1,3);

        boolean verify = status.getActualGoodSet().equals(expectedGoodset);

        Assert.assertTrue(verify);
    }

}