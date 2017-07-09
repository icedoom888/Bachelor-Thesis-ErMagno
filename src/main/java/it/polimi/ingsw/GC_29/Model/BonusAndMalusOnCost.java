package it.polimi.ingsw.GC_29.Model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lorenzotara on 26/05/17.
 */
public class BonusAndMalusOnCost {

    private ZoneType zoneType;
    private GoodSet firstDiscount; //positive value means major cost
    private GoodSet secondDiscount;
    private boolean alternative;

    public BonusAndMalusOnCost(ZoneType zoneType,
                               GoodSet firstDiscount,
                               GoodSet secondDiscount,
                               boolean alternative) {

        this.zoneType = zoneType;
        this.firstDiscount = firstDiscount;
        this.secondDiscount = secondDiscount;
        this.alternative = alternative;

    }

    /**
     * This method creates the list of all the possible costs that the card can assume taking into
     * all the possible alternatives of the cardCost
     * @param costs
     * @param cardZoneType
     */
    public void filter(ArrayList<Cost> costs, ZoneType cardZoneType) {

        if (this.zoneType == cardZoneType
                || (this.zoneType == ZoneType.ANYTOWER
                    && (cardZoneType == ZoneType.BLUETOWER
                    || cardZoneType == ZoneType.GREENTOWER
                    || cardZoneType == ZoneType.YELLOWTOWER
                    || cardZoneType == ZoneType.PURPLETOWER))) {

            int listLength = costs.size();

            for (int i = 0; i < listLength ; i++) {
                if (alternative) {
                    Cost newCost = new Cost(costs.get(i));
                    changeCost(newCost,secondDiscount);
                    costs.add(newCost);
                }
                changeCost(costs.get(i), firstDiscount);
            }

        }
    }


    /**
     * Changes the cost of a card based on the discount that the player earned,
     * @param cost
     * @param discount
     */
    private void changeCost(Cost cost, GoodSet discount) {


        for (Map.Entry<GoodType, Integer> goodTypeIntegerEntry : discount.getMapGoodSet().entrySet()) {

            Integer goodAmountCost = cost.getCost().getGoodAmount(goodTypeIntegerEntry.getKey());
            Integer goodDiscount = goodTypeIntegerEntry.getValue();
            GoodType goodType = goodTypeIntegerEntry.getKey();

            if (goodDiscount * goodAmountCost != 0) {

                GoodSet goodSetToAdd = new GoodSet();

                if (goodAmountCost - goodDiscount >= 0) {

                    goodSetToAdd.setGoodAmount(goodType, - goodDiscount);
                }

                else {

                    goodSetToAdd.setGoodAmount(goodType, - cost.getCost().getGoodAmount(goodType));

                }

                cost.getCost().addGoodSet(goodSetToAdd);

            }
        }
    }

    @Override
    public String toString() {

        if(firstDiscount.areAllZeroValues() && secondDiscount.areAllZeroValues()){
            return "";
        }

        else{
            String string = "BonusAndMalusOnCost { " + "\n"
                    + "zoneType = " + zoneType + "\n"
                    + "discount = " + firstDiscount + "\n";

            if(alternative){

                string = string + "secondDiscount = " + secondDiscount + "\n"
                        +  '}';
            }

            return string;

        }

    }
}
