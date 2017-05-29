package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class Action {

    private int workers;
    protected FamilyPawn temporaryPawn;
    protected ZoneType zoneType;
    protected ActionSpace actionSpaceSelected;
    protected PlayerStatus playerStatus;

    public Action(FamilyPawn pawnSelected,
                  ZoneType zoneType,
                  PlayerStatus playerStatus) {

        this.zoneType = zoneType;
        this.temporaryPawn = new FamilyPawn(pawnSelected);
        this.playerStatus = playerStatus;
    }


    public ZoneType getZoneType() {
        return zoneType;
    }

    public int getWorkers() {
        return workers;
    }

    public FamilyPawn getTemporaryPawn() {
        return temporaryPawn;
    }


    public void execute() {

        payWorkers();
        addPawn();
        update();
    }



    /**
     * During the execute, this method make the player pay to increase the value of his pawn
     */
    //TODO: utilizare un filtraggio speciale su bonus e malus per il malus che fa pagare doppi o i workers
    protected void payWorkers() {
        playerStatus.getActualGoodSet().subGoodSet(new GoodSet(0,0,0,workers,0,0,0));
    }



    /**
     * if it is a real action, update:
     * sets as not available the family pawn used by the player
     * sets as occupied the actionSpace if it is single
     * ...
     */

    public boolean isPossible() {

        return isActionAvailable()
                && checkActionSpaceAvailability()
                && checkSufficientActionValue()
                && checkFamilyPawn();
    }

    private boolean isActionAvailable() {
        return Filter.applySpecia(playerStatus, zoneType);
    }


    /**
     * checkActionSpaceAvailability calls the Filter only if the actionSpace has just one place and is occupied:
     * Filter.applyOnActionSpace returns true only if the player has a particular bonusAndMalus, otherwise
     * it returns false. If the actionSpace is not single or is not occupied, the method returns true.
     * @return true if the player can add a pawn in the actionSpace, false otherwise
     */
    private boolean checkActionSpaceAvailability() {

        if (actionSpaceSelected.isSingle() && actionSpaceSelected.isOccupied()) {
            return Filter.applySpecial(playerStatus, actionSpaceSelected);
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

        if (temporaryPawn.getActualValue() < actionSpaceSelected.getActionCost()) {
            int workersNeeded = workersNeeded();

            if (workersNeeded > playerStatus.getActualGoodSet().getGoodAmount(GoodType.WORKERS)) {
                return false;

            } else {
                setWorkers(workersNeeded);
                return true;
            }

        }
        setWorkers(0);
        return true;
    }


    /**
     * executeBonusAndMalusOnAction calls the Filter.apply that can change the value
     * of the pawnSelected
     */
    private void executeBonusAndMalusOnAction() { // serve per controllare che con B&M il valore della pawn vada bene o meno

        Filter.apply(playerStatus, zoneType, temporaryPawn.getActualValue());
    }



    private int workersNeeded() {

        return actionSpaceSelected.getActionCost() - temporaryPawn.getActualValue(); //TODO inverti
    }



    public void setWorkers(int workers) {

        this.workers = workers;
    }



    /**
     * checkFamilyPawn checks if the pawn selected by the player is available or not
     * @return true if the pawn hasn't been used during the turn, false otherwise
     */
    private boolean checkFamilyPawn() {

        if(temporaryPawn.getType() != FamilyPawnType.BONUS) {

            FamilyPawnType familyPawnType = temporaryPawn.getType();
            return playerStatus.getFamilyPawnAvailability().get(familyPawnType);

        }

        else{

            return true;
        }
    }


    /**
     * addPawn put the pawn on the selected actionSpace and then execute the effect.
     * This method controls if the pawn is a bonusPawn from a bonus action, in that case it does not add the paen in the action space,
     * but it gives the effects of the actionSpace to the player (see the game Rules).
     */
    protected void addPawn() {

        if(temporaryPawn.getType() != FamilyPawnType.BONUS){

            actionSpaceSelected.addPawn(temporaryPawn);
        }

        // TODO: inserire la pawnSelected nella mappa fi pawn usate del gameStatus
        // TODO: Gamastatus.getInstance()

        Effect effect = actionSpaceSelected.getEffect();

        if (effect != null){

            effect.execute(playerStatus);
        }
    }

    protected void update() {

        FamilyPawnType familyPawnType = temporaryPawn.getType();

        if (familyPawnType != FamilyPawnType.BONUS) {

            playerStatus.getFamilyPawnAvailability().put(familyPawnType, false);

            if (actionSpaceSelected.isSingle()) actionSpaceSelected.setOccupied(true);

        }

    }

    //metodi per testing


    public ActionSpace getActionSpaceSelected() {
        return actionSpaceSelected;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }
}
