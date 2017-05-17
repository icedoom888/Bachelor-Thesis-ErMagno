package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Tower implements Cleanable {
    private Floor[] floors;
    private CardColor cardType;
    private boolean occupied;
    private int goldCostIfOccupied;

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public void clean() {

    }

    public Floor[] getFloors() {
        return floors;
    }

    public CardColor getCardType() {
        return cardType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getGoldCostIfOccupied() {
        return goldCostIfOccupied;
    }

    public Floor visitFloor(int floor) {
        return null;
    }
}
