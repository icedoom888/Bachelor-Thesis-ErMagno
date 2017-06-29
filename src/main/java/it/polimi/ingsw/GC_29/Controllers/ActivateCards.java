package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.WorkAction;

/**
 * Created by Christian on 16/06/2017.
 */
public class ActivateCards extends Input {

    private int workersChosen;

    public ActivateCards(int workersChosen) {

        this.workersChosen = workersChosen;
    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        System.out.println("perform activate cards");

        WorkAction workAction = (WorkAction) model.getCurrentPlayer().getCurrentAction();

        if(workAction.handlePayToObtainCards(workersChosen)){

            System.out.println("puoi attivare la payToObtain");

            model.getCurrentPlayer().setPlayerState(PlayerState.ACTIVATE_PAY_TO_OBTAIN_CARDS);

        }

        else{

            workAction.execute();

            controller.handleEndAction();

        }
    }
}
