package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class BonusEffect implements Effect {

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
    public void execute(Player status){

        Action currentAction = status.getCurrentAction();
        bonusAndMalusOnAction.filter(currentAction.getTemporaryPawn(), currentAction.getZoneType());

    }

    public BonusAndMalusOnAction getBonusAndMalusOnAction() {
        return bonusAndMalusOnAction;
    }

    public BonusAndMalusOnGoods getBonusAndMalusOnGoods() {
        return bonusAndMalusOnGoods;
    }

    public BonusAndMalusOnCost getBonusAndMalusOnCost() {
        return bonusAndMalusOnCost;
    }

    @Override
    public String toString() {
        return "BonusEffect{"+"\n"
                + bonusAndMalusOnAction +"\n"
                + bonusAndMalusOnGoods +"\n"
                + bonusAndMalusOnCost +"\n"
                + '}';
    }
}
