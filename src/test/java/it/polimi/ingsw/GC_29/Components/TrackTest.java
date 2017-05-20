package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 20/05/17.
 */
public class TrackTest {
    @Test
    public void testMovePawn() throws Exception {
        System.out.println("ciao");
        Track track = new Track(4, 10);
        System.out.println("track: " + track + "\n");

        Pawn redPawn = new Pawn(PlayerColor.RED);
        Pawn bluePawn = new Pawn(PlayerColor.BLUE);
        System.out.println("redPawn: " + redPawn + "\n");
        System.out.println("bluePawn: " + bluePawn + "\n");

        ArrayList<Pawn> pawns = new ArrayList<Pawn>();
        pawns.add(redPawn);
        pawns.add(bluePawn);

        System.out.println("pawns: " + pawns + "\n");

        track.startTrack(pawns);
        System.out.println("track: " + track + "\n");

        System.out.println("Cerco la redPawn: la sua posizione è " + track.findPawn(redPawn) + "\n");
        System.out.println("find pawn sul PawnSlot: " + track.getTrack()[0].findPawn(redPawn) + "\n");
        track.getTrack()[5].addPawn(redPawn);

        System.out.println(track.getTrack()[5]);

        track.movePawn(5,redPawn);
        track.movePawn(4,bluePawn);

    }

}