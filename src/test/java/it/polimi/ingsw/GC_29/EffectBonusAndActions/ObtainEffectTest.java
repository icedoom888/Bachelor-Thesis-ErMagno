package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.junit.jupiter.api.Test;



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
        PlayerStatus status = new PlayerStatus(null, null);
        status.getActualGoodSet().addGoodSet(statusGoodset);
        status.getBonusAndMalusOnGoods().add(bonus);
        status.getBonusAndMalusOnGoods().add(malus);

        testEffect.execute(status);
        System.out.println(status.getActualGoodSet());
    }

    @Test
    public void testActivateBonusMalusOnGoods() throws Exception {

    }

}