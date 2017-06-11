package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Player.Player;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Lorenzotara on 10/06/17.
 */
public class EndTurn extends Input {

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        Player currentPlayer = model.getCurrentPlayer();
        currentPlayer.setPlayerState(PlayerState.WAITING);

        if (model.getGameState() != GameState.CHECKONSKIPPED) {
            List<Player> turnOrder = model.getTurnOrder();

            if (turnOrder.indexOf(currentPlayer) == turnOrder.size()-1) { // è l'ultimo giocatore del turno

                model.setGameState(GameState.CHECKONSKIPPED);

            }
        }

        setSkippedPlayer(model, controller);

    }


    private void setSkippedPlayer(GameStatus model, Controller controller) throws Exception {

        if (model.getSkippedTurnPlayers().isEmpty()) {
            model.setGameState(GameState.RUNNING);
            controller.handleEndRound();
        }

        else {
            Player newCurrentPlayer = model.getSkippedTurnPlayers().remove(0);
            newCurrentPlayer.setPlayerState(PlayerState.DOACTION);
            model.setCurrentPlayer(newCurrentPlayer);

        }
    }
}
