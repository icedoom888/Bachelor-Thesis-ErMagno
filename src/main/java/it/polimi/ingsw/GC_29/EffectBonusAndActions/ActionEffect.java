package it.polimi.ingsw.GC_29.EffectBonusAndActions;


import it.polimi.ingsw.GC_29.Components.Discount;
import it.polimi.ingsw.GC_29.Player.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 18/05/2017.
 */
public class ActionEffect implements Effect{

    private ActionType type;
    private int actionValue;
    private Discount discount;


    @Override
    public void execute(PlayerStatus status) {
        // devo passare al builder realAction false
        // chiedo al player che discount vuole
    }

    @Override
    public void execute(Player player) {

    }
}
