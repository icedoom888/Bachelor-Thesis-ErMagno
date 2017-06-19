package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetGoodSet extends Query<GoodSet> {

    @Override
    public GoodSet perform(GameStatus model) {
        return model.getCurrentPlayer().getActualGoodSet();
    }
}
