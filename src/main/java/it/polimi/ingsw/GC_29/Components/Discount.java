package it.polimi.ingsw.GC_29.Components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public Discount(
            @JsonProperty("firsDiscount") GoodSet firstDiscount,
            @JsonProperty("secondDiscount")GoodSet secondDiscount,
            @JsonProperty("alternativeDiscount")boolean alternativeDiscount) {

        this.firstDiscount = firstDiscount;
        this.secondDiscount = secondDiscount;
        this.alternativeDiscount = alternativeDiscount;
    }
}
