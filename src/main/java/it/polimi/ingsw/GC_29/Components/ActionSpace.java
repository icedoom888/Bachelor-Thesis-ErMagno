package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ActionSpace {
    private Effect effect;
    private int actionCost;
    private PawnSlot pawnPlaced;
    private boolean single;
    private boolean occupied;

    public void addPawn(FamilyPawn pawnPlaced) {
        this.pawnPlaced.addPawn(pawnPlaced);
    }

    public void removePawns(){

    }

    public Effect getEffect() {
        return effect;
    }

    public int getActionCost() {
        return actionCost;
    }

    public PawnSlot getPawnPlaced() {
        return pawnPlaced;
    }

    public boolean isSingle() {
        return single;
    }

    public boolean isOccupied() {
        return occupied;
    }


    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setSingle(boolean single) {

        this.single = single;
    }
}
