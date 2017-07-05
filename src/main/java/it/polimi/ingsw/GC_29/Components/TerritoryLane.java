package it.polimi.ingsw.GC_29.Components;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import javafx.fxml.FXML;

/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class TerritoryLane extends Lane {
    private TerritorySlot[] slots;

    public TerritoryLane(int laneDimension){
        super(laneDimension);
        slots = new TerritorySlot[6];
        slots[0]= new TerritorySlot(0,0);
        slots[1]= new TerritorySlot(0,0);
        slots[2]= new TerritorySlot(1,3);
        slots[3]= new TerritorySlot(4,7);
        slots[4]= new TerritorySlot(10,12);
        slots[5]= new TerritorySlot(20,18);
        firstFreeSlotIndex = 0;
    }

    public TerritorySlot getSlot(int position){
        return slots[position];
    }

    @Override
    public String toTable() {
        AsciiTable laneTable = new AsciiTable();
        int i = 0;
        laneTable.addRule();
        laneTable.addRow("Index","VictoryPoints Given","Military Points Given","Card");
        for (DevelopmentCard developmentCard : cards){
            if (developmentCard!= null) {
                laneTable.addRule();
                laneTable.addRow(i + ")",slots[i].getVictoryPointsGiven(),slots[i].getMilitaryPointsNeeded(),"Card: \n" + developmentCard.toString());
            }
            else {
                laneTable.addRule();
                laneTable.addRow(i + ")",slots[i].getVictoryPointsGiven(),slots[i].getMilitaryPointsNeeded(),"No card");
            }
            i++;
        }
        laneTable.setTextAlignment(TextAlignment.LEFT);
        laneTable.addRule();
        return laneTable.render() + "\n\n\n";
    }
}
