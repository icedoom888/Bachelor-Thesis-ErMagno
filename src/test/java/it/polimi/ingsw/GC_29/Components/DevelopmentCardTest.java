package it.polimi.ingsw.GC_29.Components;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
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

        GameStatus model = gameSetup.getGameStatus();

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