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

        if (currentPlayer.getPlayerState() != PlayerState.SUSPENDED) {
            currentPlayer.setPlayerState(PlayerState.WAITING);
        }

        List<Player> turnOrder = model.getTurnOrder();

        if (model.getGameState() == GameState.CHECKONSKIPPED) {
            setSkippedPlayer(model, controller);
        }

        else if (turnOrder.indexOf(currentPlayer) == turnOrder.size()-1) {

            System.out.println("Current player: " + currentPlayer.getPlayerID());
            System.out.println(turnOrder.indexOf(currentPlayer));


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
            }

            else if (model.getCurrentTurn() == 4) {

                System.out.println("Setting Skipped players");

                //TODO: anche qui
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


    private void setSkippedPlayer(GameStatus model, Controller controller) throws Exception {

        if (model.getSkippedTurnPlayers().isEmpty()) {

            System.out.println("nessuno skippa");

            model.setGameState(GameState.RUNNING);
            controller.handleEndRound();
        }

        else {

            model.setGameState(GameState.CHECKONSKIPPED);

            Player newCurrentPlayer = null; //ho dovuto inizializzare per lo stesso motivo

            boolean b = true;

            while (b) {

                List<Player> skippedPlayers = model.getSkippedTurnPlayers();

                for (Player skippedPlayer : skippedPlayers) {

                    if (skippedPlayer.getPlayerState() != PlayerState.SUSPENDED) {

                        b = false;
                        newCurrentPlayer = skippedPlayer;
                        skippedPlayers.remove(skippedPlayer);
                        break;
                    }

                    skippedPlayers.remove(skippedPlayer);
                }

            }

            //Player newCurrentPlayer = model.getSkippedTurnPlayers().remove(0);

            model.setCurrentPlayer(newCurrentPlayer);

            controller.getActionChecker().resetActionList();

            controller.getActionChecker().setCurrentPlayer();

            newCurrentPlayer.setPlayerState(PlayerState.DOACTION);

        }
    }
}
