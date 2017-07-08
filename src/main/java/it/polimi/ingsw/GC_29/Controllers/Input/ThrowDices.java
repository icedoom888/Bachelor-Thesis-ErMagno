package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.Input.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.Dice;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;

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

        boolean playerChosen = false;

            for (Player player : turnOrder) {

                player.setPlayerState(PlayerState.WAITING);

                if (player.getPlayerState() != PlayerState.SUSPENDED && !playerChosen) {

                    currentPlayerIndex =  turnOrder.indexOf(player);

                    playerChosen = true;

                }

            }

        controller.chooseCurrentPlayer(currentPlayerIndex);

    }

}
