package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

/**
 * Created by Christian on 09/07/2017.
 */
public class PersonalBoardQuery extends Query<String> {

    private PlayerColor playerColor;

    public PersonalBoardQuery(PlayerColor playerColor){

        this.playerColor = playerColor;

    }

    @Override
    public String perform(Model model) {

        return model.getPlayer(playerColor).getPersonalBoard().toTable();

    }
}
