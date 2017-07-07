package it.polimi.ingsw.GC_29.Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by AlbertoPennino on 05/07/2017.
 */
public class PawnSlotTest {

    PawnSlot pawnSlot = new PawnSlot(3,true);
    FamilyPawn pawn1 = new FamilyPawn(PlayerColor.BLUE,FamilyPawnType.ORANGE,4);
    FamilyPawn pawn2 = new FamilyPawn(PlayerColor.BLUE,FamilyPawnType.BLACK,4);
    FamilyPawn pawn3 = new FamilyPawn(PlayerColor.GREEN,FamilyPawnType.BLACK,1);


    @org.testng.annotations.Test
    public void testSearchFamiliar() throws Exception {
        pawnSlot.addPawn(pawn1);
        pawnSlot.addPawn(pawn2);

        assertTrue(pawnSlot.searchFamiliar(pawn1));
        assertFalse(pawnSlot.searchFamiliar(pawn3));
    }
}
