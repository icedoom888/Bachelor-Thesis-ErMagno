package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class BonusEffect implements Effect {

    //TODO: cambiare execute non come permanent

    private BonusAndMalusOnAction bonusAndMalusOnAction;
    private BonusAndMalusOnGoods bonusAndMalusOnGoods;
    private BonusAndMalusOnCost bonusAndMalusOnCost;


    public BonusEffect(BonusAndMalusOnAction bonusAndMalusOnAction,
                       BonusAndMalusOnGoods bonusAndMalusOnGoods,
                       BonusAndMalusOnCost bonusAndMalusOnCost) {

        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
        this.bonusAndMalusOnGoods = bonusAndMalusOnGoods;
        this.bonusAndMalusOnCost = bonusAndMalusOnCost;
    }

    @Override
    /**
     * This effect add the bonusAndMalusOnAction in the list of
     * bonusAndMalusOnAction of the player
     */
    public void execute(PlayerStatus status) {

        System.out.println("You received a Bonus! It will be added to your Bonus List");

        ArrayList<BonusAndMalusOnAction> bonusOnActionList = status.getBonusAndMalusOnAction();
        ArrayList<BonusAndMalusOnGoods> bonusAndMalusOnGoodsList = status.getBonusAndMalusOnGoods();
        ArrayList<BonusAndMalusOnCost> bonusAndMalusOnCostList = status.getBonusAndMalusOnCost();

        bonusOnActionList.add(bonusAndMalusOnAction);
        bonusAndMalusOnGoodsList.add(bonusAndMalusOnGoods);
        bonusAndMalusOnCostList.add(bonusAndMalusOnCost);

    }

    @Override
    public String toString() {
        return "BonusEffect{" + "bonusAndMalusOnAction=" + bonusAndMalusOnAction + ", bonusAndMalusOnGoods=" + bonusAndMalusOnGoods + ", bonusAndMalusOnCost=" + bonusAndMalusOnCost + '}';
    }
}
