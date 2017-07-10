package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Controllers.Input.Pray;
import it.polimi.ingsw.GC_29.Controllers.Input.PrivilegeChosen;
import it.polimi.ingsw.GC_29.Model.*;
import it.polimi.ingsw.GC_29.Query.GetCouncilPrivileges;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 10/07/17.
 */
public class ControllerTest {
    @Test
    public void testHandleEndRound() throws Exception {

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

        controller.setCardsOnTowers();

        model.setCurrentRound(2);

        player1.updateGoodSet(new GoodSet(0,0,0,0,0,0,10));
        player2.updateGoodSet(new GoodSet(0,0,0,0,0,0,3));
        player3.updateGoodSet(new GoodSet(0,0,0,0,0,0,2));
        player4.updateGoodSet(new GoodSet(0,0,0,0,0,0,0));

        controller.handleEndRound();

        assertTrue(player1.getPlayerState() == PlayerState.PRAY);
        assertTrue(player2.getPlayerState() == PlayerState.PRAY);

        assertTrue(player3.getBonusAndMalusOnAction().size() > 0
                || player3.getBonusAndMalusOnCost().size() > 0
                || player3.getBonusAndMalusOnGoods().size() > 0
                || player3.getSpecialBonusAndMaluses().size() > 0 );

        assertTrue(player4.getBonusAndMalusOnAction().size() > 0
                || player4.getBonusAndMalusOnCost().size() > 0
                || player4.getBonusAndMalusOnGoods().size() > 0
                || player4.getSpecialBonusAndMaluses().size() > 0 );

        new Pray(true, player1.getPlayerColor()).perform(model, controller);
        new Pray(false, player2.getPlayerColor()).perform(model, controller);

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS) == 0);
        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS) == 15);

        assertTrue(player2.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS) == 3);
        assertTrue(player2.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS) == 0);

        assertTrue(player2.getBonusAndMalusOnAction().size() > 0
                || player2.getBonusAndMalusOnCost().size() > 0
                || player2.getBonusAndMalusOnGoods().size() > 0
                || player2.getSpecialBonusAndMaluses().size() > 0 );




    }

    @Test
    public void testSetNewRound() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("1", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("2", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("3", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("4", PlayerColor.YELLOW, new PersonalBoard(6));

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

        controller.setCardsOnTowers();

        model.setCurrentRound(1);

        PlayerColor[] playerColors = model.getGameBoard().getCouncilPalace().getTurnOrder();
        playerColors[0] = player2.getPlayerColor();
        playerColors[1] = player3.getPlayerColor();
        playerColors[2] = player4.getPlayerColor();
        playerColors[3] = player1.getPlayerColor();

        player1.getSpecialBonusAndMaluses().add(SpecialBonusAndMalus.SKIPFIRSTTURN);

        controller.setNewRound();

        for (Player player : players) {
            for (Boolean aBoolean : player.getFamilyPawnAvailability().values()) {
                assertTrue(aBoolean);
            }
            for (LeaderCard leaderCard : player.getLeaderCards()) {
                if (!leaderCard.isPermanent()) {
                    assertTrue(!leaderCard.isActivated());
                }
            }
        }

        List<Player> turnOrder = model.getTurnOrder();
        assertTrue(turnOrder.get(0).getPlayerColor() == player2.getPlayerColor());
        assertTrue(turnOrder.get(1).getPlayerColor() == player3.getPlayerColor());
        assertTrue(turnOrder.get(2).getPlayerColor() == player4.getPlayerColor());
        assertTrue(turnOrder.get(3).getPlayerColor() == player1.getPlayerColor());

        assertTrue(model.getSkippedTurnPlayers().size() == 1 && model.getSkippedTurnPlayers().get(0)==player1);
        assertTrue(model.getCurrentTurn() == 1);
        assertTrue(model.getCurrentRound() == 2);
    }

    @Test
    public void testEndGame() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("1", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("2", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("3", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("4", PlayerColor.YELLOW, new PersonalBoard(6));

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

        controller.setCardsOnTowers();

        player1.updateGoodSet(new GoodSet(0,0,0,0,0,100,0));
        int oneVictory = 5;
        player2.updateGoodSet(new GoodSet(0,0,0,0,0,50,0));
        int twoVictory = 2;
        player3.updateGoodSet(new GoodSet(0,0,0,0,0,50,0));
        int threeVictory = 2;
        player4.updateGoodSet(new GoodSet(0,0,0,0,0,0,0));
        int fourVictory = 0;

        ArrayList<Effect> permaments = new ArrayList<>();
        permaments.add(new ObtainEffect(0,0,0,0,10,0,0));
        DevelopmentCard purple = new DevelopmentCard(null, null, null, CardColor.PURPLE, null, permaments, false, 0);

        player1.getPersonalBoard().getVenturesLane().addCard(purple);
        player1.getCardsOwned().put(CardColor.PURPLE, 1);
        oneVictory += 10;
        player2.getPersonalBoard().getVenturesLane().addCard(purple);
        player2.getPersonalBoard().getVenturesLane().addCard(purple);
        player2.getCardsOwned().put(CardColor.PURPLE, 2);
        twoVictory += 20;
        player3.getPersonalBoard().getVenturesLane().addCard(purple);
        player3.getPersonalBoard().getVenturesLane().addCard(purple);
        player3.getPersonalBoard().getVenturesLane().addCard(purple);
        player3.getCardsOwned().put(CardColor.PURPLE, 3);
        threeVictory += 30;
        player4.getPersonalBoard().getVenturesLane().addCard(purple);
        player4.getPersonalBoard().getVenturesLane().addCard(purple);
        player4.getCardsOwned().put(CardColor.PURPLE, 2);
        fourVictory += 20;

        player1.getCardsOwned().put(CardColor.BLUE, 6);
        oneVictory += 21;
        player2.getCardsOwned().put(CardColor.BLUE, 5);
        twoVictory += 15;
        player3.getCardsOwned().put(CardColor.BLUE, 4);
        threeVictory += 10;
        player4.getCardsOwned().put(CardColor.BLUE, 3);
        fourVictory += 6;

        player1.getCardsOwned().put(CardColor.GREEN, 6);
        oneVictory += 20;
        player2.getCardsOwned().put(CardColor.GREEN, 5);
        twoVictory += 10;
        player3.getCardsOwned().put(CardColor.GREEN, 4);
        threeVictory += 4;
        player4.getCardsOwned().put(CardColor.GREEN, 3);
        fourVictory += 1;

        player1.getActualGoodSet().setGoodAmount(GoodType.WOOD, 10);
        player2.getActualGoodSet().setGoodAmount(GoodType.WOOD, 10);
        player3.getActualGoodSet().setGoodAmount(GoodType.WOOD, 10);
        player4.getActualGoodSet().setGoodAmount(GoodType.WOOD, 10);

        player1.getActualGoodSet().setGoodAmount(GoodType.STONE, 10);
        player2.getActualGoodSet().setGoodAmount(GoodType.STONE, 10);
        player3.getActualGoodSet().setGoodAmount(GoodType.STONE, 10);
        player4.getActualGoodSet().setGoodAmount(GoodType.STONE, 10);

        player1.getActualGoodSet().setGoodAmount(GoodType.COINS, 10);
        player2.getActualGoodSet().setGoodAmount(GoodType.COINS, 10);
        player3.getActualGoodSet().setGoodAmount(GoodType.COINS, 10);
        player4.getActualGoodSet().setGoodAmount(GoodType.COINS, 10);

        player1.getActualGoodSet().setGoodAmount(GoodType.WORKERS, 10);
        player2.getActualGoodSet().setGoodAmount(GoodType.WORKERS, 10);
        player3.getActualGoodSet().setGoodAmount(GoodType.WORKERS, 10);
        player4.getActualGoodSet().setGoodAmount(GoodType.WORKERS, 10);

        oneVictory += 8;
        twoVictory += 8;
        threeVictory += 8;
        fourVictory += 8;

        System.out.println(player1.getPlayerID() + " " + oneVictory +  " " +player2.getPlayerID() + " " + twoVictory+ " " + player3.getPlayerID() + " " + threeVictory+  " " + player4.getPlayerID() + " " + fourVictory);

        controller.endGame();

        assertTrue(player1.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS) == oneVictory);
        assertTrue(player2.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS) == twoVictory);
        assertTrue(player3.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS) == threeVictory);
        assertTrue(player4.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS) == fourVictory);
    }

    @Test
    public void testHandleEndAction() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("1", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("2", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("3", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("4", PlayerColor.YELLOW, new PersonalBoard(6));

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

        controller.setCardsOnTowers();

        controller.getActionChecker().setCurrentPlayer();

        model.setCurrentPlayer(player1);

        player1.getCouncilPrivilegeEffectList().add(new CouncilPrivilegeEffect(1));
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);

        player1.getCurrentBonusActionList().add(new ActionEffect(ZoneType.GREENTOWER, 3));

        controller.handleEndAction();

        GetCouncilPrivileges query = new GetCouncilPrivileges();

        query.perform(model);

        assertTrue(player1.getPlayerState() == PlayerState.CHOOSE_COUNCIL_PRIVILEGE);

        System.out.println(player1.getSpecialBonusAndMaluses());

        new PrivilegeChosen(integers).perform(model, controller);

        assertTrue(player1.getPlayerState() == PlayerState.BONUSACTION);

        model.setCurrentPlayer(player2);

        controller.getActionChecker().setCurrentPlayer();

        controller.handleEndAction();

        assertTrue(player2.getPlayerState() == PlayerState.ENDTURN);
    }

}