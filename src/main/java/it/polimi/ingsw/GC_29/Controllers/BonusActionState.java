package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;

/**
 * Created by Christian on 21/05/2017.
 */
public class BonusActionState implements State {

    @Override
    public void executeState(PlayerController wrapper) {

        boolean validAction = false;

        ActionEffect currentBonusAction = wrapper.getBonusActionEffect();

        while (!validAction){

            ActionEffect processedBonusAction = new ActionEffect(currentBonusAction);

            processedBonusAction.execute(wrapper.getPlayerStatus()); // chiedo cosa vuole se ho alternative

            wrapper.setActionBuilder(processedBonusAction); // costruttore con overloading

            wrapper.setCurrentAction(wrapper.getActionBuilder().build());

            validAction = wrapper.getCurrentAction().isPossible();
        }

        wrapper.setCurrentState(new ExecuteActionState());

        /*
        *
        * TODO: qui vi è da prendere la decisione se creare nei diversi actionBuilder un overloading sul costruttore che prende in ingresso un ActionEffect e o gestisce
        * oppure spostare le richieste comuni come la pedina e il numero di worker prima dell'actionBuilder, creare un prototipo di azione e "filtrarla" nei
        * towerActionBuilder WorkActionBuilder, e market dato che sono le uniche che hanno biosgno di ulteriori specifiche.
         */

    }
}
