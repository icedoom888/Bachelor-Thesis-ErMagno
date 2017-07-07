package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;

/**
 * Created by Lorenzotara on 29/06/17.
 */
public class GetExcommunication extends Query {

    @Override
    public String perform(Model model) {

        return model.getGameBoard().getExcommunicationLane().
                getExcommunicationTile(model.getCurrentEra()).getUrl();

    }
}
