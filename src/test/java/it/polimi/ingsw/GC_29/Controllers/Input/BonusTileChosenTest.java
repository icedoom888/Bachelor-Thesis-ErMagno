package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.PersonalBoard;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 07/07/17.
 */
public class BonusTileChosenTest {
    @Test
    public void testPerform() throws Exception {

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

        controller.setCurrentBonusTileIndexPlayer(3);
        model.setCurrentPlayer(model.getTurnOrder().get(model.getTurnOrder().size() - 1));
        model.getCurrentPlayer().setPlayerState(PlayerState.CHOOSE_BONUS_TILE);
        for (int i = model.getTurnOrder().size() - 1; i >= 0; i--) {

            controller.startTimer(model.getTurnOrder().get(i));
            new BonusTileChosen(1).perform(model, controller);

            if (i!=0) {
                assertTrue(model.getTurnOrder().get(i).getPlayerState() == PlayerState.WAITING);

            }
            else {
                assertTrue(model.getTurnOrder().get(i).getPlayerState() == PlayerState.THROWDICES);

            }
        }



    }

}