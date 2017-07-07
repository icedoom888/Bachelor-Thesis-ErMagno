package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.CardColor;
import it.polimi.ingsw.GC_29.Model.DevelopmentCard;
import it.polimi.ingsw.GC_29.Controllers.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetDevelopmentCard extends Query<List<String>> {

    CardColor playerCardColor;

    public GetDevelopmentCard(CardColor playerCardColor) {
        this.playerCardColor = playerCardColor;
    }


    @Override
    public List<String> perform(Model model) {

        List<String> returnList = new ArrayList<>();

        List<DevelopmentCard> playerCards = Arrays.asList(model.getCurrentPlayer().getPersonalBoard().getLane(playerCardColor).getCards());

        for (DevelopmentCard playerCard : playerCards) {

            if(playerCard!= null){
                returnList.add(playerCard.toString());
            }

        }

        return returnList;
    }
}
