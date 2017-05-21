package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 21/05/17.
 */
public class CardCost {

    // TODO: add method to call the Static Speaker class
    private int numberOfAlternatives;
    private boolean alternative; // TODO: forse inutile per via di numberOfAlternatives
    private boolean withPrice;
    private GoodSet[] cost;
    private GoodSet[] necessaryGoodset;
    private boolean withActionValue;
    private int actionValue;

    public CardCost(boolean withPrice, int numberOfAlternatives, boolean alternative, GoodSet[] costs, GoodSet[] necessaryGoodset, boolean withActionValue, int actionValue) {
        this.numberOfAlternatives = numberOfAlternatives;
        this.alternative = alternative;
        this.cost = new GoodSet[numberOfAlternatives];
        this.necessaryGoodset = new GoodSet[numberOfAlternatives];
        this.withPrice = withPrice;
        this.withActionValue = withActionValue;
        this.actionValue = actionValue;
        for (int i = 0; i < numberOfAlternatives; i++) {
            this.cost[i] = costs[i];
            this.necessaryGoodset[i] = necessaryGoodset[i];
        }
    }
    
    public CardCost(CardCost cardCost) { // immutable object
        this.numberOfAlternatives = cardCost.numberOfAlternatives;
        this.cost = cardCost.cost;
        this.alternative = cardCost.alternative;
        this.necessaryGoodset = cardCost.necessaryGoodset;
        this.withPrice = cardCost.withPrice;
        this.withActionValue = cardCost.withActionValue;
        this.actionValue = cardCost.actionValue;
    }

    public int getNumberOfAlternatives() {
        return numberOfAlternatives;
    }

    /*
    public GoodSet[] getCardCost() { // immutable field
        GoodSet[] temporaryGoodSet = new GoodSet[numberOfAlternatives];
        for (int i = 0; i < numberOfAlternatives; i++) {
            temporaryGoodSet[i] = this.cost[i];
        }
        return temporaryGoodSet;
    }
    */

    public boolean isAlternative() {
        return alternative;
    }
}
