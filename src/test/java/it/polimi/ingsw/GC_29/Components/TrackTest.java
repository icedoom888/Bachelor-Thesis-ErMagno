package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;
import org.testng.annotations.Test;

import java.util.ArrayList;


/**
 * Created by Lorenzotara on 20/05/17.
 */
public class TrackTest {

    @Test
    public void testMovePawn() throws Exception {

        Track track = new Track(4, 10);

        Pawn redPawn = new Pawn(PlayerColor.RED);
        Pawn bluePawn = new Pawn(PlayerColor.BLUE);

        ArrayList<Pawn> pawns = new ArrayList<>();
        pawns.add(redPawn);
        pawns.add(bluePawn);

        track.startTrack(pawns);

        track.getTrack()[5].addPawn(redPawn);

        System.out.println(track.getTrack()[5]);

        track.movePawn(4,redPawn);
        track.movePawn(4,bluePawn);

        System.out.println(track);
    }

}