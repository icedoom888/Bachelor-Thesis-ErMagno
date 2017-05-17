package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Track implements Cleanable {
    private PawnSlot[] track;

    public void movePawn(int position, Pawn pawn) {
    }

    @Override
    public void clean() {

    }

    public PawnSlot[] getTrack() {
        return track;
    }
}
