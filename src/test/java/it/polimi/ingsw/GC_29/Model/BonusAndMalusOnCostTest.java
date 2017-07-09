package it.polimi.ingsw.GC_29.Model;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 09/07/17.
 */
public class BonusAndMalusOnCostTest {
    @Test
    public void testFilter() throws Exception {

        Cost cost1 = new Cost(new GoodSet(1,1,1,1,1,1,1), new GoodSet());
        Cost cost2 = new Cost(new GoodSet(2,2,2,2,2,2,2), new GoodSet());
        Cost cost3 = new Cost(new GoodSet(3,3,3,3,3,3,3), new GoodSet());
        Cost cost4 = new Cost(new GoodSet(4,4,4,4,4,4,4), new GoodSet());

        ArrayList<Cost> costs = new ArrayList<>();
        costs.add(cost1);
        costs.add(cost2);
        costs.add(cost3);
        costs.add(cost4);

        int intialSize = costs.size();

        BonusAndMalusOnCost bonusAndMalusOnCost = new BonusAndMalusOnCost(ZoneType.GREENTOWER, new GoodSet(), new GoodSet(1,1,1,1,1,1,1), true);

        bonusAndMalusOnCost.filter(costs, ZoneType.GREENTOWER);

        assertTrue(costs.size() == 2*intialSize);
        assertTrue(costs.get(0).getCost().equals(new GoodSet(1,1,1,1,1,1,1)));
        assertTrue(costs.get(1).getCost().equals(new GoodSet(2,2,2,2,2,2,2)));
        assertTrue(costs.get(2).getCost().equals(new GoodSet(3,3,3,3,3,3,3)));
        assertTrue(costs.get(3).getCost().equals(new GoodSet(4,4,4,4,4,4,4)));

        assertTrue(costs.get(4).getCost().equals(new GoodSet()));
        assertTrue(costs.get(5).getCost().equals(new GoodSet(1,1,1,1,1,1,1)));
        assertTrue(costs.get(6).getCost().equals(new GoodSet(2,2,2,2,2,2,2)));
        assertTrue(costs.get(7).getCost().equals(new GoodSet(3,3,3,3,3,3,3)));


        cost1 = new Cost(new GoodSet(1,1,1,1,1,1,1), new GoodSet());
        cost2 = new Cost(new GoodSet(2,2,2,2,2,2,2), new GoodSet());
        cost3 = new Cost(new GoodSet(3,3,3,3,3,3,3), new GoodSet());
        cost4 = new Cost(new GoodSet(4,4,4,4,4,4,4), new GoodSet());

        costs = new ArrayList<>();
        costs.add(cost1);
        costs.add(cost2);
        costs.add(cost3);
        costs.add(cost4);

        intialSize = costs.size();

        /**
         * malus bigger than real cost
         */
        bonusAndMalusOnCost = new BonusAndMalusOnCost(ZoneType.GREENTOWER, new GoodSet(), new GoodSet(5,5,5,5,5,5,5), true);

        bonusAndMalusOnCost.filter(costs, ZoneType.GREENTOWER);

        assertTrue(costs.size() == 2*intialSize);
        assertTrue(costs.get(0).getCost().equals(new GoodSet(1,1,1,1,1,1,1)));
        assertTrue(costs.get(1).getCost().equals(new GoodSet(2,2,2,2,2,2,2)));
        assertTrue(costs.get(2).getCost().equals(new GoodSet(3,3,3,3,3,3,3)));
        assertTrue(costs.get(3).getCost().equals(new GoodSet(4,4,4,4,4,4,4)));

        assertTrue(costs.get(4).getCost().equals(new GoodSet()));
        assertTrue(costs.get(5).getCost().equals(new GoodSet()));
        assertTrue(costs.get(6).getCost().equals(new GoodSet()));
        assertTrue(costs.get(7).getCost().equals(new GoodSet()));

    }

}