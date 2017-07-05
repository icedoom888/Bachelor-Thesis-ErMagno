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

        //TODO: qui devi inserire il controllo sulla lista dei riconessi e chiamare il metodo handleReconnectedPlayers

        if(!(controller.getPlayerReconnected().isEmpty())){

            controller.handleReconnectedPlayers();
        }
        
        controller.stopTimer();

        Player currentPlayer = model.getCurrentPlayer();

        System.out.println("SONO DENTRO PERFORM END TURN, IL CURRENT PLAYER CHE HA CHIAMATO END TURN E' " + currentPlayer.getPlayerID() + " color " + currentPlayer.getPlayerColor());

        currentPlayer.setPlayerState(PlayerState.WAITING);
        System.out.println("\n\nTURNO: " + model.getCurrentTurn());
        System.out.println("END TURN, NOME " + currentPlayer.getPlayerID() + " colore " + currentPlayer.getPlayerColor()+"\n\n");
        System.out.println("INDICE DEL PLAYER CHE STA FINENDO IL TURNO: " + model.getTurnOrder().indexOf(currentPlayer));

        System.out.println("\n\nTURN ORDER UGUALE A: " + model.getTurnOrder());

        List<Player> turnOrder = model.getTurnOrder();

        if (model.getGameState() == GameState.CHECKONSKIPPED) {
            setSkippedPlayer(model, controller);
        }

        //else if (turnOrder.indexOf(currentPlayer) == turnOrder.size()-1) {
        else if (seeIfLastPlayer(turnOrder, currentPlayer)) {

            System.out.println("TURNO MINORE DI QUATTRO, SETTO IL PLAYER CORRENTE\n\n");

            if (model.getCurrentTurn() < 4) {

                model.setCurrentTurn(model.getCurrentTurn()+1);

                turnOrder = model.getTurnOrder();

                for (Player player : turnOrder) {

                    if (player.getPlayerState() != PlayerState.SUSPENDED) {

                        model.setCurrentPlayer(player);

                        System.out.println("IL PLAYER CORRENTE è: " + player.getPlayerID() + "\n\n");

                        break;

                    }

                }


                controller.getActionChecker().resetActionList();

                controller.getActionChecker().setCurrentPlayer();

                model.getCurrentPlayer().setPlayerState(PlayerState.DOACTION);

                controller.startTimer(model.getCurrentPlayer());
            }

            else if (model.getCurrentTurn() == 4) {

                System.out.println("TURNO UGUALE A QUATTRO, SETTO GLI SKIPPED\n\n");

                setSkippedPlayer(model, controller);

            }
        }

        else {

            int indexOfNextPlayer = turnOrder.indexOf(currentPlayer) + 1;

            System.out.println("\n\nNON è L'ULTIMO TURNO, IL PLAYER CHE DEVE ESSERE SETTATO CORRENTE è: "
                    + turnOrder.get(indexOfNextPlayer) + "\n IL CUI INDICE è: " + indexOfNextPlayer);

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

            System.out.println("\n\nINDICE CHE PASSO AL CONTROLLER PER SCEGLIERE IL CURRENT PLAYER: " + indexOfNextPlayer);

            controller.chooseCurrentPlayer(indexOfNextPlayer);
        }
    }


    private void setSkippedPlayer(GameStatus model, Controller controller) throws Exception {

        if (model.getSkippedTurnPlayers().isEmpty()) {

            System.out.println("NESSUNO STA SKIPPANDO, VADO IN END ROUND \n\n");

            model.setGameState(GameState.RUNNING);

            controller.handleEndRound();
        }

        else {

            model.setGameState(GameState.CHECKONSKIPPED);

            Player newCurrentPlayer = null; //ho dovuto inizializzare per lo stesso motivo

            List<Player> skippedPlayers = model.getSkippedTurnPlayers();

            for (Player skippedPlayer : skippedPlayers) {

                boolean b = true;

                while (b) {

                    if (skippedPlayer.getPlayerState() != PlayerState.SUSPENDED) {

                        newCurrentPlayer = skippedPlayer;
                        skippedPlayers.remove(skippedPlayer);
                        break;
                    }
                }
            }

            //Player newCurrentPlayer = model.getSkippedTurnPlayers().remove(0);

            model.setCurrentPlayer(newCurrentPlayer);

            controller.getActionChecker().resetActionList();

            controller.getActionChecker().setCurrentPlayer();

            newCurrentPlayer.setPlayerState(PlayerState.DOACTION);

            controller.startTimer(newCurrentPlayer);

        }
    }

    private boolean seeIfLastPlayer(List<Player> turnOrder, Player currentPlayer) {

        System.out.println("\n\nSONO IN SEE IF LAST PLAYER");

        int indexOfCurrentPlayer = turnOrder.indexOf(currentPlayer);

        System.out.println("\n\nINDICE DEL CURRENT PLAYER: " + indexOfCurrentPlayer);


        if (indexOfCurrentPlayer == turnOrder.size()-1) {

            System.out.println("\n\nIL GIOCATORE è L'ULTIMO DEL TURNO: il giocatore è: " + currentPlayer.getPlayerID());

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
