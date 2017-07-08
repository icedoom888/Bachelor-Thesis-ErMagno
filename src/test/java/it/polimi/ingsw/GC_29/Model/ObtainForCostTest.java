package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 08/07/2017.
 */
public class ObtainForCostTest {
    @Test
    public void testExecute() throws Exception {
        Player player1 = new Player("ciao",PlayerColor.GREEN,new PersonalBoard(6));
        ArrayList<GoodType> list = new ArrayList<>();
        list.add(GoodType.WOOD);
        CardCost cost = new CardCost(true,true,new Cost(new GoodSet(1,0,0,0,0,0,0),new GoodSet(0,0,0,0,0,0,0)),new Cost(new GoodSet(2,0,0,0,0,0,0),new GoodSet(3,0,0,0,0,0,0)));
        DevelopmentCard card1 = new DevelopmentCard("cia",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);
        DevelopmentCard card2 = new DevelopmentCard("cia2",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);
        DevelopmentCard card3 = new DevelopmentCard("cia3",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);
        DevelopmentCard card4 = new DevelopmentCard("cia4",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);
        DevelopmentCard card5 = new DevelopmentCard("cia5",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);
        DevelopmentCard card6 = new DevelopmentCard("cia6",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);

        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card2);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card3);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card4);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card5);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card6);

        /*
        for (DevelopmentCard card : player1.getPersonalBoard().getLane(CardColor.GREEN).getCards()){
            System.out.println(card);
        }*/

        ObtainForCost obtainForCost = new ObtainForCost(CardColor.GREEN,list,new GoodSet(0,1,0,0,0,0,0));
        GoodSet expectedGoodSet = new GoodSet(0,18,0,0,0,0,0);
        obtainForCost.execute(player1);
        assertTrue(player1.getActualGoodSet().equals(expectedGoodSet));
    }

}