package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Dice;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.List;

/**
 * Created by Christian on 16/06/2017.
 */
public class ThrowDices extends Input {


    @Override
    public void perform(Model model, Controller controller) {

        controller.stopTimer();

        for (Dice dice : model.getGameBoard().getDiceLane()) {
            dice.roll();
        }

        controller.setFamilyPawnsAndLeaderValues();


        List<Player> turnOrder = model.getTurnOrder();

        int currentPlayerIndex = 0; //devo inizializzarla se no da errori in chooseCurrentPlayer

        boolean b = true;

        while (b) {

            for (Player player : turnOrder) {

                if (player.getPlayerState() != PlayerState.SUSPENDED) {

                    currentPlayerIndex =  turnOrder.indexOf(player);

                    b = false;

                    break;
                }

            }
        }


        controller.chooseCurrentPlayer(currentPlayerIndex);

    }

}
