package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.List;

/**
 * Created by Christian on 19/06/2017.
 */
public class BonusTileChosen extends Input {

    private int bonusTileIndex;

    public BonusTileChosen(int bonusTileChosen) {

        bonusTileIndex = bonusTileChosen;
    }


    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        BonusTile bonusTile = model.getBonusTileMap().remove(bonusTileIndex);

        model.getCurrentPlayer().getPersonalBoard().setBonusTile(bonusTile);

        int playerToChooseBonusTileIndex = controller.getCurrentBonusTileIndexPlayer();

        if(playerToChooseBonusTileIndex == 0) {

            beginMatch(model, controller);

        }

        else{

            playerToChooseBonusTileIndex--;

            controller.setCurrentBonusTileIndexPlayer(playerToChooseBonusTileIndex);

            model.getCurrentPlayer().setPlayerState(PlayerState.WAITING);

            model.setCurrentPlayer(model.getTurnOrder().get(playerToChooseBonusTileIndex));

            model.getCurrentPlayer().setPlayerState(PlayerState.CHOOSE_BONUS_TILE);

        }
    }

    private void beginMatch(GameStatus model, Controller controller) {

        List<Player> turnOrder = model.getTurnOrder();

        for (Player player : turnOrder) {

            if(turnOrder.indexOf(player) == 0){

                model.setCurrentPlayer(player);

                controller.getActionChecker().setCurrentPlayer();

                player.setPlayerState(PlayerState.THROWDICES);
            }

            else {

                player.setPlayerState(PlayerState.WAITING);
            }

        }
        
    }
}
