package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

import java.util.ArrayList;

/**
 * Created by Christian on 07/06/2017.
 */
public class GetValidActions extends Query<ArrayList<Action>> {

    @Override
    public ArrayList<Action> perform(GameStatus model) {
        return model.getCurrentPlayer().getCurrentValidActionsList();
    }
}
