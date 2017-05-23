package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 19/05/17.
 */
@JsonDeserialize(as = BonusEffect.class)
public class BonusEffect implements Effect {

    //private String name = "bonusEffect";
    private BonusAndMalusOnAction bonusAndMalusOnAction;

    public BonusEffect(BonusAndMalusOnAction bonusAndMalusOnAction) {
        this.bonusAndMalusOnAction = bonusAndMalusOnAction;
    }

    @Override
    /**
     * This effect add the bonusAndMalusOnAction in the list of
     * bonusAndMalusOnAction of the player
     */
    public void execute(PlayerStatus status) {
        System.out.println("You received a Bonus! It will be added to your Bonus List");
        ArrayList<BonusAndMalusOnAction> bonusOnActionList = status.getBonusAndMalusOnAction();
        bonusOnActionList.add(bonusAndMalusOnAction);
    }
}
