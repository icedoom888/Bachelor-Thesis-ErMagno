package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.PersonalBoard;
import it.polimi.ingsw.GC_29.Player.Player;
import org.testng.annotations.Test;

/**
 * Created by AlbertoPennino on 20/05/2017.
 */
public class ObtainOnConditionEffectTest {
    @Test
    public void testExecute() throws Exception{
        Effect effectForEachCondition_1 = new ObtainEffect(new GoodSet(0,2,0,0,0,0,0));
        Effect effectForEachCondition_2 = new ObtainEffect(new GoodSet(1,1,1,1,1,1,1));
        GoodSet goodsCondition_2 = new GoodSet(4,0,0,0,0,0,0);
        ObtainOnConditionEffect test_1 = new ObtainOnConditionEffect(effectForEachCondition_1, CardColor.GREEN);
        ObtainOnConditionEffect test_2 = new ObtainOnConditionEffect(effectForEachCondition_2, goodsCondition_2);

        Player testStatus = new Player(null, null, new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6));        GoodSet actualGoodSet = new GoodSet(9,0,0,0,0,0,0);
        testStatus.updateGoodSet(actualGoodSet);
        for (int i=0; i<5; i++) {
            testStatus.updateCardsOwned(CardColor.GREEN);
        }
        System.out.println(testStatus.getCardsOwned().get(CardColor.GREEN));

        //System.out.println(test_1.goodsObtained);
        test_1.execute(testStatus);
        System.out.println(testStatus.getActualGoodSet());

        System.out.println();

        //System.out.println(test_2.goodsObtained);
        test_2.evaluateActualGoodsObtained(testStatus);
        //System.out.println(test_2.goodsObtained);





    }
}
