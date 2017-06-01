package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Christian on 01/06/2017.
 */
public class ActionChecker {

    private static ActionChecker instance = null;

    private ArrayList<Action> actionList;
    private HashMap<FamilyPawn, ArrayList<Action>> validActionForFamilyPawnMap;
    private Player currentPlayer;

    private ActionChecker(){

        actionList = new ArrayList<>();
        validActionForFamilyPawnMap = new HashMap<>();
    }

    public ActionChecker getInstance(){

        if(instance == null){
            instance = new ActionChecker();
        }

        return instance;

    }

    public void setCurrentPlayer(Player currentPlayer) throws NullPointerException{

        this.currentPlayer = currentPlayer;
    }

    /**
     * This method checks the valid actions for the familiyPawn chosen. The valid actions (isPossible() true) are added in the List
     * of the valid action Map with key the family pawn chosen
     * @param familyPawnChosen
     */
    public void checkActionOnPawn(FamilyPawn familyPawnChosen){

        for(Action action : actionList){

            action.setFamiliyPawn(familyPawnChosen);

            if(action.isPossible()){
                validActionForFamilyPawnMap.get(familyPawnChosen).add(action);
            }
        }
    }

    public void cleanValidActionMap(){

        for(ArrayList<Action> list : validActionForFamilyPawnMap.values()){

            list.clear();
        }
    }
}
