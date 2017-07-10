package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.Input.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.*;

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
    public void perform(Model model, Controller controller) {

        controller.stopTimer();

        Player player = model.getPlayer(playerColor);

        model.setCurrentPlayer(player);

        if (answer) {

            System.out.println("PERFORMING PRAY ANSWER YES\n");

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

        player.setPlayerState(PlayerState.WAITING);


        if (controller.getPlayersPraying() == 0) {

            model.setGameState(GameState.RUNNING);

            if (model.getCurrentEra() != Era.THIRD) {

                controller.setNewRound();
            }

            else controller.endGame();
        }

    }


}
