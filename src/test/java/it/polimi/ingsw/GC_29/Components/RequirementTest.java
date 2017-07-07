package it.polimi.ingsw.GC_29.Components;

import static org.junit.Assert.*;

import java.util.HashMap;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class RequirementTest {
    @org.testng.annotations.Test
    public void testIsPossible() throws Exception {

        GoodSet goodSetRequirement1 = new GoodSet(1,1,0,0,0,0,0);
        HashMap<CardColor,Integer> cardRequirement2 = new HashMap<>();
        cardRequirement2.put(CardColor.BLUE,3);
        Requirement requirement1 = new Requirement(goodSetRequirement1, null);
        Requirement requirement2 = new Requirement(null, cardRequirement2);

        GoodSet goodsetPlayer1 = new GoodSet(2,1,0,0,0,0,0);
        HashMap<CardColor,Integer> cardPlayer1 = new HashMap<>();
        cardPlayer1.put(CardColor.BLUE,2);

        HashMap<CardColor,Integer> cardPlayer2 = new HashMap<>();
        cardPlayer2.put(CardColor.BLUE,5);

        assertTrue(requirement1.isPossible(cardPlayer1,goodsetPlayer1));
        assertFalse(requirement2.isPossible(cardPlayer1,goodsetPlayer1));
        assertTrue(requirement2.isPossible(cardPlayer2,goodsetPlayer1));
    }
}
