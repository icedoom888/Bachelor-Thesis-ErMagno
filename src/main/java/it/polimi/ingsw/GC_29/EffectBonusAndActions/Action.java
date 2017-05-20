package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.ActionSpace;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;
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
    protected ActionSpace actionSpaceSelected;
    private FamilyPawn temporaryPawn;
    private GoodSet tempGoodSet;
    private boolean realAction;
    private PlayerStatus playerStatus;

    public Action(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction, PlayerStatus playerStatus) {
        this.pawnSelected = pawnSelected;
        this.actionSelected = actionSelected;
        this.workersSelected = workersSelected;
        this.temporaryPawn = new FamilyPawn(pawnSelected);
        this.realAction = realAction;
        this.playerStatus = playerStatus;
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

    public boolean isRealAction() {
        return realAction;
    }

    public abstract void execute();

    protected abstract void update();

    protected boolean isPossible() { // nelle figlie override con return super.isPossibile() && tutti i controlli della zona specifica
        return checkActionSpaceOccupied(actionSpaceSelected)&&checkSufficientActionValue();
    }

    private boolean checkActionSpaceOccupied(ActionSpace actionSpace) {
        //TODO: impl - controlla B&M che balzano actionSpace occupato
        return true;
    }

    private boolean checkSufficientActionValue() {
        executeBonusAndMalusOnAction();
        //TODO: impl - verifica il valore della temp pawn >= actionSpace value
        return true;
    }

    private void executeBonusAndMalusOnAction() { // serve per controllare che con B&M il valore della pawn vada bene o meno
        //TODO: impl - iteri la lista b&m sulla tempPawn
    }

    /*
    * Ultimi 2 metodi dentro isPossible
     */

    protected void addPawn() { // mette il familiare e si occupa di attivare effetto actionSpace

    }

}
