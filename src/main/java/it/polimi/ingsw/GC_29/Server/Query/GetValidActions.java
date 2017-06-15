package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Server.Query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian on 07/06/2017.
 */
public class GetValidActions extends Query<Map<Integer, String>> {

    @Override
    public Map<Integer, String> perform(GameStatus model) {

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
