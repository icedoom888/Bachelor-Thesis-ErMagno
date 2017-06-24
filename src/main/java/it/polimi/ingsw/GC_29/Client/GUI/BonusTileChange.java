package it.polimi.ingsw.GC_29.Client.GUI;

import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class BonusTileChange {

    private Map<Integer, String> bonusTiles;

    public BonusTileChange(Map<Integer, String> bonusTiles) {

        this.bonusTiles = bonusTiles;
    }

    public Map<Integer, String> getBonusTiles() {
        return bonusTiles;
    }
}
