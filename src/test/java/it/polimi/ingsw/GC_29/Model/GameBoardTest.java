package it.polimi.ingsw.GC_29.Model;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by AlbertoPennino on 09/07/2017.
 */
public class GameBoardTest {

    @Test
    public void testCostruttore() throws Exception {

        FaithPointsTrack track = new FaithPointsTrack(4,20,null);
        GameBoard gameBoard = new GameBoard(4,track);


        assertTrue(gameBoard.getTower(CardColor.BLUE)!=null);
        assertTrue(gameBoard.getTower(ZoneType.BLUETOWER)!=null);
        assertTrue(gameBoard.getCouncilPalace()!=null);
        assertTrue(gameBoard.getFaithPointsTrack()!=null);
        gameBoard.clearAll();
        gameBoard.clearRound();
        assertTrue(gameBoard.getDiceLane()!=null);
        assertTrue(gameBoard.getExcommunicationLane()!=null);
        assertTrue(gameBoard.getMarket()!=null);
        assertTrue(gameBoard.getHarvestArea()!=null);
        assertTrue(gameBoard.getProductionArea()!=null);
        assertTrue(gameBoard.getTowerMap()!=null);
        assertTrue(gameBoard.getVenturesPointsTrack()!=null);
        assertTrue(gameBoard.getWorkArea(ZoneType.PRODUCTION)!=null);
        assertTrue(gameBoard.getWorkArea(ZoneType.HARVEST)!=null);
        assertTrue(gameBoard.getDice(FamilyPawnType.BLACK)!=null);
        //gameBoard.toString();
        //System.out.println(gameBoard.toTable());

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

        System.out.println(model.getGameBoard().toTable());
    }

}