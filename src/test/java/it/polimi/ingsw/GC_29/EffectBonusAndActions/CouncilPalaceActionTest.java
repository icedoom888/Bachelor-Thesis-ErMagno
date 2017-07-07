package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.Model;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 07/07/2017.
 */
public class CouncilPalaceActionTest {

    @Test
    public void testExecute() throws Exception {

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



        FamilyPawn familyPawn1 = new FamilyPawn(player1.getFamilyPawn(FamilyPawnType.NEUTRAL));

        System.out.println(player1.getActualGoodSet());

        actionList.get(20).setFamiliyPawn(familyPawn1);
        actionList.get(20).setPlayer(player1);
        assertTrue(actionList.get(20).isPossible());
        actionList.get(20).isPossible();
        System.out.println(actionList.get(20).getWorkers());
        actionList.get(20).execute();

        GoodSet expectedGoodsetPlayer1 = new GoodSet(2,2,3,1,2,2,2);
        System.out.println(player1.getActualGoodSet());
        boolean verify = player1.getActualGoodSet().equals(expectedGoodsetPlayer1);
        assertTrue(verify);

        CouncilPalaceActionSpace councilPalaceActionSpace = (CouncilPalaceActionSpace) actionList.get(20).getActionSpaceSelected();

        PlayerColor[] order = councilPalaceActionSpace.getTurnOrder();
        assertTrue( order[0] == PlayerColor.BLUE);

        assertFalse(player1.getFamilyPawnAvailability().get(FamilyPawnType.NEUTRAL));


        FamilyPawn familyPawn2 = new FamilyPawn(player2.getFamilyPawn(FamilyPawnType.NEUTRAL));

        player2.updateGoodSet(new GoodSet(-2,-2,-2,-2,-2,-2,-2));

        actionList.get(20).setFamiliyPawn(familyPawn2);
        actionList.get(20).setPlayer(player2);
        assertFalse(actionList.get(20).isPossible());
        actionList.get(20).isPossible();
        assertTrue(player2.getFamilyPawnAvailability().get(FamilyPawnType.NEUTRAL));

        player2.updateGoodSet(new GoodSet(0,0,0,1,0,0,0));

        actionList.get(20).setFamiliyPawn(familyPawn2);
        actionList.get(20).setPlayer(player2);
        assertTrue(actionList.get(20).isPossible());
        actionList.get(20).isPossible();
        actionList.get(20).execute();

        GoodSet expectedGoodsetPlayer2 = new GoodSet(0,0,1,0,0,0,0);
        System.out.println(player2.getActualGoodSet());
        verify = player2.getActualGoodSet().equals(expectedGoodsetPlayer2);
        assertTrue(verify);

        order = councilPalaceActionSpace.getTurnOrder();
        assertTrue( order[0] == PlayerColor.BLUE);
        assertTrue(order[1]==PlayerColor.GREEN);

        assertFalse(player2.getFamilyPawnAvailability().get(FamilyPawnType.NEUTRAL));

    }

}