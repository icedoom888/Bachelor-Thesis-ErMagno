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

    public void addPawn(Pawn pawn) {
    }

    public void removePawn(Pawn pawn) {
    }

    public boolean isFree() {
        return false;
    }
}
