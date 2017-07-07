package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 19/06/17.
 */
public class DevelopmentCardTest {
    @Test
    public void testToString() throws Exception {
        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);

        GameSetup gameSetup = new GameSetup(players);

        gameSetup.init();

        gameSetup.setExcommunicationTiles();

        gameSetup.setGoodsForPlayers();

        gameSetup.setLeaderCards();

        Model model = gameSetup.getModel();

        Controller controller = null;
        try {
            controller = new Controller(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setNewRound();

        System.out.println(model.getGameBoard().getVictoryPointsTrack().toTable());
        System.out.println(model.getGameBoard().getFaithPointsTrack().toTable());

        Tower tower = model.getGameBoard().getTower(CardColor.YELLOW);
        for (Floor floor : tower.getFloors()) {
            System.out.println(floor.getDevelopmentCard().toTable());
        }

        System.out.println(model.getGameBoard().toTable());

        System.out.println(model.getGameBoard().getTower(ZoneType.BLUETOWER).getFloor(0).getDevelopmentCard().toTable());
    }

}