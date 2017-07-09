package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 09/07/2017.
 */
public class LeaderCardTest {
    @Test
    public void testIsPossible() throws Exception {
        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("d", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("x", PlayerColor.YELLOW, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        GameSetup gameSetup = new GameSetup(players);
        gameSetup.init();
        gameSetup.setExcommunicationTiles();
        gameSetup.setLeaderCards();
        gameSetup.setGoodsForPlayers();
        Model model = gameSetup.getModel();
        Controller controller = null;
        try {
            controller = new Controller(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.chooseCurrentPlayer(0);

        player1.updateGoodSet(new GoodSet(50,50,50,50,50,50,50));
        CardCost cost = new CardCost(true,true,new Cost(new GoodSet(1,0,0,0,0,0,0),new GoodSet(0,0,0,0,0,0,0)),new Cost(new GoodSet(2,0,0,0,0,0,0),new GoodSet(3,0,0,0,0,0,0)));
        DevelopmentCard card1 = new DevelopmentCard("cia",Era.FIRST,cost,CardColor.GREEN,null,null,true,0);
        DevelopmentCard card2 = new DevelopmentCard("cia",Era.FIRST,cost,CardColor.YELLOW,null,null,true,0);
        DevelopmentCard card3 = new DevelopmentCard("cia",Era.FIRST,cost,CardColor.BLUE,null,null,true,0);
        DevelopmentCard card4 = new DevelopmentCard("cia",Era.FIRST,cost,CardColor.PURPLE,null,null,true,0);

        player1.getPersonalBoard().getLane(CardColor.YELLOW).addCard(card2);
        player1.getPersonalBoard().getLane(CardColor.YELLOW).addCard(card2);
        player1.getPersonalBoard().getLane(CardColor.YELLOW).addCard(card2);
        player1.getPersonalBoard().getLane(CardColor.YELLOW).addCard(card2);
        player1.getPersonalBoard().getLane(CardColor.YELLOW).addCard(card2);
        player1.getPersonalBoard().getLane(CardColor.YELLOW).addCard(card2);


        player1.getPersonalBoard().getLane(CardColor.BLUE).addCard(card3);
        player1.getPersonalBoard().getLane(CardColor.BLUE).addCard(card3);
        player1.getPersonalBoard().getLane(CardColor.BLUE).addCard(card3);
        player1.getPersonalBoard().getLane(CardColor.BLUE).addCard(card3);
        player1.getPersonalBoard().getLane(CardColor.BLUE).addCard(card3);
        player1.getPersonalBoard().getLane(CardColor.BLUE).addCard(card3);


        player1.getPersonalBoard().getLane(CardColor.PURPLE).addCard(card4);
        player1.getPersonalBoard().getLane(CardColor.PURPLE).addCard(card4);
        player1.getPersonalBoard().getLane(CardColor.PURPLE).addCard(card4);
        player1.getPersonalBoard().getLane(CardColor.PURPLE).addCard(card4);
        player1.getPersonalBoard().getLane(CardColor.PURPLE).addCard(card4);
        player1.getPersonalBoard().getLane(CardColor.PURPLE).addCard(card4);


        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);
        player1.getPersonalBoard().getLane(CardColor.GREEN).addCard(card1);

        if (player1.getLeaderCards().get(0).isPossible(player1)){
            player1.getLeaderCards().get(0).execute(player1);
            assertTrue(player1.getLeaderCards().get(0).isActivated());
        }
        if (player1.getLeaderCards().get(1).isPossible(player1)){
            player1.getLeaderCards().get(1).execute(player1);
            assertTrue(player1.getLeaderCards().get(1).isActivated());

        }
        if (player1.getLeaderCards().get(2).isPossible(player1)){
            player1.getLeaderCards().get(2).execute(player1);
            assertTrue(player1.getLeaderCards().get(2).isActivated());

        }
        if (player1.getLeaderCards().get(3).isPossible(player1)){
            player1.getLeaderCards().get(3).execute(player1);
            assertTrue(player1.getLeaderCards().get(3).isActivated());

        }
        player1.getLeaderCards().get(0).getLeaderName();
        player1.getLeaderCards().get(0).getRequirement();
        player1.getLeaderCards().get(0).setActivated(true);
        assertFalse(player1.getLeaderCards().get(0).isDiscarded());
        player1.getLeaderCards().get(0).setDiscarded();
        assertTrue(player1.getLeaderCards().get(0).isDiscarded());
        player1.getLeaderCards().get(0).getUrl();
        player1.getLeaderCards().get(0).isPermanent();



        player2.updateGoodSet(new GoodSet(0,0,0,0,0,0,0));
        assertFalse(player2.getLeaderCards().get(0).isPossible(player2));

        player2.getLeaderCards().get(0).toString();
    }

}