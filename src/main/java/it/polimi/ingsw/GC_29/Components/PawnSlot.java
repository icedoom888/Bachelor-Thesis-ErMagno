package it.polimi.ingsw.GC_29.Components;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class PawnSlot {
    private ArrayList<Pawn> playerPawns;

    /*
    * this field needs to know how many Pawns can stay inside the slot
    * in case of B&M that let the player put the Pawn in slots already occupied the controller will not use this attribute
     */
    private int maxNumberOfPawns;

    private boolean free;

    public PawnSlot(int maxNumberOfPawns, boolean free) {
        this.playerPawns = new ArrayList<Pawn>(); // correct use of arrayList, we need to be capable of altering the length of the list
        this.maxNumberOfPawns = maxNumberOfPawns;
        this.free = free;
    }


    public ArrayList<Pawn> getPlayerPawns() {
        return playerPawns;
    }

    public int getMaxNumberOfPawns() {
        return maxNumberOfPawns;
    }

    public void addPawn(Pawn pawn) {
        playerPawns.add(pawn);
    }

    public void removePawn(Pawn pawn) {
        playerPawns.remove(pawn);
    }

    public Pawn findPawn(Pawn pawn) {

        if (playerPawns.contains(pawn)) {
            int index = playerPawns.indexOf(pawn);
            return playerPawns.get(index);
        }
        else return null;
    }



    public void clearSlot(){
        playerPawns.clear(); // more efficient vs removeAll() method. Clear() is O(n), removeAll is O(n^2)
        free = true;
    }



    public boolean isFree() {
        return false;
    }
}
