package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.testng.annotations.Test;

/**
 * Created by AlbertoPennino on 20/05/2017.
 */
public class ObtainOnConditionEffectTest {
    @Test
    public void testExecute() throws Exception{
        GoodSet goodsForEachCondition_1 = new GoodSet(0,2,0,0,0,0,0);
        GoodSet goodsForEachCondition_2 = new GoodSet(1,1,1,1,1,1,1);
        GoodSet goodsCondition_2 = new GoodSet(4,0,0,0,0,0,0);
        ObtainOnConditionEffect test_1 = new ObtainOnConditionEffect(goodsForEachCondition_1, CardColor.GREEN);
        ObtainOnConditionEffect test_2 = new ObtainOnConditionEffect(goodsForEachCondition_2, goodsCondition_2);

        PlayerStatus testStatus = new PlayerStatus(null, null);
        GoodSet actualGoodSet = new GoodSet(9,0,0,0,0,0,0);
        testStatus.updateGoodSet(actualGoodSet);
        for (int i=0; i<5; i++) {
            testStatus.updateCardsOwned(CardColor.GREEN);
        }
        System.out.println(testStatus.getCardsOwned().get(CardColor.GREEN));

        System.out.println(test_1.goodsObtained);
        test_1.execute(testStatus);
        System.out.println(testStatus.getActualGoodSet());

        System.out.println();

        System.out.println(test_2.goodsObtained);
        test_2.evaluateActualGoodsObtained(testStatus);
        System.out.println(test_2.goodsObtained);





    }
}
