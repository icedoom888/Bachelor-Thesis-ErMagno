package it.polimi.ingsw.GC_29.Components;

import java.util.HashMap;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ExcommunicationLane implements Cleanable {

    private HashMap<ExcommunicationTile, PawnSlot> tileAndPawns;
    private int maxNumberOfPawns;

    public ExcommunicationLane(int maxNumberOfPawns) {

        this.tileAndPawns = new HashMap<>(3);
        this.maxNumberOfPawns = maxNumberOfPawns;
    }

    public void setExcommunicationLane(ExcommunicationTile tile1,
                                       ExcommunicationTile tile2,
                                       ExcommunicationTile tile3){

        tileAndPawns.put(tile1,new PawnSlot(maxNumberOfPawns,true));
        tileAndPawns.put(tile2,new PawnSlot(maxNumberOfPawns,true));
        tileAndPawns.put(tile3,new PawnSlot(maxNumberOfPawns,true));
    }


    @Override
    public void clean() {

    }

    public PawnSlot getExcommunicatedPawns(ExcommunicationTile excommunicationTile) {
        return tileAndPawns.get(excommunicationTile);
    }


    /**
     * This method sets all the Excommunication Tiles when the game starts
     * The tiles will be the keys of the HashMap, the PawnSlots will be the excommunicated Players
     * for that specific Excommunication Tile

    public void setExcommunicationTiles(ExcommunicationTile excommunicationTile1, ExcommunicationTile excommunicationTile2, ExcommunicationTile excommunicationTile3, int maxNumberOfPawns) {
        tileAndPawns.put(excommunicationTile1, pawnSlots[1]);
        tileAndPawns.put(excommunicationTile2, pawnSlots[2]);
        tileAndPawns.put(excommunicationTile3, pawnSlots[3]);
    }*/

    @Override
    public String toString() {
        return "ExcommunicationLane{" + "tileAndPawns=" + tileAndPawns +
                ", maxNumberOfPawns=" + maxNumberOfPawns + '}';
    }
}