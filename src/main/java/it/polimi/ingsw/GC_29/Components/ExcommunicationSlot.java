package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 31/05/17.
 */
public class ExcommunicationSlot extends PawnSlot {

    private ExcommunicationTile excommunicationTile;

    public ExcommunicationSlot(int maxNumberOfPawns, boolean free, ExcommunicationTile excommunicationTile) {
        super(maxNumberOfPawns, free);
        this.excommunicationTile = excommunicationTile;
    }

    public ExcommunicationTile getExcommunicationTile() {
        return excommunicationTile;
    }

    @Override
    public String toString() {
        return "ExcommunicationSlot{" + "excommunicationTile=" + excommunicationTile + ", playerPawns=" + playerPawns + '}';
    }
}
