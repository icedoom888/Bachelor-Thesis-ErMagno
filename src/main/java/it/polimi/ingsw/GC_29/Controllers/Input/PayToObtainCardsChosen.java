package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.Input.Input;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.WorkAction;

import java.util.Map;

/**
 * Created by Christian on 17/06/2017.
 */
public class PayToObtainCardsChosen extends Input {

    private Map<String, Integer> activatedCardMap;

    public PayToObtainCardsChosen(Map<String, Integer> activatedCardMap) {

       this.activatedCardMap = activatedCardMap;

    }


    @Override
    public void perform(Model model, Controller controller) {

        WorkAction workAction = (WorkAction) model.getCurrentPlayer().getCurrentAction();

        workAction.setPayToObtainCardsChosen(activatedCardMap);

        workAction.execute();

        workAction.getCardsForWorkers().clear();

        controller.handleEndAction();
    }
}
