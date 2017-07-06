package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.DevelopmentCard;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.WorkAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetCardsForWorkers extends Query<Map<Integer, ArrayList<String>>> {

    @Override
    public Map<Integer, ArrayList<String>> perform(GameStatus model) {

        Map<Integer, ArrayList<DevelopmentCard>> cardsForWorkersMap = ((WorkAction)model.getCurrentPlayer().getCurrentAction()).getCardsForWorkers();

        Map<Integer, ArrayList<String>> cardMap = new HashMap<>();

        for (Map.Entry<Integer, ArrayList<DevelopmentCard>> entry : cardsForWorkersMap.entrySet()) {

            ArrayList<DevelopmentCard> cards = entry.getValue();

            cardMap.put(entry.getKey(), new ArrayList<>());

            for (DevelopmentCard card : cards) {

                cardMap.get(entry.getKey()).add(card.toString());
            }
        }

        /*for(Integer workersIndex : cardsForWorkersMap.keySet()){

            ArrayList<DevelopmentCard> cards = cardsForWorkersMap.get(workersIndex);

            cardMap.put(workersIndex, new ArrayList<>());

            for (DevelopmentCard card : cards) {

                cardMap.get(workersIndex).add(card.toString());
            }
        }*/

        return cardMap;
    }
}
