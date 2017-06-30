package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.Era;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Components.SpecialBonusAndMalus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Filter;
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

        Player player = model.getPlayer(playerColor);

        if (answer) {

            int playerFaithPoints = player.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS);

            GoodSet goodSet = new GoodSet(0,0,0,0,model.getGameBoard().getFaithPointsTrack().getVictoryPointsPerSlot()[playerFaithPoints],0,0);

            if (Filter.applySpecial(player, SpecialBonusAndMalus.FIVEVICTORYPOINTSIFPRAY)) {

                goodSet.addGoodSet(new GoodSet(0,0,0,0,5,0,0));
            }

            Filter.apply(player, goodSet);

            goodSet.addGoodSet(new GoodSet(0,0,0,0, 0, 0, - playerFaithPoints));

            player.updateGoodSet(goodSet);
        }

        else controller.executeTiles(player);

        controller.praying();

        if (player.getPlayerState() != PlayerState.SUSPENDED) {

            player.setPlayerState(PlayerState.WAITING);

        }

        if (controller.getPlayersPraying() == 0) {

            model.setGameState(GameState.RUNNING);

            if (model.getCurrentEra() != Era.THIRD) {

                controller.setNewRound();
            }

            else controller.endGame();
        }

    }


}
