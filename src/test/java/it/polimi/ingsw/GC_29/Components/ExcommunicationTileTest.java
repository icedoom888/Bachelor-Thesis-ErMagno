package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class ExcommunicationTileTest {
    @Test
    public void testExecute() throws Exception{
        ArrayList<Player> players = new ArrayList<>();

        Player player1 = new Player("l", PlayerColor.BLUE, new PersonalBoard(6));
        Player player2 = new Player("e", PlayerColor.GREEN, new PersonalBoard(6));

        players.add(player1);
        players.add(player2);

        GameSetup gameSetup = new GameSetup(players);

        gameSetup.init();
        gameSetup.setExcommunicationTiles();

        ExcommunicationTile excommunicationTile1 = gameSetup.getModel().getGameBoard().getExcommunicationLane().getExcommunicationTile(Era.FIRST);
        excommunicationTile1.execute(player1);

        int bonusOnAction = player1.getBonusAndMalusOnAction().size();
        int bonusOnCost = player1.getBonusAndMalusOnCost().size();
        int bonusOnGoods = player1.getBonusAndMalusOnGoods().size();

        if (excommunicationTile1.getMalusOnAction()!= null){
            assertTrue(bonusOnAction == 1);
            assertFalse(bonusOnCost == 1);
        }

        if (excommunicationTile1.getMalusOnCost()!=null){
            assertTrue(bonusOnCost == 1);
            assertFalse(bonusOnGoods == 1);

        }

        if (excommunicationTile1.getMalusOnGoods()!=null){
            assertTrue(bonusOnGoods == 1);
            assertFalse(bonusOnCost == 1);

        }

    }
}
