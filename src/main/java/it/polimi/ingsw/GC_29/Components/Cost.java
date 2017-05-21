package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 21/05/17.
 */
public class Cost {

    private int numberOfAlternatives;
    private boolean alternative; // TODO: forse inutile per via di numberOfAlternatives
    private boolean withPrice;
    private GoodSet[] cost;
    private GoodSet[] necessaryGoodset;
    private boolean withActionValue;
    private int actionValue;

    public Cost(boolean withPrice, int numberOfAlternatives, boolean alternative, GoodSet[] costs, GoodSet[] necessaryGoodset, boolean withActionValue, int actionValue) {
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

    public Cost(Cost cost) { // immutable object
        this.numberOfAlternatives = cost.numberOfAlternatives;
        this.cost = cost.cost;
        this.alternative = cost.alternative;
        this.necessaryGoodset = cost.necessaryGoodset;
    }

    public int getNumberOfAlternatives() {
        return numberOfAlternatives;
    }

    /*
    public GoodSet[] getCost() { // immutable field
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
