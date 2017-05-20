package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class BonusEffect implements Effect {

    private BonusAndMalusOnAction bonusAndMalusOnAction;

    public BonusEffect(BonusAndMalusOnAction bonusAndMalusOnAction) {
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
    }

    @Override
    /**
     * This effect add the bonusAndMalusOnAction in the list of
     * bonusAndMalusOnAction of the player
     */
    public void execute(Player player) { // x albi qua modifica
        System.out.println("You received a Bonus! It will be added to your Bonus List");
        ArrayList<BonusAndMalusOnAction> bonusOnActionList = player.getStatus().getBonusAndMalusOnActionList(); // x albi qua modifica
        bonusOnActionList.add(bonusAndMalusOnAction);
    }
}
