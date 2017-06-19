package it.polimi.ingsw.GC_29.Controllers;


import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 13/06/2017.
 */
public class Initialize extends Input {

    private PlayerColor playerColor;

    public Initialize(PlayerColor playerColor) {

        this.playerColor = playerColor;

    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        if(model.getGameState() == null){

            model.setGameState(GameState.RUNNING);
        }

        List<Player> turnOrder = model.getTurnOrder();

        Player lastPlayer = turnOrder.get(turnOrder.size()-1);

        if(lastPlayer.getPlayerColor() == playerColor){

            model.setCurrentPlayer(lastPlayer);
            //controller.setCurrentBonusTileIndexPlayer()
            lastPlayer.setPlayerState(PlayerState.CHOOSE_BONUS_TILE);
        }

        else {
            model.getPlayer(playerColor).setPlayerState(PlayerState.WAITING);
        }


        /*if(model.getCurrentPlayer().getPlayerColor() == playerColor){

            controller.getActionChecker().setCurrentPlayer();
            model.getCurrentPlayer().setPlayerState(PlayerState.THROWDICES);
        }
        else {
            model.getPlayer(playerColor).setPlayerState(PlayerState.WAITING);
        }*/

    }

}
