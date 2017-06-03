package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

/**
 * Created by Christian on 21/05/2017.
 */
public class ValidationActionTurnState implements TurnState {


    /**
     * this method creates an action and if the action is valid the method sets the new state for the PlayerController,
     * that is the executeActionState. If the player decides to skip the action (with the method isPlaceFamilyMemberAction)
     * this method brings the playerController into the EndTurnState.
     *
     * @param wrapper the playerController reference
     */
    @Override
    public void executeState(PlayerController wrapper) {

        boolean actionSelected = false;

        boolean skipTurn = false;

        Action currentAction = null;

        while (!actionSelected){

            ActionChecker.getInstance().resetActionListExceptPlayer();

            if(wrapper.isPlaceFamilyMemberAction()){

                // TODO: da inserire richiesta di attivazione carta leader

                FamilyPawn pawnSelected = AskFamilyPawn();

                ActionChecker.getInstance().setValidActionForFamilyPawn(pawnSelected);

                actionSelected = wrapper.choseAction();

            }

            else{

                skipTurn = true;
                break;

            }

        }

        if(!skipTurn){

            //wrapper.getPlayerStatus().setCurrentAction(currentAction);

            wrapper.setCurrentTurnState(new ExecuteActionTurnState());
        }

        else {
            wrapper.setCurrentTurnState(new EndTurnState());
        }

    }

    private FamilyPawn AskFamilyPawn() {

        return new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 4); // metodo di interfaccia, questo return è fasullo
    }


    private ZoneType askForAction() {
        return ZoneType.GREENTOWER; // prova
    }


}
