package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Christian on 21/05/2017.
 */
public class ValidationActionState implements State {

    @Override
    public void executeState(PlayerController wrapper) {

        boolean validAction = false;

        boolean skipTurn = false;

        Action currentAction = null;

        while (!validAction){

            if(wrapper.isPlaceFamilyMemberAction()){

                // TODO: da inserire richiesta di attivazione carta leader

                ZoneType typeSelected = askForAction();

                FamilyPawn pawnSelected = AskFamilyPawn();

                currentAction = FactoryAction.getAction(typeSelected, pawnSelected, wrapper.getPlayerStatus());

                validAction = currentAction.isPossible();

            }

            else{

                skipTurn = true;
                break;

            }

        }

        if(!skipTurn){

            wrapper.getPlayerStatus().setCurrentAction(currentAction);

            wrapper.setCurrentState(new ExecuteActionState());
        }

        else {
            wrapper.setCurrentState(new EndTurnState());
        }

    }


    /**
     * ask if the player wants to place a family member or skip his round
     * @return
     */

    private FamilyPawn AskFamilyPawn() {

        return new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 4); // metodo di interfaccia, questo return è fasullo
    }


    private ZoneType askForAction() {
        return ZoneType.BLUETOWER; // prova
    }


}
