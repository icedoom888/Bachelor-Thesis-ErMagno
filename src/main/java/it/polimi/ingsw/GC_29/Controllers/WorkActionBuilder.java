package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.ActionType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.
 */
public class WorkActionBuilder implements ActionBuilder {

    private PlayerStatus playerStatus;

    public WorkActionBuilder(ActionType harvest) {
    }

    @Override
    public Action build() {
        return null;
    }
}
