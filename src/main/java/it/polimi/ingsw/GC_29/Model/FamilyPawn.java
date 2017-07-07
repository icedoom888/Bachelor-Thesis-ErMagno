package it.polimi.ingsw.GC_29.Model;

import java.io.Serializable;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class FamilyPawn extends Pawn implements Serializable{

    private FamilyPawnType type;
    private int actualValue;

    /** this method will be called at the beginning of every turn to set the pawn values based on the dices
     * @param actualValue is the dice value
     */
    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public FamilyPawn(PlayerColor playerColor, FamilyPawnType type, int actualValue) {
        super(playerColor);
        this.type = type;
        this.actualValue = actualValue;
    }

    public FamilyPawn(FamilyPawn familyPawn) {
        this(familyPawn.getPlayerColor(), familyPawn.getType(), familyPawn.getActualValue());
    }

    public FamilyPawnType getType() {
        return type;
    }

    public int getActualValue() {
        return actualValue;
    }

    @Override
    public String toString() {
        return "FamilyPawn{" +
                "type=" + type +
                ", actualValue=" + actualValue +
                '}';
    }
}
