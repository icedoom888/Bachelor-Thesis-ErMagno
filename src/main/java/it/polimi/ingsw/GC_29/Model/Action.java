package it.polimi.ingsw.GC_29.Model;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public abstract class Action {


    protected int workers;
    protected FamilyPawn temporaryPawn;
    protected Model model;
    protected ZoneType zoneType;
    protected ActionSpace actionSpaceSelected;
    protected Player player;
    protected Boolean enable = true;
    protected Boolean onlyWorkers = false;

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Boolean getOnlyWorkers() {
        return onlyWorkers;
    }

    public void setOnlyWorkers(Boolean onlyWorkers) {
        this.onlyWorkers = onlyWorkers;
    }

    protected Boolean isValid = true;

    public Action(ZoneType zoneType, Model model) {

        this.zoneType = zoneType;
        this.model = model;
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

    public void setFamiliyPawn(FamilyPawn familyPawnChosen){

        this.temporaryPawn = familyPawnChosen;
    }

    public void setPlayer(Player player){

        this.player = player;
    }


    public void execute() {

        payWorkers();
        addPawn();
        update();
    }



    /**
     * During the execute, this method make the player pay to increase the value of his pawn
     */
    protected void payWorkers() {

        player.updateGoodSet(new GoodSet(0,0,0,-workers,0,0,0));
    }



    /**
     * if it is a real action, update:
     * sets as not available the family pawn used by the player
     * sets as occupied the actionSpace if it is single
     * ...
     */

    public boolean isPossible() {

        return  enable
                && !isActionDenied()
                && checkActionSpaceAvailability()
                && checkSufficientActionValue()
                && checkFamilyPawn();

    }

    private boolean isActionDenied() {
        return Filter.applySpecial(player, zoneType);
    }


    /**
     * checkActionSpaceAvailability calls the Filter only if the actionSpace has just one place and is occupied:
     * Filter.applyOnActionSpace returns true only if the player has a particular bonusAndMalus, otherwise
     * it returns false. If the actionSpace is not single or is not occupied, the method returns true.
     * @return true if the player can add a pawn in the actionSpace, false otherwise
     */
    private boolean checkActionSpaceAvailability() {

        if (actionSpaceSelected.isSingle() && actionSpaceSelected.isOccupied()) {
            return Filter.applySpecial(player, SpecialBonusAndMalus.PAWNONOCCUPIEDSPACE);
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

        if (!onlyWorkers) {
            executeBonusAndMalusOnAction();
        }

        if (temporaryPawn.getActualValue() < actionSpaceSelected.getActionCost()) {
            int workersNeeded = workersNeeded();

            if (workersNeeded > player.getActualGoodSet().getGoodAmount(GoodType.WORKERS)) {
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

        Filter.apply(player, temporaryPawn, zoneType);
    }


    /**
     * returns the workers needed to do the action taking into account all the bonus and malus of the player
     * @return
     */
    private int workersNeeded() {

        int workersNeeded = actionSpaceSelected.getActionCost() - temporaryPawn.getActualValue();

        if (Filter.applySpecial(player, SpecialBonusAndMalus.TWOWORKERS)) {
            return workersNeeded*2;
        }

        return workersNeeded;
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
            return player.getFamilyPawnAvailability().get(familyPawnType);

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

            model.getPawnsOnActionSpace().put(temporaryPawn, actionSpaceSelected);

            model.updatePawnsGUI(temporaryPawn);
        }



        Effect effect = actionSpaceSelected.getEffect();

        if (effect != null){

            effect.execute(player);
        }
    }

    /**
     * updates the pawn's availabilty on the player and sets the actionSpace occupied
     */
    protected void update() {

        FamilyPawnType familyPawnType = temporaryPawn.getType();

        if (familyPawnType != FamilyPawnType.BONUS) {

            player.getFamilyPawnAvailability().put(familyPawnType, false);

            if (actionSpaceSelected.isSingle()) actionSpaceSelected.setOccupied(true);

        }

    }

    public void reset() {
        resetExceptPlayer();
        this.player = null;
    }

    public void resetExceptPlayer(){
        this.workers = 0;
        this.temporaryPawn = null;
        this.onlyWorkers = false;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    //metodi per testing


    public ActionSpace getActionSpaceSelected() {
        return actionSpaceSelected;
    }

    public Player getPlayer() {
        return player;
    }


    @Override
    public String toString() {
        return " zoneType= " + zoneType
                + ", workers= " + workers
                +  ", enable= " + enable ;
    }

    public boolean isValid() {
        return isValid;
    }
}
