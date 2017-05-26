package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.CardCost;
import it.polimi.ingsw.GC_29.Components.Cost;
import it.polimi.ingsw.GC_29.Components.Discount;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import javax.smartcardio.Card;
import java.util.ArrayList;

/**
 * Created by Lorenzotara on 26/05/17.
 */
public class BonusAndMalusOnCost {

    private ActionType actionType;
    private GoodSet firstDiscount;
    private GoodSet secondDiscount;
    private boolean alternative;

    public BonusAndMalusOnCost(ActionType actionType,
                               GoodSet firstDiscount,
                               GoodSet secondDiscount,
                               boolean alternative) {

        this.actionType = actionType;
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
     * @param cardActionType
     */
    public void filter(PlayerStatus playerStatus, ArrayList<Cost> costs, ActionType cardActionType) {

        if (this.actionType == cardActionType) {

            int listLength = costs.size();
            int cont = 0;
            for (Cost cost : costs) {
                if (cont >= listLength) break;
                if (alternative) {
                    Cost newCost = new Cost(cost);
                    changeCost(newCost,secondDiscount);
                    costs.add(newCost);
                }
                changeCost(cost, firstDiscount);
                cont++;
            }
        }
    }

    public void changeCost(Cost cost, GoodSet discount) {
        cost.getCost().addGoodSet(discount);
    }
}
