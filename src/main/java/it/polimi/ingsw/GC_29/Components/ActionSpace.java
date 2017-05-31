package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.FieldType;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class ActionSpace implements Cleanable {
    private Effect effect;
    private int actionCost;
    protected PawnSlot pawnPlaced;
    private boolean single;
    private boolean occupied;

    public ActionSpace(Effect effect, int actionCost, PawnSlot pawnPlaced, boolean single, boolean occupied) {
        this.effect = effect;
        this.actionCost = actionCost;
        this.pawnPlaced = pawnPlaced;
        this.single = single;
        this.occupied = occupied;
    }

    // TODO: clean e remove

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


    @Override
    public String toString() {
        return "ActionSpace{" +
                "the effect is:" + effect +
                ", the actionCost value is:" + actionCost +
                ", the pawns placed are=" + pawnPlaced +
                ", the actionSpace is only for one pawn:" + single +
                ", the actionSpace is occupied=" + occupied +
                '}';
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public void addPawn(FamilyPawn pawnPlaced) {
        this.pawnPlaced.addPawn(pawnPlaced);
        occupied = true;
    }

    public void removePawn(FamilyPawn familyPawn){
        for(Pawn pawn : pawnPlaced.getPlayerPawns()){
            if (pawn.getPlayerColor()==familyPawn.getPlayerColor() && ((FamilyPawn)pawn).getType()== familyPawn.getType()) {
                pawnPlaced.removePawn(pawn);
                break;
            }
        }
    }

    @Override
    public void clean() {

        if(!pawnPlaced.isFree()){

            pawnPlaced.clearSlot();

        }

    }
}
