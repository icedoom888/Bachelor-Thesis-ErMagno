package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

/**
 * Created by Lorenzotara on 04/07/17.
 */
public class UseLeaderCardsGUI extends Input {

    private PlayerColor playerColor;

    public UseLeaderCardsGUI(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public void perform(Model model, Controller controller) {
        for (Player player : model.getTurnOrder()) {

            if (player.getPlayerColor() == playerColor) {
                player.sendAvailableLeaders();

                return;
            }
        }
    }
}
