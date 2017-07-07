package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.DevelopmentCard;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.WorkAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetCardsForWorkers extends Query<Map<Integer, ArrayList<String>>> {

    @Override
    public Map<Integer, ArrayList<String>> perform(Model model) {

        Map<Integer, ArrayList<DevelopmentCard>> cardsForWorkersMap = ((WorkAction)model.getCurrentPlayer().getCurrentAction()).getCardsForWorkers();

        Map<Integer, ArrayList<String>> cardMap = new HashMap<>();

        for (Map.Entry<Integer, ArrayList<DevelopmentCard>> cardsForWorkersEntry : cardsForWorkersMap.entrySet()) {

            ArrayList<DevelopmentCard> cards = cardsForWorkersEntry.getValue();

            cardMap.put(cardsForWorkersEntry.getKey(), new ArrayList<>());

            for (DevelopmentCard card : cards) {
                cardMap.get(cardsForWorkersEntry.getKey()).add(card.toString());
            }


        }


        /* PRIMA DEL CICLO FOR C'ERA QUESTO
        for(Integer workersIndex : cardsForWorkersMap.keySet()){

            ArrayList<DevelopmentCard> cards = cardsForWorkersMap.get(workersIndex);

            cardMap.put(workersIndex, new ArrayList<>());

            for (DevelopmentCard card : cards) {

                cardMap.get(workersIndex).add(card.toString());
            }
        }
        */

        return cardMap;
    }
}
