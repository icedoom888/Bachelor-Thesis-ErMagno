package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Lorenzotara on 01/07/17.
 */
public class JoinGame extends Input {

    PlayerColor playerColor;

    public JoinGame(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        for (Player player : model.getTurnOrder()) {

            if (player.getPlayerColor() == playerColor) {
                player.setNotSuspended();
                break;
            }
        }

    }
}
