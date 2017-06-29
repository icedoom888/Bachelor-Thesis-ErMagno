package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Controllers.GameStatus;

/**
 * Created by Lorenzotara on 29/06/17.
 */
public class GetExcommunication extends Query {

    @Override
    public String perform(GameStatus model) {

        return model.getGameBoard().getExcommunicationLane().
                getExcommunicationTile(model.getCurrentEra()).getUrl();

    }
}
