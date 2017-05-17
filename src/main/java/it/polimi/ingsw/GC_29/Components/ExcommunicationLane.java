package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ExcommunicationLane implements Cleanable {
    private ExcommunicationTile tileEndTurnTwo;
    private ExcommunicationTile tileEndTurnFour;
    private ExcommunicationTile tileEndTurnSix;
    private PawnSlot[] excommunicatedPlayers;

    @Override
    public void clean() {

    }

    public void setTileEndTurnTwo(ExcommunicationTile tileEndTurnTwo) {
        this.tileEndTurnTwo = tileEndTurnTwo;
    }

    public void setTileEndTurnFour(ExcommunicationTile tileEndTurnFour) {
        this.tileEndTurnFour = tileEndTurnFour;
    }

    public void setTileEndTurnSix(ExcommunicationTile tileEndTurnSix) {
        this.tileEndTurnSix = tileEndTurnSix;
    }

    public ExcommunicationTile getTileEndTurnTwo() {
        return tileEndTurnTwo;
    }

    public ExcommunicationTile getTileEndTurnFour() {
        return tileEndTurnFour;
    }

    public ExcommunicationTile getTileEndTurnSix() {
        return tileEndTurnSix;
    }

    public PawnSlot[] getExcommunicatedPlayers() {
        return excommunicatedPlayers;
    }
}
