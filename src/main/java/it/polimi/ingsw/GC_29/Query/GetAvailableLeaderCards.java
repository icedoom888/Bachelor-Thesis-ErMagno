package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.LeaderCard;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

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
    public Map<Integer, Boolean> perform(Model model) {

        HashMap<Integer, Boolean> leadersAvailable = new HashMap<>();

        for (Player player : model.getTurnOrder()) {

            if (player.getPlayerColor() == playerColor) {

                ArrayList<LeaderCard> leaderCards = player.getLeaderCards();


                for (LeaderCard leaderCard : leaderCards) {

                    if (!(leaderCard.isActivated() || leaderCard.isDiscarded())) {

                        leadersAvailable.put(leaderCards.indexOf(leaderCard), leaderCard.isPossible(player));

                    }
                }

            }
        }

        return leadersAvailable;
    }
}
