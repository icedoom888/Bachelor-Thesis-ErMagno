package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Dice;

/**
 * Created by Christian on 16/06/2017.
 */
public class ThrowDices extends Input {


    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        for (Dice dice : model.getGameBoard().getDiceLane()) {
            dice.roll();
        }

        controller.setFamilyPawnsAndLeaderValues();

        controller.chooseCurrentPlayer(0);
    }
}
