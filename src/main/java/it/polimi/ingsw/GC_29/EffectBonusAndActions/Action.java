package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.ActionSpace;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public abstract class Action {
    protected FamilyPawn pawnSelected;
    private ActionType actionSelected;
    private int workers;
    protected ActionSpace actionSpaceSelected;
    private FamilyPawn temporaryPawn;
    // private GoodSet tempGoodSet;
    protected boolean realAction;
    protected PlayerStatus playerStatus;

    public Action(FamilyPawn pawnSelected, ActionType actionSelected, int workers, boolean realAction, PlayerStatus playerStatus) {
        this.pawnSelected = pawnSelected;
        this.actionSelected = actionSelected;
        this.workers = workers;
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

    public int getWorkers() {
        return workers;
    }

    public FamilyPawn getTemporaryPawn() {
        return temporaryPawn;
    }

    public boolean isRealAction() {
        return realAction;
    }

    public abstract void execute();

    /**
     * if it is a real action, update:
     * set as not available the family pawn used by the player
     * set as occupied the actionSpace if it is single
     * ...
     */
    protected void update() {
        if (realAction) {
            FamilyPawnType familyPawnType = pawnSelected.getType();
            playerStatus.getFamilyPawnAvailability().put(familyPawnType, false);

            if (actionSpaceSelected.isSingle()) actionSpaceSelected.setOccupied(true);

        }

    }

    public boolean isPossible() {
        return checkActionSpaceOccupied()
                && checkSufficientActionValue()
                && checkFamilyPawn();
    }


    /**
     * checkActionSpaceOccupied calls the Filter only if the actionSpace has just one place and is occupied:
     * Filter.applyOnActionSpace returns true only if the player has a particular bonusAndMalus, otherwise
     * it returns false. If the actionSpace is not single or is not occupied, the method returns true.
     * @return true if the player can add a pawn in the actionSpace, false otherwise
     */
    private boolean checkActionSpaceOccupied() {

        if (actionSpaceSelected.isSingle() && actionSpaceSelected.isOccupied()) {
            return Filter.applyOnActionSpace(playerStatus, actionSpaceSelected);
        }

        return true;
    }


    /**
     * checkSufficientActionValue checks if the pawnSelected has enough value or
     * if, thanks to bonusAndMalus, reaches the needed value or, in case it doesn't reach it,
     * if the player has enough workers to reach he right actionValue.
     * @return true if the player has enough actionValue on the pawn or enough workers to access the
     * actionSpace, false otherwise
     */
    private boolean checkSufficientActionValue() {

        executeBonusAndMalusOnAction();

        if (pawnSelected.getActualValue() < actionSpaceSelected.getActionCost()) {
            int workersNeeded = workersNeeded();

            if (workersNeeded > playerStatus.getActualGoodSet().getGoodAmount(GoodType.WORKERS)) {
                return false;

            } else {
                setWorkers(workersNeeded);
            }

        }
        return true;
    }


    /**
     * executeBonusAndMalusOnAction calls the Filter.apply that can change the value
     * of the pawnSelected
     */
    private void executeBonusAndMalusOnAction() { // serve per controllare che con B&M il valore della pawn vada bene o meno
        Filter.apply(playerStatus, actionSelected, pawnSelected.getActualValue());
    }



    private int workersNeeded() {
        return pawnSelected.getActualValue() - actionSpaceSelected.getActionCost();
    }



    public void setWorkers(int workers) {
        this.workers = workers;
    }



    /**
     * checkFamilyPawn checks if the pawn selected by the player is available or not
     * @return true if the pawn hasn't been used during the turn, false otherwise
     */
    private boolean checkFamilyPawn() {
        FamilyPawnType familyPawnType = pawnSelected.getType();
        return playerStatus.getFamilyPawnAvailability().get(familyPawnType);
    }


    /**
     * addPawn put the pawn on the selected actionSpace and then execute the effect
     */
    protected void addPawn() {
        actionSpaceSelected.addPawn(pawnSelected);

        /*
        // TODO: meglio metterlo nell'update? sarebbe codice ripetuto a meno che update non sia più abstract - cosa che per il momento è successa
        if (realAction) {
            playerStatus.getFamilyPawnAvailability().put(pawnSelected.getType(), false);
        }*/

        actionSpaceSelected.getEffect().execute(playerStatus);
    }

}
