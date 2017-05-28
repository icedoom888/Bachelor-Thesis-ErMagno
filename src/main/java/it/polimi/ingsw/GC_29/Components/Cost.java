package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 26/05/17.
 */
public class Cost {

    private GoodSet cost;
    private GoodSet necessaryResources;

    public Cost(GoodSet cost, GoodSet necessaryResources) {
        this.cost = cost;
        this.necessaryResources = necessaryResources;
    }

    public Cost(){
        this.cost = new GoodSet();
        this.necessaryResources = new GoodSet();
    }

    public Cost(Cost cost) {
        this.cost = cost.cost;
        this.necessaryResources = cost.necessaryResources;
    }

    public GoodSet getCost() {
        return cost;
    }

    public GoodSet getNecessaryResources() {
        return necessaryResources;
    }

    @Override
    public String toString() {
        return "Cost{" + "cost=" + cost + ", necessaryResources=" + necessaryResources + '}';
    }
}
