package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

/**
 * Created by Lorenzotara on 01/07/17.
 */
public class JoinGame extends Input {

    PlayerColor playerColor;

    public JoinGame(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public void perform(Model model, Controller controller) {

        System.out.println("il colore del player sospeso è "+playerColor);

        for (Player player : model.getTurnOrder()) {

            if (player.getPlayerColor() == playerColor) {
                player.setNotSuspended();
                System.out.println("PLAYER DI NUOVO IN GIOCO");
                break;
            }
        }

    }
}
