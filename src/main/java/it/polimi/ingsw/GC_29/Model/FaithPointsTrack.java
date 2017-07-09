package it.polimi.ingsw.GC_29.Model;

import de.vandermeer.asciitable.AsciiTable;

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

    @Override
    public String toTable() {
        AsciiTable asciiTable = new AsciiTable();
        for (int i=0; i<track.length; i++) {
            asciiTable.addRule();
            asciiTable.addRow(i, "Victory Points: " + victoryPointsPerSlot[i], track[i].getPlayerPawns());
        }
        asciiTable.addRule();

        return asciiTable.render();
    }
}


