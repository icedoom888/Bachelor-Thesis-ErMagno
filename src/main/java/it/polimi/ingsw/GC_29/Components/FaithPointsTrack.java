package it.polimi.ingsw.GC_29.Components;

import java.util.HashMap;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class FaithPointsTrack implements Cleanable {
    private FaithPointsPawnSlot[] faithPointsTrack;
    private int numberOfFaithPoints;
    private HashMap<Pawn, Integer> pawnMap;


    public FaithPointsTrack(int numberOfFaithPoints) {
        this.faithPointsTrack = new FaithPointsPawnSlot[numberOfFaithPoints];
    }

    public FaithPointsPawnSlot[] getFaithPointsTrack() {
        return faithPointsTrack;
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
        FaithPointsPawnSlot pawnSlot = faithPointsTrack[index];
        Pawn realPawn = pawnSlot.findPawn(pawn);

        if (realPawn != null) {
            faithPointsTrack[index].removePawn(realPawn);
            faithPointsTrack[position].addPawn(realPawn);
            pawnMap.put(realPawn,position);

        } else {
            System.out.println("Error: Wrong Pawn");
        }
    }

    @Override
    public void clean() {
        for (FaithPointsPawnSlot faithPointsPawnSlot : faithPointsTrack) {
            if(!(faithPointsPawnSlot.isFree())){
                faithPointsPawnSlot.clearSlot();
            }
        }
    }
}
