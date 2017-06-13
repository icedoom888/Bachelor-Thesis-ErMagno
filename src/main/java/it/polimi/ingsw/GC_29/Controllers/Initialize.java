package it.polimi.ingsw.GC_29.Controllers;


import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Christian on 13/06/2017.
 */
public class Initialize extends Input {

    private PlayerColor playerColor;

    public Initialize(PlayerColor playerColor){

        this.playerColor = playerColor;
    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        if(model.getGameState() == null){

            model.setGameState(GameState.RUNNING);
        }


        if(model.getCurrentPlayer().getPlayerColor() == playerColor){
            model.getCurrentPlayer().setPlayerState(PlayerState.DOACTION);
        }
        else {
            model.getPlayer(playerColor).setPlayerState(PlayerState.WAITING);
        }

    }
}
