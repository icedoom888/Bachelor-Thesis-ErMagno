package it.polimi.ingsw.GC_29.Model;

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
        this.cost = new GoodSet(cost.cost);
        this.necessaryResources = new GoodSet(cost.necessaryResources);
    }

    public GoodSet getCost() {
        return cost;
    }

    public GoodSet getNecessaryResources() {
        return necessaryResources;
    }

    @Override
    public String toString() {

        String string =  cost + "\n";

        if(!necessaryResources.areAllZeroValues()){

            string = string + "necessaryResources = " + necessaryResources +"\n"
                    + '}';
        }

        return string;
    }
}
