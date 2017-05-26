package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.ActionSpace;
import it.polimi.ingsw.GC_29.Components.CardCost;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;

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



    public static void apply(PlayerStatus playerStatus,ActionType actionType,int actionValue){
        ArrayList<BonusAndMalusOnAction> currentPlayerBonusMalusOnAction = playerStatus.getBonusAndMalusOnAction();

        for (BonusAndMalusOnAction playerBonusMalus : currentPlayerBonusMalusOnAction){
            if(playerBonusMalus.getActionType()==actionType){
                playerBonusMalus.filter(actionValue,playerStatus.getActualGoodSet());
            }
        }
    }



    public static void apply(PlayerStatus playerStatus, CardCost cardCost) {

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
     * @param actionType
     * @return boolean value: true if the player can try to make the action, false otherwise
     */
    public static boolean applySpecia(PlayerStatus playerStatus, ActionType actionType) { return true; }

}
