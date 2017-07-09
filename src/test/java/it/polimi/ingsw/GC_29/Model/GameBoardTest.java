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
    }

}