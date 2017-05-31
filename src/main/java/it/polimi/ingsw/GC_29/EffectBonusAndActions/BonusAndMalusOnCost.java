package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.Cost;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 26/05/17.
 */
public class BonusAndMalusOnCost {

    private ZoneType zoneType;
    private GoodSet firstDiscount;
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
     * This method creates the list of all the possible costs that card can assume taking into
     * account all the bonusAndMalusOnCost of the player and all the possible alternatives of the
     * cardCost
     * @param playerStatus
     * @param costs
     * @param cardZoneType
     */
    public void filter(PlayerStatus playerStatus, ArrayList<Cost> costs, ZoneType cardZoneType) {

        if (this.zoneType == cardZoneType) {

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


    public void changeCost(Cost cost, GoodSet discount) {
        cost.getCost().addGoodSet(discount);
    }
}
