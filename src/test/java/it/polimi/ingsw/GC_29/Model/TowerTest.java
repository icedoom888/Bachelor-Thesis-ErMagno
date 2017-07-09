package it.polimi.ingsw.GC_29.Model;


import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 19/06/17.
 */
public class TowerTest {
    @Test
    public void testClean() throws Exception {

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

        Tower tower0 = model.getGameBoard().getTower(ZoneType.GREENTOWER);
        Tower tower1 = model.getGameBoard().getTower(ZoneType.YELLOWTOWER);
        Tower tower2 = model.getGameBoard().getTower(ZoneType.BLUETOWER);
        Tower tower3 = model.getGameBoard().getTower(ZoneType.PURPLETOWER);

        System.out.println(tower0.toString());
        System.out.println(tower0.toTable());

        tower0.clean();
        tower1.clean();
        tower2.clean();
        tower3.clean();


        for (Floor floor : tower0.getFloors()) {
            assertTrue(!floor.getActionSpace().isOccupied());
            assertTrue(floor.getDevelopmentCard() == null);
        }

        for (Floor floor : tower1.getFloors()) {
            assertTrue(!floor.getActionSpace().isOccupied());
            assertTrue(floor.getDevelopmentCard() == null);
        }

        for (Floor floor : tower2.getFloors()) {
            assertTrue(!floor.getActionSpace().isOccupied());
            assertTrue(floor.getDevelopmentCard() == null);
        }

        for (Floor floor : tower3.getFloors()) {
            assertTrue(!floor.getActionSpace().isOccupied());
            assertTrue(floor.getDevelopmentCard() == null);
        }



    }

}