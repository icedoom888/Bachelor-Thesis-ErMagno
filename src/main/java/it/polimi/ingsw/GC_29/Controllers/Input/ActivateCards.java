package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.WorkAction;
import it.polimi.ingsw.GC_29.Model.Player;

/**
 * Created by Christian on 16/06/2017.
 */
public class ActivateCards extends Input {

    private int workersChosen;

    public ActivateCards(int workersChosen) {

        this.workersChosen = workersChosen;
    }


    /**
     * After a player has chose the cards he wants to activate during a WorkAction,
     * the controller performs this input and then calls handlePayToObtainCards
     * @param model
     * @param controller
     */
    @Override
    public void perform(Model model, Controller controller) {

        Player currentPlayer = model.getCurrentPlayer();

        WorkAction workAction = (WorkAction) currentPlayer.getCurrentAction();


        controller.handlePayToObtainCards(workAction, currentPlayer, workersChosen);

    }
}
