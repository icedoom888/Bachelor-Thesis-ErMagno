package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class FaithPointsPawnSlot extends PawnSlot {

    private int victoryPoints;

    public FaithPointsPawnSlot(int maxNumberOfPawns, boolean free, int victoryPoints) {
        super(maxNumberOfPawns, free);
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints() {

        return victoryPoints;
    }


}
