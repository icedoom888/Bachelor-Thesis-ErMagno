package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.ActionSpace;
import it.polimi.ingsw.GC_29.Components.CardCost;
import it.polimi.ingsw.GC_29.Components.Cost;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Christian on 21/05/2017.
 */

/**
 * The class Filter mimics the behavior of a static class
 */
public final class Filter {

    private Filter(){ // private in order to deny the instantiation

    }



    /**
     *
     * @param goodsObtained GoodSet to be filtered through the bonusMalusOnGoodsList
     */
    public static void apply(PlayerStatus playerStatus, GoodSet goodsObtained){

        ArrayList<BonusAndMalusOnGoods> currentPLayerBonusMalusOnGoods = playerStatus.getBonusAndMalusOnGoods();

        for (BonusAndMalusOnGoods playerBonusMalus : currentPLayerBonusMalusOnGoods) {

            if (playerBonusMalus != null) {
                playerBonusMalus.filter(goodsObtained);
            }
        }
    }



    public static void apply(PlayerStatus playerStatus, ZoneType zoneType, int actionValue){
        ArrayList<BonusAndMalusOnAction> currentPlayerBonusMalusOnAction = playerStatus.getBonusAndMalusOnAction();

        for (BonusAndMalusOnAction playerBonusMalus : currentPlayerBonusMalusOnAction){
            if(playerBonusMalus.getZoneType()== zoneType){
                playerBonusMalus.filter(actionValue,playerStatus.getActualGoodSet());
            }
        }
    }


    /**
     * This method divides the cardCost in its two components (if present) and put them in a list.
     * Then it calls the filter of bonusAndMalusOnCost on the list.
     * @param playerStatus
     * @param cardCost
     * @param costs
     * @param zoneType
     */
    public static void apply(PlayerStatus playerStatus, CardCost cardCost, ArrayList<Cost> costs, ZoneType zoneType) {
        ArrayList<BonusAndMalusOnCost> currentPlayerBonusAndMalusOnCost = playerStatus.getBonusAndMalusOnCost();
        LinkedList<BonusAndMalusOnCost> currentPlayerBonusAndMalusOnCostTemporary = playerStatus.getCurrentBonusActionBonusMalusOnCostList();

        costs.add(cardCost.getMainCost());

        if (cardCost.isAlternative()) {
            costs.add(cardCost.getAlternativeCost());
        }

        for (BonusAndMalusOnCost bonusAndMalusOnCost : currentPlayerBonusAndMalusOnCost) {
            bonusAndMalusOnCost.filter(playerStatus, costs, zoneType);
        }

        for (BonusAndMalusOnCost bonusAndMalusOnCost : currentPlayerBonusAndMalusOnCostTemporary) {
            bonusAndMalusOnCost.filter(playerStatus,costs, zoneType);
            currentPlayerBonusAndMalusOnCostTemporary.removeFirst();
        }

    }


    /**
     * The method checks if the player has a bonusAndMalus that can make him access to the actionSpace
     * even if the actionSpace is occupied. This method returns true if the player has that particular bonusAndMalus,
     * false otherwise.
     * @param playerStatus
     * @return boolean value: true if the player can access the actionSpace, false otherwise
     */
    public static boolean applySpecial(PlayerStatus playerStatus, ActionSpace actionSpace) {
        return true;
    }



    /**
     * The method checks if the player has a bonusAndMalus that can make him not pay the tower if occupied.
     * This method returns true if the player has that particular bonusAndMalus, false otherwise.
     * @param playerStatus
     * @return boolean value: true if the player can avoid to pay the towerCost, false otherwise
     */
    public static boolean applySpecial(PlayerStatus playerStatus, GoodSet towerCost) { return true; }



    /**
     * The method checks if the player has a bonusAndMalus that denies him to make the action. It returns
     * true if the player DOES NOT have it, false otherwise
     * @param playerStatus
     * @param zoneType
     * @return boolean value: true if the player can try to make the action, false otherwise
     */
    public static boolean applySpecia(PlayerStatus playerStatus, ZoneType zoneType) { return true; }

}
