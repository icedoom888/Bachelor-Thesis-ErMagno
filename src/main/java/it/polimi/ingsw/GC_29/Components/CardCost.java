package it.polimi.ingsw.GC_29.Components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Lorenzotara on 21/05/17.
 */
public class CardCost {

    // TODO: add method to call the Static Speaker class
    private boolean withPrice; // has the card have a price?
    private boolean alternative; // can you choose between differnts methods to pay?
    private Cost mainCost; // mainCost - one of the alternatives and the alternative chosen by the player
    private Cost alternativeCost;

    private CardCost(
            boolean alternative,
            boolean withPrice,
            Cost mainCost,
            Cost alternativeCost) {

        this.alternative = alternative;
        this.withPrice = withPrice;
        this.mainCost = mainCost;
        this.alternativeCost = alternativeCost;


    }


    
    public CardCost(CardCost cardCost) { // immutable object
        this.withPrice = cardCost.withPrice;
        this.mainCost = cardCost.mainCost;
        this.alternative = cardCost.alternative;
        this.withPrice = cardCost.withPrice;
    }




    public boolean isWithPrice() {
        return withPrice;
    }

    public Cost getMainCost() {
        return mainCost;
    }

    public Cost getAlternativeCost() {
        return alternativeCost;
    }

    public boolean isAlternative() {
        return alternative;
    }

    @Override
    public String toString() {
        return "CardCost{" + "withPrice=" + withPrice + ", alternative=" + alternative + ", mainCost=" + mainCost + ", alternativeCost=" + alternativeCost + '}';
    }
}
