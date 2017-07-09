package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;

/**
 * Created by Christian on 09/07/2017.
 */
public class GameBoardQuery extends Query<String> {
    @Override
    public String perform(Model model) {
        return model.getGameBoard().toTable();
    }
}
