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

        for (int i = 0; i < maxNumberOfPawns ; i++) {
            track[i] = new PawnSlot(maxNumberOfPawns, true);
        }

        this.pawnMap = new HashMap<Pawn, Integer>();
    }

    public int findPawn(Pawn pawn) {
        return pawnMap.get(pawn);
    }

    /**
    * movePawn search the position of the pawn in the HashMap,
    * then search the pawn in the right PawnSlot.
    * If the Pawn is really there, it changes its position from the index
    * provided by pawnMap to the new given position, if not it prints
    * that there has been an error
     */
    public void movePawn(int position, Pawn pawn) {
        // TODO: testing
        int index = findPawn(pawn);
        PawnSlot pawnSlot = track[index];
        Pawn realPawn = pawnSlot.findPawn(pawn);

        if (realPawn != null) {
            track[index].removePawn(realPawn);
            track[position].addPawn(realPawn);
            pawnMap.put(realPawn,position);

        } else {
            System.out.println("Error: Wrong Pawn");
        }
    }

    @Override
    public void clean() {
        for (PawnSlot pawnSlot : track) {
            pawnSlot.clearSlot();
            pawnMap.clear();
        }


    }

    public PawnSlot[] getTrack() {
        return track;
    }
}
