package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Dice {
    private int face;
    private FamilyPawnType color;

    public int getFace() {
        return face;
    }

    public FamilyPawnType getColor() {
        return color;
    }

    public Dice(FamilyPawnType dicetype) {

        if (dicetype != FamilyPawnType.BLACK && dicetype != FamilyPawnType.ORANGE && dicetype != FamilyPawnType.WHITE){
            throw new IllegalArgumentException("Illegal dice type: " + dicetype);
        }

        this.face = 1;
        this.color= dicetype;
    }

    public void roll(){

        face = (int)(Math.random()*6) + 1;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "face=" + face +
                ", color=" + color +
                '}';
    }
}
