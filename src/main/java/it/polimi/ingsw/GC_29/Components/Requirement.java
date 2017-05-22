package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public abstract class Requirement {
    public boolean check(PlayerStatus status) {
        return true;
    }
}
