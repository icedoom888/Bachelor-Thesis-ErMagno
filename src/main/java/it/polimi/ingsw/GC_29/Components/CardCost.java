package it.polimi.ingsw.GC_29.Components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Lorenzotara on 21/05/17.
 */
public class CardCost {

    // TODO: add method to call the Static Speaker class
    private boolean alternative; // can you choose between differnts methods to pay?
    private boolean withPrice; // has the card have a price?
    private GoodSet mainCost; // mainCost - one of the alternatives and the alternative chosen by the player
    private GoodSet alternativeCost;
    private boolean necessaryGoodSetForMainCost; // if it's true the necessaryGoodSet refers to mainCost
    private GoodSet necessaryGoodSet;

    public CardCost(
            boolean alternative,
            boolean withPrice,
            GoodSet mainCost,
            GoodSet alternativeCost,
            boolean necessaryGoodSetForMainCost,
            GoodSet necessaryGoodSet) {

        this.alternative = alternative;
        this.withPrice = withPrice;
        this.mainCost = mainCost;
        this.alternativeCost = alternativeCost;
        this.necessaryGoodSetForMainCost = necessaryGoodSetForMainCost;
        this.necessaryGoodSet = necessaryGoodSet;

    }


    
    public CardCost(CardCost cardCost) { // immutable object
        this.mainCost = cardCost.mainCost;
        this.alternative = cardCost.alternative;
        this.necessaryGoodSet = cardCost.necessaryGoodSet;
        this.withPrice = cardCost.withPrice;
    }




    public boolean isWithPrice() {
        return withPrice;
    }

    public GoodSet getMainCost() {
        return mainCost;
    }

    public GoodSet getAlternativeCost() {
        return alternativeCost;
    }

    public boolean isNecessaryGoodSetForMainCost() {
        return necessaryGoodSetForMainCost;
    }

    public GoodSet getNecessaryGoodSet() {
        return necessaryGoodSet;
    }

    public boolean isAlternative() {
        return alternative;
    }

    @Override
    public String toString() {
        return "CardCost{" + "alternative=" + alternative + ", withPrice=" + withPrice + ", mainCost=" + mainCost + ", alternativeCost=" + alternativeCost + ", necessaryGoodSetForMainCost=" + necessaryGoodSetForMainCost + ", necessaryGoodSet=" + necessaryGoodSet + '}';
    }
}
