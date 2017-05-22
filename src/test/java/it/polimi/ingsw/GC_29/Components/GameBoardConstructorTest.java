package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.BonusAndMalusOnAction;
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
        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRSTERA,"sei",null,null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECONDERA,"un",null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRDERA,"bufu",null,null,"cosa");



        GameBoard gameBoard_1 = new GameBoard(numberOfPlayers_1,tile_1,tile_2,tile_3);
        System.out.println("gameBoard_1 created");
        GameBoard gameBoard_2 = new GameBoard(numberOfPlayers_2,tile_1,tile_2,tile_3);
        System.out.println("gameBoard_2 created");
        GameBoard gameBoard_3 = new GameBoard(numberOfPlayers_3,tile_1,tile_2,tile_3);
        System.out.println("gameBoard_3 created");
    }
}
