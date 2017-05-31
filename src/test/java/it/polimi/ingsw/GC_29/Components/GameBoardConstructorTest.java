package it.polimi.ingsw.GC_29.Components;

import org.testng.annotations.Test;

/**
 * Created by AlbertoPennino on 21/05/2017.
 */
public class GameBoardConstructorTest {

    @Test
    public void testGameBoard() throws Exception{
        int numberOfPlayers_1 = 4;
        int numberOfPlayers_2 = 3;
        int numberOfPlayers_3 = 2;
        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST,"sei",null,null,null,"777");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.SECOND,"un",null,null,null,"su ogni");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,null,"cosa");



        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        System.out.println("gameBoard_1 created");

        GameBoard gameBoard_2 = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        System.out.println("gameBoard_2 created");

        GameBoard gameBoard_3 = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        System.out.println("gameBoard_3 created");
    }
}
