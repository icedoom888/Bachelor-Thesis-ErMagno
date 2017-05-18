package it.polimi.ingsw.GC_29.Components;

/**
 * Created by AlbertoPennino on 18/05/2017.
 */
public class TerritoryLane extends Lane {
    private ComplexSlot[] slots;

    public TerritoryLane(){
        cards = new DevelopmentCard[6];
        slots = new ComplexSlot[6];
        slots[0]= new ComplexSlot(0,0);
        slots[1]= new ComplexSlot(0,0);
        slots[2]= new ComplexSlot(1,3);
        slots[3]= new ComplexSlot(4,7);
        slots[4]= new ComplexSlot(10,12);
        slots[5]= new ComplexSlot(20,18);
        numberOfCardsPresent = 0;
    }

    public ComplexSlot getSlot(int position){
        return slots[position];
    }

}
