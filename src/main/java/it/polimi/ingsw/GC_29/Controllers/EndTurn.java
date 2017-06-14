package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Player.Player;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Lorenzotara on 10/06/17.
 */
public class EndTurn extends Input {

    /**
     * During the EndTurn, the current PlayerState is set to WAITING. Then, if the player that was playing was
     * playing because he skipped a turn, the method chooses another player from the ones who skipped. If the list
     * of skipped players is empty, the GameStatus is set to RUNNING and it is the end of the Round. On the other hand,
     * if it was a normal player, if he is not the last one, the controller chooses the next player. In case he was the
     * last player, but it was not the last turn, the first player in order starts to play. In the event that the turn
     * was the 4th, the GameState is set to CheckOnSkipped and, if there are players who have skipped the turn, they
     * start playing, if not, controller.endRound() is called.
     * @param model
     * @param controller
     * @throws Exception
     */
    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        Player currentPlayer = model.getCurrentPlayer();
        currentPlayer.setPlayerState(PlayerState.WAITING);
        List<Player> turnOrder = model.getTurnOrder();

        if (model.getGameState() == GameState.CHECKONSKIPPED) {
            setSkippedPlayer(model, controller);
        }


        if (turnOrder.indexOf(currentPlayer) == turnOrder.size()-1) {

            if (model.getCurrentTurn() < 4) {
                model.setCurrentTurn(model.getCurrentTurn()+1);
                model.setCurrentPlayer(model.getTurnOrder().get(0));

                controller.getActionChecker().resetActionList();

                controller.getActionChecker().setCurrentPlayer();

                model.getCurrentPlayer().setPlayerState(PlayerState.DOACTION);
            }

            if (model.getCurrentTurn() == 4) {

                setSkippedPlayer(model, controller);

            }
        }

        else {
            int indexOfNextPlayer = turnOrder.indexOf(currentPlayer) + 1;
            controller.chooseCurrentPlayer(indexOfNextPlayer);
        }
    }


    private void setSkippedPlayer(GameStatus model, Controller controller) throws Exception {

        if (model.getSkippedTurnPlayers().isEmpty()) {
            model.setGameState(GameState.RUNNING);
            controller.handleEndRound();
        }

        else {
            model.setGameState(GameState.CHECKONSKIPPED);
            Player newCurrentPlayer = model.getSkippedTurnPlayers().remove(0);
            model.setCurrentPlayer(newCurrentPlayer);

            controller.getActionChecker().resetActionList();

            controller.getActionChecker().setCurrentPlayer();

            newCurrentPlayer.setPlayerState(PlayerState.DOACTION);

        }
    }
}
