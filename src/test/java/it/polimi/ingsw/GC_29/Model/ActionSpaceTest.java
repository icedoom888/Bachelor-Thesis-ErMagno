package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 09/07/17.
 */
public class ActionSpaceTest {
    @Test
    public void testAddPawn() throws Exception {

        ActionSpace actionSpace = new ActionSpace(new ObtainEffect(new GoodSet()), 4, new PawnSlot(3,true), true, false );
        actionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4));

        assertTrue(actionSpace.isSingle());
        assertTrue(actionSpace.isOccupied());
        assertTrue(!actionSpace.getPawnPlaced().isFree());
        assertTrue(actionSpace.getPawnPlaced().searchFamiliar(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4)));

    }

    @Test
    public void testRemovePawn() throws Exception {

        ActionSpace actionSpace = new ActionSpace(new ObtainEffect(new GoodSet()), 4, new PawnSlot(3,true), true, false );
        actionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4));
        actionSpace.removePawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4));

        assertTrue(actionSpace.isSingle());
        assertTrue(!actionSpace.isOccupied());
        assertTrue(actionSpace.getPawnPlaced().isFree());
        assertTrue(!actionSpace.getPawnPlaced().searchFamiliar(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4)));
    }

    @Test
    public void testClean() throws Exception {
        ActionSpace actionSpace = new ActionSpace(new ObtainEffect(new GoodSet()), 4, new PawnSlot(3,true), true, false );
        actionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4));
        actionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4));
        actionSpace.addPawn(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4));

        actionSpace.clean();
        assertTrue(actionSpace.isSingle());
        assertTrue(!actionSpace.isOccupied());
        assertTrue(actionSpace.getPawnPlaced().isFree());
        assertTrue(!actionSpace.getPawnPlaced().searchFamiliar(new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 4)));
    }

}