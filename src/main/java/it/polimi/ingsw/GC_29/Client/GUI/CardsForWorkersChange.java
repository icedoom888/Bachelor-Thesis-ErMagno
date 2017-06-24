package it.polimi.ingsw.GC_29.Client.GUI;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class CardsForWorkersChange {

    private Map<Integer, ArrayList<String>> cardsForWorkers;


    public CardsForWorkersChange(Map<Integer, ArrayList<String>> cardsForWorkers) {
        this.cardsForWorkers = cardsForWorkers;
    }

    public Map<Integer, ArrayList<String>> getCardsForWorkers() {
        return cardsForWorkers;
    }
}
