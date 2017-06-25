package it.polimi.ingsw.GC_29.Client.GUI;

import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class PossibleCostsChange {

    private Map<Integer, String> possibleCosts;

    public PossibleCostsChange(Map<Integer, String> possibleCosts) {
        this.possibleCosts = possibleCosts;
    }

    public Map<Integer, String> getPossibleCosts() {
        return possibleCosts;
    }
}
