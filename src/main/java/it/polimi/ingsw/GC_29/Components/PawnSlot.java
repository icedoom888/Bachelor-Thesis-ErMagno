package it.polimi.ingsw.GC_29.Components;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class PawnSlot {
    private ArrayList<Pawn> playerPawns;
    private int maxNumberOfPawns; // serve per non mettere più pedine del necessario
                                 // nel caso di B&M che fanno mettere pedine in più il controller non guarderà questa variabile
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

    public void clearSlot(){
        playerPawns.clear(); // more efficient vs removeAll() method. Clear() is O(n), removeAll is O(n^2)
        free = true;
    }



    public boolean isFree() {
        return false;
    }
}
