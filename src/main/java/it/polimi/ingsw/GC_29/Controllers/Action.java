package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.ActionSpace;
import it.polimi.ingsw.GC_29.Components.ActionType;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public abstract class Action {
    private FamilyPawn pawnSelected;
    private ActionType actionSelected;
    private int workersSelected;
    private ActionSpace selectedActionSpace;
    private FamilyPawn temporaryPawn;
    private GoodSet tempGoodSet;
    private boolean realAction;

    public Action(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction) {
        this.pawnSelected = pawnSelected;
        this.actionSelected = actionSelected;
        this.workersSelected = workersSelected;
        this.temporaryPawn = new FamilyPawn(pawnSelected);
        this.tempGoodSet = tempGoodSet;
        this.realAction = realAction;
    }

    public FamilyPawn getPawnSelected() {
        return pawnSelected;
    }

    public ActionType getActionSelected() {
        return actionSelected;
    }

    public int getWorkersSelected() {
        return workersSelected;
    }

    public FamilyPawn getTemporaryPawn() {
        return temporaryPawn;
    }

    public GoodSet getTempGoodSet() {
        return tempGoodSet;
    }

    public boolean isRealAction() {
        return realAction;
    }

    public abstract void apply(PlayerStatus playerStatus);

    public boolean checkActionSpaceOccupied(ActionSpace actionSpace) {
        //TODO: impl - controlla B&M che balzano actionSpace occupato
        return true;
    }

    public boolean checkSufficientActionValue() {
        executeBonusAndMalusOnAction();
        //TODO: impl - verifica il valore della temp pawn >= actionSpace value
        return true;
    }

    private void executeBonusAndMalusOnAction() {
        //TODO: impl - iteri la lista b&m sulla tempPawn
    }

}
