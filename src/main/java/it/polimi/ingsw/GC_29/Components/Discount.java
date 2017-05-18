package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Christian on 18/05/2017.
 */
public class Discount {

    private GoodSet firstDiscount;
    private GoodSet secondDiscount;
    private boolean alternativeDiscount;

    public GoodSet getFirstDiscount() {
        return firstDiscount;
    }

    public GoodSet getSecondDiscount() {
        return secondDiscount;
    }

    public boolean isAlternativeDiscount() {
        return alternativeDiscount;
    }
}
