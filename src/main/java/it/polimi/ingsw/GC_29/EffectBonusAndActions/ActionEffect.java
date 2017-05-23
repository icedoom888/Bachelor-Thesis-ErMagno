package it.polimi.ingsw.GC_29.EffectBonusAndActions;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.polimi.ingsw.GC_29.Components.Discount;
import it.polimi.ingsw.GC_29.Controllers.ActionBuilder;
import it.polimi.ingsw.GC_29.Controllers.FactoryActionBuilder;
import it.polimi.ingsw.GC_29.Player.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 18/05/2017.
 */
// @JsonDeserialize(as = ActionEffect.class)
public class ActionEffect implements Effect{

    private ActionType type;
    private int actionValue; //TODO: qui metterei direttamente la bonusPawn
    private Discount discount;

    @JsonCreator
    public ActionEffect(
            @JsonProperty("type") ActionType type,
            @JsonProperty("actionValue") int actionValue,
            @JsonProperty("discount") Discount discount) {

        this.type = type;

        this.actionValue = actionValue;

        this.discount = discount;
    }

    public ActionEffect(ActionEffect actionEffect) {
        this.type = actionEffect.type;

        this.actionValue = actionEffect.actionValue;

        this.discount = actionEffect.discount;
    }

    public ActionType getType() {

        return type;
    }

    public int getActionValue() {

        return actionValue;
    }

    public Discount getDiscount() {

        return discount;
    }

    @Override
    public String toString() {
        return "ActionEffect{" +
                "type=" + type +
                ", actionValue=" + actionValue +
                ", discount=" + discount +
                '}';
    }

    /*@Override
    public void execute(PlayerStatus status) {
        // devo passare al builder realAction false
        // chiedo al player che discount vuole
    }*/

    @Override
    public void execute(PlayerStatus playerStatus) {

        ActionBuilder actionBuilder = FactoryActionBuilder.getActionBuilder(type,true, playerStatus);
    }

}
