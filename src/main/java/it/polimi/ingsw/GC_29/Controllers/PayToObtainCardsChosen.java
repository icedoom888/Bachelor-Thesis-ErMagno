package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.WorkAction;

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
    public void perform(GameStatus model, Controller controller) throws Exception {

        System.out.println("performing PayToObtainCardsChosen");

        WorkAction workAction = (WorkAction) model.getCurrentPlayer().getCurrentAction();

        workAction.setPayToObtainCardsChosen(activatedCardMap);

        workAction.execute();

        controller.handleEndAction();
    }
}
