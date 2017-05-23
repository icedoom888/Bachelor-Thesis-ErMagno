package it.polimi.ingsw.GC_29.EffectBonusAndActions;


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

    //private String name = "actionEffect";
    private ActionType type;
    private int actionValue;
    private Discount discount;


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
