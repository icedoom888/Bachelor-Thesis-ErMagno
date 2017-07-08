package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Change.BonusTileChangeGui;
import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.BonusTile;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;

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


    /**
     * This perform stops the timer for the bonusTile.
     * As every starting turn input, it checks if there are players disconnected
     * or that want to reconnect.
     * Then it gives the bonus tile chosen from the client to the correct player.
     * If there are other players who have to chose the tile, it will be the turn of another one.
     * If, insted, everyone chose his tile, beginMatch() is called.
     *
     * @param model
     * @param controller
     */
    @Override
    public void perform(Model model, Controller controller) {

        controller.stopTimer();

        model.notifyEndMove();

        controller.handleReconnectedPlayers();

        controller.handleDisconnectedPlayers();

        BonusTile bonusTile;

        if (bonusTileIndex != -1) {

            bonusTile = model.getBonusTileMap().remove(bonusTileIndex);

        }

        else {

            Map<Integer, BonusTile> bonusTileMap = model.getBonusTileMap();

            List<BonusTile> bonusTileList = new ArrayList<>(bonusTileMap.values());

            bonusTile = bonusTileList.get(0);

            for (Map.Entry<Integer, BonusTile> integerBonusTileEntry : bonusTileMap.entrySet()) {

                if (integerBonusTileEntry.getValue() == bonusTile) {
                    model.getCurrentPlayer().notifyObserver(new BonusTileChangeGui(integerBonusTileEntry.getKey()));
                    break;
                }
            }


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

           // controller.setCurrentBonusTileIndexPlayer(playerToChooseBonusTileIndex);

            Boolean isNotSuspended = false;

            for(; playerToChooseBonusTileIndex >= 0; playerToChooseBonusTileIndex--){

                if(model.getTurnOrder().get(playerToChooseBonusTileIndex).getPlayerState() == PlayerState.SUSPENDED){

                    List<Integer> indexKeys = Arrays.asList(model.getBonusTileMap().keySet().toArray(new Integer[model.getBonusTileMap().size()]));

                    int bonusIndex = indexKeys.get(0);

                    bonusTile = model.getBonusTileMap().get(bonusIndex);

                    model.getBonusTileMap().remove(bonusTile);

                    controller.getPlayerBonusTileIndexMap().put(model.getTurnOrder().get(playerToChooseBonusTileIndex), bonusIndex);

                }

                else{

                    isNotSuspended = true;

                    controller.setCurrentBonusTileIndexPlayer(playerToChooseBonusTileIndex);
                    break;
                }
            }

            if(isNotSuspended){

                model.getCurrentPlayer().setPlayerState(PlayerState.WAITING);


                model.setCurrentPlayer(model.getTurnOrder().get(playerToChooseBonusTileIndex));


                model.getCurrentPlayer().setPlayerState(PlayerState.CHOOSE_BONUS_TILE);

                controller.startTimer(model.getCurrentPlayer());

            }

            else{

                beginMatch(model, controller);

            }




        }
    }

    /**
     * This method finds out the first player that has to throw the dices (it can happen
     * that the first player (and the second, and the third...) is suspended) and set his state
     * to throw dices and all the other players state to waiting
     *
     * @param model
     * @param controller
     */
    private void beginMatch(Model model, Controller controller) {

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
