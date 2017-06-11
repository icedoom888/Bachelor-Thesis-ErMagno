package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public interface Effect {

    void execute(Player status) throws Exception;
}
