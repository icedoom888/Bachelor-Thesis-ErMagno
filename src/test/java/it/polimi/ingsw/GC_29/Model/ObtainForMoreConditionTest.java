package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 09/07/2017.
 */
public class ObtainForMoreConditionTest {
    @Test
    public void testExecute() throws Exception {
        Player player1 = new Player("ciao", PlayerColor.GREEN, new PersonalBoard(6));
        player1.updateGoodSet(new GoodSet(3,3,3,3,3,3,3));

        ObtainEffect obtainEffect = new ObtainEffect(new GoodSet(1,0,0,0,0,0,0));
        ArrayList<GoodSet> list = new ArrayList<>();
        list.add(new GoodSet(1,0,0,0,0,0,0));

        ObtainForMoreCondition obtainForMoreCondition = new ObtainForMoreCondition(obtainEffect,list);
        obtainForMoreCondition.execute(player1);
        System.out.println(player1.getActualGoodSet());
        GoodSet expectedGoodSet = new GoodSet(6,3,3,3,3,3,3);
        assertTrue(player1.getActualGoodSet().equals(expectedGoodSet));

    }
}