package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.GoodSet;
import it.polimi.ingsw.GC_29.Model.Model;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetGoodSet extends Query<GoodSet> {

    @Override
    public GoodSet perform(Model model) {
        return model.getCurrentPlayer().getActualGoodSet();
    }
}
