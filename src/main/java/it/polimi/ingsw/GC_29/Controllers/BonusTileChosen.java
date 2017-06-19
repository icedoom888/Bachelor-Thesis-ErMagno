package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.BonusTile;

/**
 * Created by Christian on 19/06/2017.
 */
public class BonusTileChosen extends Input {

    private int bonusTileIndex;

    public BonusTileChosen(int bonusTileChosen) {

        bonusTileIndex = bonusTileChosen;
    }


    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        BonusTile bonusTile = model.getBonusTileList().remove(bonusTileIndex);

        model.getCurrentPlayer().getPersonalBoard().setBonusTile(bonusTile);


    }
}
