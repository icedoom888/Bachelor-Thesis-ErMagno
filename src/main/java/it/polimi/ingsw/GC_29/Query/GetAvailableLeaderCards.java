package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.LeaderCard;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 03/07/17.
 */
public class GetAvailableLeaderCards extends Query<Map<Integer, Boolean>> {

    PlayerColor playerColor;

    public GetAvailableLeaderCards(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }


    @Override
    public Map<Integer, Boolean> perform(GameStatus model) {

        System.out.println("Performing available leader query\n");

        for (Player player : model.getTurnOrder()) {

            if (player.getPlayerColor() == playerColor) {

                ArrayList<LeaderCard> leaderCards = player.getLeaderCards();

                HashMap<Integer, Boolean> leadersAvailable = new HashMap<>();

                System.out.println("SIZE LEADER CARDS: " + leaderCards.size());

                for (LeaderCard leaderCard : leaderCards) {

                    if (!(leaderCard.isActivated() || leaderCard.isDiscarded())) {

                        leadersAvailable.put(leaderCards.indexOf(leaderCard), leaderCard.isPossible(player));

                        System.out.println(leadersAvailable);
                    }
                }


                return leadersAvailable;

            }
        }

        return null;
    }
}
