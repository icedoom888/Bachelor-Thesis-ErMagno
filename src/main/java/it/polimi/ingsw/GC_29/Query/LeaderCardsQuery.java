package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.LeaderCard;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 03/07/2017.
 */
public class LeaderCardsQuery extends Query<List<String>> {

    private PlayerColor playerColor;

    public LeaderCardsQuery(PlayerColor playerColor){

        this.playerColor = playerColor;
    }

    @Override
    public List<String> perform(GameStatus model) {

        System.out.println("\n\nPLAYER COLOR: " + playerColor);

        Player player = model.getPlayer(playerColor);

        List<LeaderCard> leaderCards = player.getLeaderCards();

        List<String> leaderCardsList = new ArrayList<>();

        for (LeaderCard leaderCard : leaderCards) {

            if(!(leaderCard.isActivated() || leaderCard.isDiscarded())){

                leaderCardsList.add(leaderCard.toString());

            }

        }

        return leaderCardsList;

    }
}
