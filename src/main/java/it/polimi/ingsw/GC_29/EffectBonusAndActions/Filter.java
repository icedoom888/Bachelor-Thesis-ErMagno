package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.max;

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
     * this method filters the gooSet obtained from an effect through all the bonus and malus regarding the Goodsets,
     * at the end it sets to zero all the goods of the goodset that have a negative amount due to the malus
     * @param goodsObtained GoodSet to be filtered through the bonusMalusOnGoodsList
     */
    public static void apply(Player player, GoodSet goodsObtained){

        List<BonusAndMalusOnGoods> currentPLayerBonusMalusOnGoods = player.getBonusAndMalusOnGoods();

        for (BonusAndMalusOnGoods playerBonusMalus : currentPLayerBonusMalusOnGoods) {

            if (playerBonusMalus != null) {

                playerBonusMalus.filter(goodsObtained);
            }
        }

        goodsObtained.setNonNegative();
    }


    /**
     *
     * this method filters the familyPawn actionValue through all the bonus or malus regarding the specific action.
     * At the end it sets the actionValue to zero if the actionValue has a negative value due to the malus
     */
    public static void apply(Player player, FamilyPawn familyPawn, ZoneType zoneType){

        List<BonusAndMalusOnAction> currentPlayerBonusMalusOnAction = player.getBonusAndMalusOnAction();

        for (BonusAndMalusOnAction playerBonusMalus : currentPlayerBonusMalusOnAction){

            if(playerBonusMalus != null){

                playerBonusMalus.filter(familyPawn, zoneType);

            }

        }

    }


    /**
     * This method divides the cardCost in its two components (if present) and put them in a list.
     * Then it calls the filter of bonusAndMalusOnCost on the list.
     * @param player
     * @param cardCost
     * @param costs
     * @param zoneType
     */
    public static void apply(Player player, CardCost cardCost, ArrayList<Cost> costs, ZoneType zoneType) {
        List<BonusAndMalusOnCost> currentPlayerBonusAndMalusOnCost = player.getBonusAndMalusOnCost();
        LinkedList<BonusAndMalusOnCost> currentPlayerBonusAndMalusOnCostTemporary = player.getCurrentBonusActionBonusMalusOnCostList();

        costs.add(cardCost.getMainCost());

        if (cardCost.isAlternative()) {
            costs.add(cardCost.getAlternativeCost());
        }

        for (BonusAndMalusOnCost bonusAndMalusOnCost : currentPlayerBonusAndMalusOnCost) {
            bonusAndMalusOnCost.filter(player, costs, zoneType);
        }

        for (BonusAndMalusOnCost bonusAndMalusOnCost : currentPlayerBonusAndMalusOnCostTemporary) {
            bonusAndMalusOnCost.filter(player,costs, zoneType);
            currentPlayerBonusAndMalusOnCostTemporary.removeFirst();
        }

        for (Cost cost : costs) {
            cost.getCost().setNonNegative();
        }

    }

    /**
     * aplySpecial return true if the player has the special bonusAndMals, false otherwise
     * @param player
     * @param special
     */
    public static boolean applySpecial(Player player, SpecialBonusAndMalus special) {

        if(!player.getSpecialBonusAndMaluses().isEmpty()){
        return player.getSpecialBonusAndMaluses().contains(special);
        }

        else {
            return false;
        }
    }

    public static boolean applySpecial(Player player, ZoneType zoneType) {

        switch (zoneType) {
            case GREENTOWER:
                return applySpecial(player, SpecialBonusAndMalus.NOGREENTOWER);

            case YELLOWTOWER:
                return applySpecial(player, SpecialBonusAndMalus.NOYELLOWTOWER);

            case BLUETOWER:
                return applySpecial(player, SpecialBonusAndMalus.NOBLUETOWER);

            case PURPLETOWER:
                return applySpecial(player, SpecialBonusAndMalus.NOPURPLETOWER);

            case MARKET:
                return applySpecial(player, SpecialBonusAndMalus.NOMARKET);

            case COUNCILPALACE:
                return applySpecial(player, SpecialBonusAndMalus.NOCOUNCILPALACE);

            case HARVEST:
                return applySpecial(player, SpecialBonusAndMalus.NOHARVEST);

            case PRODUCTION:
                return applySpecial(player, SpecialBonusAndMalus.NOPRODUCTION);

            default:
                return false;
        }
    }

}
