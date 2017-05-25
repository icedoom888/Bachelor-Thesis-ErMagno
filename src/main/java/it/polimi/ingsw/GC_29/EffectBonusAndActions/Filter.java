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
     * even if the actionSpace is occupied. This method returns true has that particular bonusAndMalus, false otherwise.
     * @param playerStatus
     * @param actionSpace
     * @return boolean value: true if the player can access the actionSpace, false otherwise
     */
    public static boolean applyOnActionSpace(PlayerStatus playerStatus, ActionSpace actionSpace) {
        return true;
    }
}
