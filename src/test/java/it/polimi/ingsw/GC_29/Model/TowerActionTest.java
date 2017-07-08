package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 08/07/2017.
 */
public class TowerActionTest {

    @Test
    public void testIsPossible() throws Exception {
        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("d", PlayerColor.RED, new PersonalBoard(6));


        players.add(player1);
        players.add(player2);
        players.add(player3);

        GameSetup gameSetup = new GameSetup(players);

        gameSetup.init();

        gameSetup.setExcommunicationTiles();

        for (Player player : players){
            player.updateGoodSet(new GoodSet(2,2,2,2,2,2,2));
        }

        gameSetup.setLeaderCards();

        Model model = gameSetup.getModel();

        Controller controller = null;
        try {
            controller = new Controller(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setCardsOnTowers();

        controller.getActionChecker().setCurrentPlayer();

        ArrayList<Action> actionList = controller.getActionChecker().getActionList();

        player1.updateGoodSet(new GoodSet(10,10,10,10,10,10,10));
        player2.updateGoodSet(new GoodSet(10,10,10,10,10,10,10));


        //first action possible
        FamilyPawn familyPawn1 = new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.BLACK));
        familyPawn1.setActualValue(5);
        DevelopmentCard card = gameSetup.getModel().getGameBoard().getTower(CardColor.GREEN).getFloor(0).getDevelopmentCard();
        System.out.println(card);
        actionList.get(0).setFamiliyPawn(familyPawn1);
        actionList.get(0).setPlayer(player1);
        assertTrue(actionList.get(0).isPossible());
        actionList.get(0).isPossible();
        actionList.get(0).execute();

        //Second action not possible cause the tower is occupied
        FamilyPawn familyPawn2 = new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.ORANGE));
        familyPawn2.setActualValue(1);
        card = gameSetup.getModel().getGameBoard().getTower(CardColor.GREEN).getFloor(1).getDevelopmentCard();
        System.out.println(card);
        actionList.get(1).setFamiliyPawn(familyPawn2);
        actionList.get(1).setPlayer(player1);
        assertFalse(actionList.get(1).isPossible());

        FamilyPawn familyPawn3 = new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.NEUTRAL));
        familyPawn3.setActualValue(2);
        card = gameSetup.getModel().getGameBoard().getTower(CardColor.GREEN).getFloor(2).getDevelopmentCard();
        System.out.println(card);
        actionList.get(2).setFamiliyPawn(familyPawn3);
        actionList.get(2).setPlayer(player1);
        assertTrue(actionList.get(2).isPossible());
        actionList.get(2).execute();

        FamilyPawn familyPawn4 = new FamilyPawn(player2.getFamilyPawn(FamilyPawnType.BLACK));
        familyPawn4.setActualValue(2);
        card = gameSetup.getModel().getGameBoard().getTower(CardColor.GREEN).getFloor(1).getDevelopmentCard();
        actionList.get(1).setFamiliyPawn(familyPawn4);
        actionList.get(1).setPlayer(player2);
        assertTrue(actionList.get(1).isPossible());
        actionList.get(1).isPossible();
        actionList.get(1).execute();





        /*FamilyPawn familyPawn3 = new FamilyPawn(player2.getFamilyPawn(FamilyPawnType.BLACK));
        familyPawn1.setActualValue(5);
        actionList.get(2).setFamiliyPawn(familyPawn2);
        actionList.get(2).setPlayer(player2);
        assertFalse(actionList.get(2).isPossible());
        actionList.get(3).setFamiliyPawn(familyPawn2);
        actionList.get(3).setPlayer(player2);
        assertFalse(actionList.get(3).isPossible());
        actionList.get(0).setFamiliyPawn(familyPawn2);
        actionList.get(0).setPlayer(player2);
        assertTrue(actionList.get(0).isPossible());
        */

    }

    @Test
    public void testUpdate() throws Exception {
    }

    @Test
    public void testReset() throws Exception {
    }

}