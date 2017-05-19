package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class FamilyPawn extends Pawn {
    private FamilyPawnType type;
    private int actualValue;

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public FamilyPawn(FamilyPawnType type, int actualValue) {
        this.type = type;
        this.actualValue = actualValue;
    }

    public FamilyPawn(FamilyPawn familyPawn) {
        this(familyPawn.getType(), familyPawn.getActualValue());
    }

    public FamilyPawnType getType() {

        return type;
    }

    public int getActualValue() {
        return actualValue;
    }
}
