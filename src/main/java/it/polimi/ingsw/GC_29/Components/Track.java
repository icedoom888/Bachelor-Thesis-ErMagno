package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Track implements Cleanable {
    protected PawnSlot[] track;
    protected int maxNumberOfPawns;
    protected int trackLength;

    /*
    * This HashMap is good to keep track of all the pawns
    * that are in track (PawnSlot[]).
    * It contains the pawn as key and the position of the array
    * as value.
     */
    protected HashMap<Pawn, Integer> pawnMap;

    public Track(int maxNumberOfPawns, int trackLength) {
        this.maxNumberOfPawns = maxNumberOfPawns;
        this.trackLength = trackLength;
        this.track = new PawnSlot[trackLength];

        for (int i = 0; i < trackLength; i++) {
            track[i] = new PawnSlot(maxNumberOfPawns, true);
        }

        this.pawnMap = new HashMap<>();
    }

    public PawnSlot[] getTrack() {

        return track;
    }

    public PawnSlot getPawnSlot(int index) {
        return track[index];
    }

    public int findPawn(Pawn pawn) {

        return pawnMap.get(pawn);
    }


    public HashMap<Pawn, Integer> getPawnMap() {
        return pawnMap;
    }


    /**
     * movePawn search the position of the pawn in the HashMap,
     * then search the pawn in the right PawnSlot.
     * If the Pawn is really there, it changes its position from the index
     * provided by pawnMap to the new given position, if not it prints
     * that there has been an error
     * @param increment int where to move the pawn
     * @param pawn  pawn to move
     */
    public void movePawn(int increment, Pawn pawn) {

        int index = findPawn(pawn);
        PawnSlot pawnSlot = track[index];
        Pawn realPawn = pawnSlot.findPawn(pawn);

        if (realPawn != null) {
            track[index].removePawn(realPawn);
            track[index + increment].addPawn(realPawn);
            pawnMap.put(realPawn,index + increment);

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

    public void startTrack(ArrayList<Pawn> pawns) {
        for (Pawn pawn : pawns) {
            track[0].addPawn(pawn);
            pawnMap.put(pawn,0);
        }
    }

    @Override
    public String toString() {
        return "Track{" +
                "track=" + Arrays.toString(track) +
                ", maxNumberOfPawns=" + maxNumberOfPawns +
                ", trackLength=" + trackLength +
                ", pawnMap=" + pawnMap +
                '}';
    }
}
