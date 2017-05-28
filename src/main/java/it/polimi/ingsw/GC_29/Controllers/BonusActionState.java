package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;

/**
 * Created by Christian on 21/05/2017.
 */
public class BonusActionState implements State {

    /**
     * this method creates an action from an ActionEffect gained by the currentPlayer. If the action is valid the method sets the new state for the PlayerController,
     * that is the executeActionState. If the player decides to skip the action (with the method isPlaceFamilyMemberAction)
     * this method brings the playerController into the EndTurnState if there no more bonusAction for the player, otherwise (if the player skipped the BonusAction and there are
     * other BonusAction for the currentPlayer) the state of the playerController remains in the BonusActionState and the next bonusAction is processed.
     *
     * @param wrapper the playerController reference
     */
    @Override
    public void executeState(PlayerController wrapper) {

        boolean validAction = false;

        boolean skipBonusAction = false;

        Action currentAction = null;

        ActionEffect currentBonusAction = wrapper.getBonusActionEffect();

        // temporary bonusMalusOn cost setted in the playerStatus
        if(currentBonusAction.getBonusAndMalusOnCost() != null){

            wrapper.getPlayerStatus().getCurrentBonusActionBonusMalusOnCostList().add(currentBonusAction.getBonusAndMalusOnCost());

        }

        while (!validAction) {

            if (wrapper.isPlaceFamilyMemberAction()) {

                FamilyPawn familyPawn = new FamilyPawn(wrapper.getPlayerStatus().getPlayerColor(), FamilyPawnType.BONUS, currentBonusAction.getActionValue());

                ZoneType zoneType = currentBonusAction.getType();

                currentAction = FactoryAction.getAction(zoneType, familyPawn, wrapper.getPlayerStatus());

                validAction = currentAction.isPossible();

            }

            else {

                skipBonusAction = true;
                break;

            }

        }

        if (!skipBonusAction) {

            wrapper.getPlayerStatus().setCurrentAction(currentAction);

            wrapper.setCurrentState(new ExecuteActionState());

        }

        else if(!wrapper.checkPresenceBonusActionEffect()) {

            wrapper.setCurrentState(new EndTurnState());
        }

        // altrimenti il currentState rimane il BonusActionState!
    }

        /*
        *
        * TODO: qui vi è da prendere la decisione se creare nei diversi actionBuilder un overloading sul costruttore che prende in ingresso un ActionEffect e o gestisce
        * oppure spostare le richieste comuni come la pedina e il numero di worker prima dell'actionBuilder, creare un prototipo di azione e "filtrarla" nei
        * towerActionBuilder WorkActionBuilder, e market dato che sono le uniche che hanno biosgno di ulteriori specifiche.
         */

    }
