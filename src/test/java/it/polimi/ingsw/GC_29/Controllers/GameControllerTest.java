package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 01/06/17.
 */
public class GameControllerTest {

    BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

    Player player1 = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6));

    Player player2 = new Player("Alberto", PlayerColor.RED, new PersonalBoard(bonusTile, 6));

    Player player3 = new Player("Christian", PlayerColor.YELLOW, new PersonalBoard(bonusTile, 6));

    Player player4 = new Player("Gianmario", PlayerColor.GREEN, new PersonalBoard(bonusTile, 6));

    ArrayList<Player> players = new ArrayList<>();

    @Test
    public void testEndGame() throws Exception {


        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        GameController testGameController = new GameController();

        player1.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,0,1,1));
        player2.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,2,1,1));
        player3.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,4,1,1));
        player4.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,4,1,1));

        testGameController.endGame();

        GameStatus.getInstance().getTurnOrder().clear();

    }

    @Test
    public void testSetNewTurnOrder() throws Exception {


        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        GameController testGameController = new GameController();

        GameStatus gameStatus = GameStatus.getInstance();

        GameBoard gameBoard = gameStatus.getGameBoard();

        gameBoard.getCouncilPalace().setTurnOrder(player1.getPlayerColor());
        gameBoard.getCouncilPalace().setTurnOrder(player2.getPlayerColor());
        gameBoard.getCouncilPalace().setTurnOrder(player3.getPlayerColor());
        gameBoard.getCouncilPalace().setTurnOrder(player4.getPlayerColor());

        System.out.println("New TurnOrder: " );
        for (int i = 0; i < + gameBoard.getCouncilPalace().getTurnOrder().length; i++) {
            System.out.println(gameBoard.getCouncilPalace().getTurnOrder()[i]);
        }

        System.out.println("Old TurnOrder: " + gameStatus.getTurnOrder());

        testGameController.setNewTurnOrder();

        System.out.println("New TurnOrder: " + gameStatus.getTurnOrder());

        GameStatus.getInstance().getTurnOrder().clear();

    }

}
