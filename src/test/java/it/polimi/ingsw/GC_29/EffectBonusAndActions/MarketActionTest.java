package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Model.*;
import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by AlbertoPennino on 07/07/2017.
 */
public class MarketActionTest {
    @Test
    public void testExecute() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));
        Player player3 = new Player("d", PlayerColor.RED, new PersonalBoard(6));
        Player player4 = new Player("o", PlayerColor.YELLOW, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

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
        FamilyPawn familyPawn2 = new FamilyPawn(player2.getFamilyPawn(FamilyPawnType.BLACK));
        familyPawn2.setActualValue(1);
        FamilyPawn familyPawn3 = new FamilyPawn(player2.getFamilyPawn(FamilyPawnType.ORANGE));
        familyPawn3.setActualValue(1);
        FamilyPawn familyPawn4 = new FamilyPawn(player2.getFamilyPawn(FamilyPawnType.NEUTRAL));


        actionList.get(16).setPlayer(player1);
        actionList.get(16).setFamiliyPawn(familyPawn1);
        assertTrue(actionList.get(16).isPossible());
        actionList.get(16).isPossible();
        actionList.get(16).execute();

        GoodSet goodSetExpected = new GoodSet(2,2,7,1,2,2,2);
        assertTrue(player1.getActualGoodSet().equals(goodSetExpected));
        assertFalse(player1.getFamilyPawnAvailability().get(FamilyPawnType.NEUTRAL));
        assertTrue(actionList.get(16).getActionSpaceSelected().isOccupied());

        actionList.get(16).setPlayer(player2);
        actionList.get(16).setFamiliyPawn(familyPawn2);
        assertFalse(actionList.get(16).isPossible());

        actionList.get(17).setPlayer(player2);
        actionList.get(17).setFamiliyPawn(familyPawn2);
        assertTrue(actionList.get(17).isPossible());
        actionList.get(17).isPossible();
        actionList.get(17).execute();
        goodSetExpected = new GoodSet(2,2,2,7,2,2,2);
        assertTrue(player2.getActualGoodSet().equals(goodSetExpected));
        assertFalse(player2.getFamilyPawnAvailability().get(FamilyPawnType.BLACK));
        assertTrue(actionList.get(17).getActionSpaceSelected().isOccupied());

        actionList.get(18).setPlayer(player2);
        actionList.get(18).setFamiliyPawn(familyPawn4);
        assertTrue(actionList.get(18).isPossible());
        actionList.get(18).isPossible();
        actionList.get(18).execute();

        goodSetExpected = new GoodSet(2,2,4,6,2,5,2);
        assertTrue(player2.getActualGoodSet().equals(goodSetExpected));


        actionList.get(19).setPlayer(player2);
        actionList.get(19).setFamiliyPawn(familyPawn3);
        assertTrue(actionList.get(19).isPossible());
        actionList.get(19).isPossible();
        actionList.get(19).execute();

        goodSetExpected = new GoodSet(2,2,4,6,2,5,2);
        System.out.println(player2.getActualGoodSet());
        assertTrue(player2.getActualGoodSet().equals(goodSetExpected));
        assertTrue(player2.getCouncilPrivilegeEffectList().size()==1);
        System.out.println(player2.getCouncilPrivilegeEffectList().get(0));
        assertTrue(player2.getCouncilPrivilegeEffectList().get(0).getNumberOfCouncilPrivileges()==1);

    }
}
