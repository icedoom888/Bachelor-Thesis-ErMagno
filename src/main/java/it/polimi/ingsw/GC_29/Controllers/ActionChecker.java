package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Model.*;

import java.util.ArrayList;

/**
 * Created by Christian on 01/06/2017.
 */
public class ActionChecker {


    private ArrayList<Action> actionList;
    private Player currentPlayer;
    private ArrayList<Action> validActionList;
    private Model model;

    //test variable
    private boolean testVariable = false;

    public ActionChecker(Model model){

        actionList = new ArrayList<>();
        validActionList = new ArrayList<>();
        this.model = model;
    }


    public ArrayList<Action> getActionList(){

        return actionList;
    }

    public ArrayList<Action> getValidActionList() {
        return validActionList;
    }

    public void setActionList(ArrayList<Action> actionList){

        this.actionList = actionList;

    }

    public void setCurrentPlayer() {

        this.currentPlayer = model.getCurrentPlayer();

        for(Action action : actionList){

            action.setPlayer(currentPlayer);
        }
    }

    public void setValidActionForFamilyPawn(FamilyPawn familyPawn){

        checkActionOnPawn(familyPawn, actionList);

    }

    public void setValidActionForFamilyPawn(FamilyPawn familyPawn, ZoneType zoneType){

        checkActionOnPawnFiltered(familyPawn, zoneType);

    }

    private void checkActionOnPawnFiltered(FamilyPawn familyPawn, ZoneType zoneType) {

        checkActionOnPawn(familyPawn, actionList);

        if(zoneType == ZoneType.ANYTOWER){

            for(Action action : actionList){
                if(action.getZoneType() != ZoneType.GREENTOWER
                        && action.getZoneType() != ZoneType.YELLOWTOWER
                        && action.getZoneType() != ZoneType.BLUETOWER
                        && action.getZoneType() != ZoneType.PURPLETOWER){

                    action.setValid(false);
                }
            }
        }

        else {

            for (Action action : actionList) {

                if (action.getZoneType() != zoneType) {

                    action.setValid(false);
                }
            }
        }
    }

    /**
     * This method checks the valid actions for the familiyPawn chosen. The valid actions (isPossible() true) are added in the List
     * of the valid action Map with key the family pawn chosen
     * @param familyPawnChosen
     */
    public void checkActionOnPawn(FamilyPawn familyPawnChosen, ArrayList<Action> temporaryActionList) throws NullPointerException{

        for(Action action : temporaryActionList){

            action.setFamiliyPawn(new FamilyPawn(familyPawnChosen));

            System.out.println("SONO IN ACTION CHECKER COLORE " + action.getTemporaryPawn().getType() + "VALORE " +action.getTemporaryPawn().getActualValue());


            action.setValid(action.isPossible());


            validActionList.add(action);
        }

        currentPlayer.setCurrentValidActionsList(validActionList);
    }


    public void resetActionList(){

        for(Action action : actionList){

            action.reset();
        }

        validActionList.clear();
    }

    public void resetActionListExceptPlayer(){

        for(Action action : actionList){

            action.resetExceptPlayer();
        }

        validActionList.clear();
    }

    public void setOnlyWorkers() {

        for (Action action : actionList) {
            action.setOnlyWorkers(true);
        }
    }

}
