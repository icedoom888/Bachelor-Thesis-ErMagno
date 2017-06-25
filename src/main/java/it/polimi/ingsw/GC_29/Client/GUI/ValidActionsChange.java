package it.polimi.ingsw.GC_29.Client.GUI;

import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class ValidActionsChange {

    private Map<Integer, String> validActionList;

    public ValidActionsChange(Map<Integer, String> validActionList) {
        this.validActionList = validActionList;
    }

    public Map<Integer, String> getValidActionList() {
        return validActionList;
    }
}
