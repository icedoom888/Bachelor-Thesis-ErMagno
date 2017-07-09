package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 09/07/17.
 */
public class CouncilPalaceActionSpaceTest {
    @Test
    public void testClean() throws Exception {

        CouncilPalaceActionSpace councilPalaceActionSpace = new CouncilPalaceActionSpace(3);

        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 4));
        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.YELLOW, FamilyPawnType.BLACK, 4));
        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.RED, FamilyPawnType.BLACK, 4));

        councilPalaceActionSpace.clean();

        for (int i = 0; i < councilPalaceActionSpace.getTurnOrder().length; i++) {

            assertTrue(councilPalaceActionSpace.getTurnOrder()[i] == null);
        }

    }

    @Test
    public void testAddPawn() throws Exception {
        CouncilPalaceActionSpace councilPalaceActionSpace = new CouncilPalaceActionSpace(3);

        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 4));
        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 4));

        assertTrue(councilPalaceActionSpace.getTurnOrder()[0] == PlayerColor.BLUE);
        assertTrue(councilPalaceActionSpace.getTurnOrder()[1] == null);
    }

    @Test
    public void testRemovePawn() throws Exception {

        CouncilPalaceActionSpace councilPalaceActionSpace = new CouncilPalaceActionSpace(3);

        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 4));
        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.YELLOW, FamilyPawnType.BLACK, 4));
        councilPalaceActionSpace.addPawn(new FamilyPawn(PlayerColor.RED, FamilyPawnType.BLACK, 4));


        councilPalaceActionSpace.removePawn(new FamilyPawn(PlayerColor.YELLOW, FamilyPawnType.BLACK, 4));

        assertTrue(councilPalaceActionSpace.getTurnOrder()[1] == null);

    }



}