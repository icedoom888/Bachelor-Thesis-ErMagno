package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Dice {
    private int face;
    private DiceType color;

    public int getFace() {
        return face;
    }

    public DiceType getColor() {
        return color;
    }

    public Dice(int face) {
        this.face = face;
    }

    public void roll(){
        face = (int)(Math.random()*6) + 1;
    }
}
