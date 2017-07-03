package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.LeaderCard;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 03/07/2017.
 */
public class LeaderCardMapQuery extends Query<Map<Integer, Boolean>> {

    private PlayerColor playerColor;

    public LeaderCardMapQuery(PlayerColor playerColor){

        this.playerColor = playerColor;
    }

    @Override
    public Map<Integer, Boolean> perform(GameStatus model) {

        Player player = model.getPlayer(playerColor);

        List<LeaderCard> leaderCards = player.getLeaderCards();

        Map<Integer, Boolean> leaderCardMap = new HashMap<>();

        for (LeaderCard leaderCard : leaderCards) {

            int index = leaderCards.indexOf(leaderCard);

            leaderCardMap.put(index, leaderCard.isPossible(player));
        }

        return leaderCardMap;
    }
}
