package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian on 07/06/2017.
 */
public class GetValidActions extends Query<Map<Integer, String>> {

    @Override
    public Map<Integer, String> perform(Model model) {

        ArrayList<Action> actionList = model.getCurrentPlayer().getCurrentValidActionsList();

        Map<Integer, String> validActions = new HashMap<>();

        for (Action action : actionList) {

            if(action.isValid()){
                int tempIndex = actionList.indexOf(action);

                String tempPrint = action.toString();

                validActions.put(tempIndex, tempPrint);
            }

        }

        return validActions;
    }
}
