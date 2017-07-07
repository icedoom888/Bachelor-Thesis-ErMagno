package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 19/06/17.
 */
public class TowerTest {
    @Test
    public void testToString() throws Exception {

        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));
        //Player player3 = new Player("d", PlayerColor.YELLOW, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);

        GameSetup gameSetup = new GameSetup(players);

        gameSetup.init();

        Model model = gameSetup.getModel();

        Controller controller = null;
        try {
            controller = new Controller(gameSetup.getModel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setNewRound();


    }

}