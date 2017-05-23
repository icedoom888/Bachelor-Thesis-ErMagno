package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.GC_29.Components.GoodSet;

/**
 * Created by Lorenzotara on 17/05/17.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)
public class BonusAndMalusOnAction {
    private ActionType actionType;
    private int diceIncrementOrReduction;
    private GoodSet goodSetDiscountOrIncrement;
    private boolean actionAllowed;

    public BonusAndMalusOnAction(ActionType actionType, int diceIncrementOrReduction, GoodSet goodSetDiscountOrIncrement) {
        this.actionType = actionType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.goodSetDiscountOrIncrement = goodSetDiscountOrIncrement;
        this.actionAllowed = true;
    }

    @JsonCreator
    public BonusAndMalusOnAction(
            @JsonProperty("actionType")ActionType actionType,
            @JsonProperty("diceIncrementOrReduction") int diceIncrementOrReduction) {

        this.actionType = actionType;
        this.diceIncrementOrReduction = diceIncrementOrReduction;
        this.goodSetDiscountOrIncrement = new GoodSet();
        this.actionAllowed = true;
    }


    public BonusAndMalusOnAction(ActionType actionType,
                                 boolean actionAllowed) {

        this.actionType = actionType;
        this.actionAllowed = actionAllowed;
        this.diceIncrementOrReduction = 0;
        this.goodSetDiscountOrIncrement = null;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getDiceIncrementOrReduction() {
        return diceIncrementOrReduction;
    }

    public GoodSet getGoodSetDiscountOrIncrement() {
        return goodSetDiscountOrIncrement;
    }

    public boolean isActionAllowed() {
        return actionAllowed;
    }

    public boolean filter(int actionValue, GoodSet actualGoodset){
        return true;
    }
}
