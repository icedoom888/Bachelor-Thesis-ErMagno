package it.polimi.ingsw.GC_29.Model;

import de.vandermeer.asciitable.AsciiTable;

import java.util.*;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ExcommunicationLane implements Cleanable {

    private EnumMap<Era, ExcommunicationSlot> tileAndPawns;
    private int maxNumberOfPawns;

    public ExcommunicationLane(int maxNumberOfPawns) {

        this.tileAndPawns = new EnumMap<Era, ExcommunicationSlot>(Era.class);
        this.maxNumberOfPawns = maxNumberOfPawns;
    }

    public void setExcommunicationLane(ExcommunicationTile tile_1,
                                       ExcommunicationTile tile_2,
                                       ExcommunicationTile tile_3){

        tileAndPawns.put(Era.FIRST,new ExcommunicationSlot(maxNumberOfPawns,true, tile_1));
        tileAndPawns.put(Era.SECOND,new ExcommunicationSlot(maxNumberOfPawns,true, tile_2));
        tileAndPawns.put(Era.THIRD,new ExcommunicationSlot(maxNumberOfPawns,true, tile_3));
    }


    @Override
    public void clean() {
        for(Era era : Era.values()){
            tileAndPawns.put(era,null);
        }
    }

    public List<Pawn> getExcommunicatedPawns(Era era) {
        return tileAndPawns.get(era).getPlayerPawns();

    }

    public ExcommunicationTile getExcommunicationTile(Era era) {
        return tileAndPawns.get(era).getExcommunicationTile();
    }

    public void addPawn(Era era, Pawn pawn) {
        PawnSlot slot = tileAndPawns.get(era);
        slot.addPawn(pawn);
    }



    @Override
    public String toString() {
        return "ExcommunicationLane{" + "tileAndPawns=" + tileAndPawns +
                ", maxNumberOfPawns=" + maxNumberOfPawns + '}';
    }

    public String toTable() {

        AsciiTable excommunicationTable = new AsciiTable();

        for (Map.Entry<Era, ExcommunicationSlot> eraExcommunicationSlotEntry : tileAndPawns.entrySet()) {
            excommunicationTable.addRule();
            excommunicationTable.addRow("Era: " + eraExcommunicationSlotEntry.getKey(), "Tile: " + eraExcommunicationSlotEntry.getValue().getExcommunicationTile(), "Players: " + eraExcommunicationSlotEntry.getValue().getPlayerPawns());
        }
        excommunicationTable.addRule();

        return "\n\n\n" + "EXCOMMUNICATION LANE \n\n" + excommunicationTable.render();
    }
}