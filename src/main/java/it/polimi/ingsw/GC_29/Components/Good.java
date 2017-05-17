package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Christian on 17/05/2017.
 */
public class Good {

    private GoodType type;
    private int amount;

    public Good(GoodType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public GoodType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
