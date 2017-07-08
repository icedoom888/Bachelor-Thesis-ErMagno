package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;

import java.util.List;

/**
 * Created by Lorenzotara on 10/06/17.
 */
public class EndTurn extends Input {

    private final int minNumberOfPlayers = 2;

    /**
     * During the EndTurn, the current PlayerState is set to WAITING. Then, if the player that was playing was
     * playing because he skipped a turn, the method chooses another player from the ones who skipped. If the list
     * of skipped players is empty, the Model is set to RUNNING and it is the end of the Round. On the other hand,
     * if it was a normal player, if he is not the last one, the controller chooses the next player. In case he was the
     * last player, but it was not the last turn, the first player in order starts to play. In the event that the turn
     * was the 4th, the GameState is set to CheckOnSkipped and, if there are players who have skipped the turn, they
     * start playing, if not, controller.endRound() is called.
     * @param model
     * @param controller
     * @throws Exception
     */
    @Override
    public void perform(Model model, Controller controller) {

        model.notifyEndMove();

        controller.handleReconnectedPlayers();

        controller.handleDisconnectedPlayers();
        
        controller.stopTimer();

        if(controller.minNumberOfPlayerReached()){

            controller.endGame();

            return;
        }


        Player currentPlayer = model.getCurrentPlayer();

        currentPlayer.setPlayerState(PlayerState.WAITING);

        List<Player> turnOrder = model.getTurnOrder();

        if (model.getGameState() == GameState.CHECKONSKIPPED) {
            setSkippedPlayer(model, controller);
        }

        else if (seeIfLastPlayer(turnOrder, currentPlayer)) {

            if (model.getCurrentTurn() < 4) {

                model.setCurrentTurn(model.getCurrentTurn()+1);

                turnOrder = model.getTurnOrder();

                for (Player player : turnOrder) {

                    if (player.getPlayerState() != PlayerState.SUSPENDED) {

                        model.setCurrentPlayer(player);

                        break;

                    }

                }


                controller.getActionChecker().resetActionList();

                controller.getActionChecker().setCurrentPlayer();

                model.getCurrentPlayer().setPlayerState(PlayerState.DOACTION);

                controller.startTimer(model.getCurrentPlayer());
            }

            else if (model.getCurrentTurn() == 4) {

                setSkippedPlayer(model, controller);

            }
        }

        else {

            int indexOfNextPlayer = turnOrder.indexOf(currentPlayer) + 1;

            boolean b = true;

            while (b) {

                for (; indexOfNextPlayer < turnOrder.size(); indexOfNextPlayer++) {

                    if (turnOrder.get(indexOfNextPlayer).getPlayerState() != PlayerState.SUSPENDED) {

                        b = false;

                        break;
                    }

                }

                if (b) indexOfNextPlayer = 0;

            }

            controller.chooseCurrentPlayer(indexOfNextPlayer);
        }
    }


    private void setSkippedPlayer(Model model, Controller controller) {

        if (model.getSkippedTurnPlayers().isEmpty()) {

            model.setGameState(GameState.RUNNING);

            controller.handleEndRound();
        }

        else {

            model.setGameState(GameState.CHECKONSKIPPED);

            Player newCurrentPlayer = null; //ho dovuto inizializzare per lo stesso motivo

            List<Player> skippedPlayers = model.getSkippedTurnPlayers();

            for (Player skippedPlayer : skippedPlayers) {

                if (skippedPlayer.getPlayerState() != PlayerState.SUSPENDED) {
                    newCurrentPlayer = skippedPlayer;
                    skippedPlayers.remove(skippedPlayer);
                    break;
                }

            }

            if(newCurrentPlayer != null){

                model.setCurrentPlayer(newCurrentPlayer);

                controller.getActionChecker().resetActionList();

                controller.getActionChecker().setCurrentPlayer();

                newCurrentPlayer.setPlayerState(PlayerState.DOACTION);

                model.notifyEndMove();

                controller.startTimer(newCurrentPlayer);

            }

            else{

                model.setGameState(GameState.RUNNING);

                skippedPlayers.clear();

                controller.handleEndRound();

            }

        }
    }

    private boolean seeIfLastPlayer(List<Player> turnOrder, Player currentPlayer) {

        int indexOfCurrentPlayer = turnOrder.indexOf(currentPlayer);

        if (indexOfCurrentPlayer == turnOrder.size()-1) {

            return true;
        }

        else {

            for (indexOfCurrentPlayer = indexOfCurrentPlayer + 1; indexOfCurrentPlayer < turnOrder.size(); indexOfCurrentPlayer++) {

                if (turnOrder.get(indexOfCurrentPlayer).getPlayerState() != PlayerState.SUSPENDED) {
                    return false;
                }

                if (turnOrder.get(indexOfCurrentPlayer).getPlayerState() == PlayerState.SUSPENDED
                        && indexOfCurrentPlayer == turnOrder.size() - 1) {

                    return true;
                }
            }

            return false;

        }

    }
}