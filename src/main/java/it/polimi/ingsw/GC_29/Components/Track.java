package it.polimi.ingsw.GC_29.Components;

import java.util.HashMap;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Track implements Cleanable {
    private PawnSlot[] track;
    private int maxNumberOfPawns;

    /*
    * This HashMap is good to keep track of all the pawns
    * that are in track (PawnSlot[]).
    * It contains the pawn as key and the position of the array
    * as value.
     */
    private HashMap<Pawn, Integer> pawnMap;

    public Track(int maxNumberOfPawns) {
        this.maxNumberOfPawns = maxNumberOfPawns;
        this.track = new PawnSlot[maxNumberOfPawns];
        this.pawnMap = new HashMap<Pawn, Integer>();
    }

    public int findPawn(Pawn pawn) {
        return pawnMap.get(pawn);
    }

    public void movePawn(int position, Pawn pawn) {
        int index = findPawn(pawn);
        PawnSlot pawnSlot = track[index];
        Pawn realPawn = pawnSlot.findPawn(pawn);
        track[index].removePawn(realPawn);
        track[position].addPawn(realPawn);
    }

    @Override
    public void clean() {
        for (PawnSlot pawnSlot : track) {
            pawnSlot.clearSlot();
        }
    }

    public PawnSlot[] getTrack() {
        return track;
    }
}
