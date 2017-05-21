package it.polimi.ingsw.GC_29.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Tower<T extends DevelopmentCard> implements Cleanable {
    private List<Floor<T>> floors = new ArrayList<Floor<T>>();
    //private Floor[] floors;
    private CardColor cardType;
    private boolean occupied;
    private int goldCostIfOccupied;

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public void clean() {

    }

    public List<Floor<T>> getFloors() {
        return floors;
    }

    public Floor<T> getFloor(int index) {
        return floors.get(index);
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
