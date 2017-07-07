package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.LeaderCard;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

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
    public List<String> perform(Model model) {

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
