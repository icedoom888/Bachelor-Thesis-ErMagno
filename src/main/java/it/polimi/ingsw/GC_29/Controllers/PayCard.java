package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.TowerAction;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class PayCard extends Input {

    int costChosen;

    public PayCard(int costChosen) {
        this.costChosen = costChosen;
    }

    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {
        TowerAction towerAction = (TowerAction)model.getCurrentPlayer().getCurrentAction();
        towerAction.setCostChosen(costChosen);

        towerAction.execute();
        controller.handleEndAction();
    }
}
