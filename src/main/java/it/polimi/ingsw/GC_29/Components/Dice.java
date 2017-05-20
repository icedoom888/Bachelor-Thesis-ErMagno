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

    public Dice(DiceType dicetype) {
        this.face = 0;
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
