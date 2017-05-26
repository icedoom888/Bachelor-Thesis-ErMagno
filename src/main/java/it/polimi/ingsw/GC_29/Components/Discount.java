package it.polimi.ingsw.GC_29.Components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Christian on 18/05/2017.
 */
public class Discount {

    //TODO: cancellare probabilmente

    /*
    Il discount può essere sia positivo che negativo
     */

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

    public Discount(
            GoodSet firstDiscount,
            GoodSet secondDiscount,
            boolean alternativeDiscount) {

        this.firstDiscount = firstDiscount;
        this.secondDiscount = secondDiscount;
        this.alternativeDiscount = alternativeDiscount;
    }

    @Override
    public String toString() {
        return "Discount{" + "firstDiscount=" + firstDiscount + ", secondDiscount=" + secondDiscount + ", alternativeDiscount=" + alternativeDiscount + '}';
    }
}
