package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Components.ShopName;
import it.polimi.ingsw.GC_29.Components.Tower;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.Player;
import jdk.nashorn.internal.objects.NativeUint8Array;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Christian on 01/06/2017.
 */
public class ActionChecker {


    private ArrayList<Action> actionList;
    private Player currentPlayer;
    private ArrayList<Action> validActionList;
    private GameStatus gameStatus;

    //test variable
    private boolean testVariable = false;

    public ActionChecker(GameStatus gameStatus){

        actionList = new ArrayList<>();
        validActionList = new ArrayList<>();
        this.gameStatus = gameStatus;
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

    public void setCurrentPlayer() throws NullPointerException{

        this.currentPlayer = gameStatus.getCurrentPlayer();

        for(Action action : actionList){

            action.setPlayer(currentPlayer);
        }
    }

    public void setValidActionForFamilyPawn(FamilyPawn familyPawn){

        checkActionOnPawn(familyPawn, actionList);

    }

    public void setValidActionForFamilyPawn(FamilyPawn familyPawn, ZoneType zoneType){

        ArrayList<Action> filteredActionList = filterActionListPerZoneType(zoneType);

        checkActionOnPawn(familyPawn, filteredActionList);

    }

    /**
     * This method checks the valid actions for the familiyPawn chosen. The valid actions (isPossible() true) are added in the List
     * of the valid action Map with key the family pawn chosen
     * @param familyPawnChosen
     */
    public void checkActionOnPawn(FamilyPawn familyPawnChosen, ArrayList<Action> temporaryActionList) throws NullPointerException{

        for(Action action : temporaryActionList){

            action.setFamiliyPawn(familyPawnChosen);


            action.setValid(action.isPossible());


            validActionList.add(action);

            System.out.println(action);
            System.out.println("");
        }

        currentPlayer.setCurrentValidActionsList(validActionList);
    }


    private ArrayList<Action> filterActionListPerZoneType(ZoneType zoneType){

        ArrayList<Action> filteredActionList = new ArrayList<>();

        if(zoneType == ZoneType.ANYTOWER){

            for(Action action : actionList){
                if(action.getZoneType() == ZoneType.GREENTOWER
                    || action.getZoneType() == ZoneType.YELLOWTOWER
                    || action.getZoneType() == ZoneType.BLUETOWER
                    || action.getZoneType() == ZoneType.PURPLETOWER){

                    filteredActionList.add(action);
                }
            }
        }

        else{

            for(Action action : actionList){

                if(action.getZoneType() == zoneType){

                    filteredActionList.add(action);
                }
            }
        }

        return filteredActionList;
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
