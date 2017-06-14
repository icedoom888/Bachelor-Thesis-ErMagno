package it.polimi.ingsw.GC_29.Server;

import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Christian on 07/06/2017.
 */
public class GetValidActions extends Query<HashMap<Integer, String>> {

    @Override
    public HashMap<Integer, String> perform(GameStatus model) {

        ArrayList<Action> actionList = model.getCurrentPlayer().getCurrentValidActionsList();

        HashMap<Integer, String> validActions = new HashMap<>();

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
