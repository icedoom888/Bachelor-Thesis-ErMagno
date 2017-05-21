package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Tower implements Cleanable {
    private Floor[] floors;
    private CardColor cardType;
    private boolean occupied;
    private int CostIfOccupied;

    public Tower(CardColor color){
        this.floors = new Floor[4];
        floors[0] = new Floor(new ObtainEffect(0,0,0,0,0,0,0),1);
        floors[1] = new Floor(new ObtainEffect(0,0,0,0,0,0,0),3);
        if(color==CardColor.GREEN){
            floors[2] = new Floor(new ObtainEffect(1,0,0,0,0,0,0),5);
            floors[3] = new Floor(new ObtainEffect(2,0,0,0,0,0,0),7);
        }
        if(color==CardColor.BLUE){
            floors[2] = new Floor(new ObtainEffect(0,1,0,0,0,0,0),5);
            floors[3] = new Floor(new ObtainEffect(0,2,0,0,0,0,0),7);
        }
        if(color==CardColor.YELLOW){
            floors[2] = new Floor(new ObtainEffect(0,0,0,0,0,1,0),5);
            floors[3] = new Floor(new ObtainEffect(0,0,0,0,0,2,0),7);
        }
        if(color==CardColor.PURPLE){
            floors[2] = new Floor(new ObtainEffect(0,0,1,0,0,0,0),5);
            floors[3] = new Floor(new ObtainEffect(0,0,2,0,0,0,0),7);
        }

        this.cardType = color;
        this.occupied = false;
        this.CostIfOccupied = 3;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public void clean() {

    }

    public Floor[] getFloors() {
        return floors;
    }

    public Floor getFloor(int index) {
        return floors[index];
    }

    public CardColor getCardType() {
        return cardType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getCostIfOccupied() {
        return CostIfOccupied;
    }

    public Floor visitFloor(int floor) {
        return null;
    }
}
