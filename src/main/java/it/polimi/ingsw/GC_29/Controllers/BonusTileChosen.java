package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.BonusTile;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.*;

/**
 * Created by Christian on 19/06/2017.
 */
public class BonusTileChosen extends Input {

    private int bonusTileIndex;


    public BonusTileChosen(int bonusTileChosen) {

        bonusTileIndex = bonusTileChosen;
    }

    public BonusTileChosen() {

        bonusTileIndex = -1;
    }


    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        controller.stopTimer();

        BonusTile bonusTile;

        if (bonusTileIndex != -1) {

            bonusTile = model.getBonusTileMap().remove(bonusTileIndex);
        }

        else {

            Map<Integer, BonusTile> bonusTileMap = model.getBonusTileMap();

            List<BonusTile> bonusTileList = new ArrayList<>(bonusTileMap.values());

            bonusTile = bonusTileList.get(0);

            bonusTileMap.remove(bonusTile);
        }

        model.getCurrentPlayer().getPersonalBoard().setBonusTile(bonusTile);

        controller.getPlayerBonusTileIndexMap().put(model.getCurrentPlayer(), bonusTileIndex);

        int playerToChooseBonusTileIndex = controller.getCurrentBonusTileIndexPlayer();

        if(playerToChooseBonusTileIndex == 0) {

            beginMatch(model, controller);

        }

        else {

            playerToChooseBonusTileIndex--;

            controller.setCurrentBonusTileIndexPlayer(playerToChooseBonusTileIndex);

            model.getCurrentPlayer().setPlayerState(PlayerState.WAITING);


            model.setCurrentPlayer(model.getTurnOrder().get(playerToChooseBonusTileIndex));


            model.getCurrentPlayer().setPlayerState(PlayerState.CHOOSE_BONUS_TILE);

            controller.startTimer(model.getCurrentPlayer());


        }
    }

    private void beginMatch(GameStatus model, Controller controller) {

        List<Player> turnOrder = model.getTurnOrder();

        boolean firstPlayerChosen = false;

        for (Player player : turnOrder) {

            if(turnOrder.indexOf(player) == 0){

                if (player.getPlayerState() != PlayerState.SUSPENDED) {

                    firstPlayerChosen = true;

                    model.setCurrentPlayer(player);

                    controller.getActionChecker().setCurrentPlayer();

                    player.setPlayerState(PlayerState.THROWDICES);

                    controller.startTimer(player);

                }

            }

            else {

                if (player.getPlayerState() != PlayerState.SUSPENDED) {

                    if (firstPlayerChosen) {
                        player.setPlayerState(PlayerState.WAITING);
                    }

                    else {

                        player.setPlayerState(PlayerState.THROWDICES);
                        firstPlayerChosen = true;
                    }
                }
            }

        }
        
    }
}
