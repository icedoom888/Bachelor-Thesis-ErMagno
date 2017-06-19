package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

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

        GameStatus model = gameSetup.getGameStatus();

        Controller controller = null;
        try {
            controller = new Controller(gameSetup.getGameStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.setNewRound();


    }

}