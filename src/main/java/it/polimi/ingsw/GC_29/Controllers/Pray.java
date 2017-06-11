package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Era;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Lorenzotara on 10/06/17.
 */
public class Pray extends Input {

    private boolean answer;
    private PlayerColor playerColor;

    public Pray(boolean b, PlayerColor playerColor) {

        this.answer = b;
        this.playerColor = playerColor;
    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        Player player = searchPlayer(model);

        if (answer) {
            int playerFaithPoints = player.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS);
            player.updateGoodSet(new GoodSet(0,0,0,0,model.getGameBoard().getFaithPointsTrack().getVictoryPointsPerSlot()[playerFaithPoints], 0, -playerFaithPoints));
        }

        else controller.executeTiles(player);


        controller.praying();
        player.setPlayerState(PlayerState.WAITING);

        if (controller.getPlayersPraying() == 0) {
            model.setGameState(GameState.RUNNING);
            if (model.getCurrentEra() != Era.THIRD) controller.setNewRound(); //TODO: o handleEndRound?
            else controller.endGame();
        }

    }

    private Player searchPlayer(GameStatus model) {
        List<Player> turnOrder = model.getTurnOrder();

        for (Player player : turnOrder) {
            if (player.getPlayerColor() == playerColor) return player;
        }

        return null;
    }
}
