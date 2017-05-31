package it.polimi.ingsw.GC_29.Components;

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

}
