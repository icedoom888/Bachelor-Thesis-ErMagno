package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Components.LeaderCard;
import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Lorenzotara on 02/07/17.
 */
public class LeaderAction extends Input {

    PlayerColor playerColor;
    int leaderIndex;
    boolean activate;

    public LeaderAction(boolean b, int index, PlayerColor playerColor) {
        this.leaderIndex = index;
        this.activate = b;
        this.playerColor = playerColor;
    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        System.out.println("performing leader action \n");

        for (Player player : model.getTurnOrder()) {

            if (player.getPlayerColor() == playerColor) {
                LeaderCard leaderCard = player.getLeaderCards().get(leaderIndex);

                if (activate) {

                    leaderCard.execute(player);
                    }


                else {
                    player.getLeaderCards().get(leaderIndex).setDiscarded();
                    player.setLastState(player.getPlayerState());
                    player.setPlayerState(PlayerState.DISCARDINGLEADER);
                }

                break;
            }
        }
    }
}
