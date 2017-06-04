package it.polimi.ingsw.GC_29.Components;

import java.util.Arrays;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class FaithPointsTrack extends Track {

    private int[] victoryPointsPerSlot;

    public FaithPointsTrack(int maxNumberOfPawns, int trackLenght, int[] victoryPointsPerSlot) {
        super(maxNumberOfPawns, trackLenght);
        this.victoryPointsPerSlot = victoryPointsPerSlot;
    }

    public int[] getVictoryPointsPerSlot() {

        return victoryPointsPerSlot;
    }

    @Override
    public String toString() {
        return "FaithPointsTrack{" +
                "victoryPointsPerSlot=" + Arrays.toString(victoryPointsPerSlot) +
                ", track=" + Arrays.toString(track) +
                ", trackLength=" + trackLength +
                ", pawnMap=" + pawnMap +
                '}';
    }
}


