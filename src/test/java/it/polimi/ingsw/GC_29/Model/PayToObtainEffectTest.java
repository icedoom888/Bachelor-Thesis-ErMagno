package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.PayToObtainController;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 08/07/2017.
 */
public class PayToObtainEffectTest {
    @Test
    public void testExecute() throws Exception {
        Player player1 = new Player("ciao",PlayerColor.GREEN,new PersonalBoard(6));
        player1.updateGoodSet(new GoodSet(3,3,3,3,3,3,3));
        PayToObtainEffect payToObtainEffect = new PayToObtainEffect(new GoodSet(0,1,0,0,0,0,0),new ObtainEffect(1,0,0,0,0,0,0));
        GoodSet expectedGoodSet = new GoodSet(4,2,3,3,3,3,3);
        payToObtainEffect.execute(player1);
        System.out.println(player1.getActualGoodSet());
        assertTrue(player1.getActualGoodSet().equals(expectedGoodSet));

    }

    @Test
    public void testCheckSufficientGoods() throws Exception {
        Player player1 = new Player("ciao",PlayerColor.GREEN,new PersonalBoard(6));
        player1.updateGoodSet(new GoodSet(3,3,3,3,3,3,3));
        PayToObtainEffect payToObtainEffect = new PayToObtainEffect(new GoodSet(1,1,1,0,0,0,0),null);
        assertTrue(payToObtainEffect.checkSufficientGoods(player1));
        player1.updateGoodSet(new GoodSet(-3,-3,-3,-3,-3,-3,-3));
        assertFalse(payToObtainEffect.checkSufficientGoods(player1));
    }

    @Test
    public void testToString() throws Exception {
        PayToObtainEffect payToObtainEffect = new PayToObtainEffect(new GoodSet(1,1,1,0,0,0,0),null);
        System.out.println(payToObtainEffect.toString());
    }

}