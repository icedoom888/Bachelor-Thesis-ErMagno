/**package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.testng.annotations.Test;

/**
 * Created by AlbertoPennino on 20/05/2017.
 */
/**public class PayToObtainEffectTest {
    @Test
    public void testExecute() throws Exception{
        PlayerStatus testStatus = new PlayerStatus(null, null);
        GoodSet statusGoodSet = new GoodSet(5,5,5,5,5,5,5);
        testStatus.updateGoodSet(statusGoodSet);
        System.out.println(testStatus.getActualGoodSet());
        GoodSet costPayable = new GoodSet(-5,-4,-3,-2,-0,-0,-1);
        GoodSet costUnpayable = new GoodSet(-6,0,0,0,0,0,0);
        GoodSet costPayable_2 = new GoodSet(-5,-5,-5,-5,-5,-5,-5);
        GoodSet goodsObtained_1 = new GoodSet(5,4,3,2,0,0,1);
        PayToObtainEffect testEffect_1 = new PayToObtainEffect(costPayable,goodsObtained_1);
        PayToObtainEffect testEffect_2 = new PayToObtainEffect(costUnpayable,goodsObtained_1);
        PayToObtainEffect testEffect_3 = new PayToObtainEffect(costPayable_2, new GoodSet());

        testEffect_1.execute(testStatus);
        System.out.println(testStatus.getActualGoodSet());

        testEffect_2.execute(testStatus);
        System.out.println(testStatus.getActualGoodSet());

        testEffect_3.execute(testStatus);
        System.out.println(testStatus.getActualGoodSet());


    }
}
*/