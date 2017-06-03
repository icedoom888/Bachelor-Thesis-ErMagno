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

    private static ActionChecker instance = null;

    private ArrayList<Action> actionList;
    private HashMap<FamilyPawn, ArrayList<Action>> validActionForFamilyPawnMap;
    private Player currentPlayer;
    private int numberOfPlayers;



    //test variable
    private boolean testVariable = false;

    private ActionChecker(){

        actionList = new ArrayList<>();
        validActionForFamilyPawnMap = new HashMap<>();
    }

    public static ActionChecker getInstance(){

        if(instance == null){
            instance = new ActionChecker();
        }

        return instance;

    }

    public void setActionList(ArrayList<Action> actionList){

        this.actionList = actionList;
    }

    public void setNumberOfPlayers(int numberOfPlayers){

        this.numberOfPlayers = numberOfPlayers;
    }

    public void setCurrentPlayer(Player currentPlayer) throws NullPointerException{

        this.currentPlayer = currentPlayer;

        initializeActionMap(currentPlayer);
    }

    public void initializeActionMap(Player currentPlayer) throws NullPointerException{

        for(FamilyPawn pawn : currentPlayer.getFamilyPawns()){

            validActionForFamilyPawnMap.put(pawn, new ArrayList<>());
        }
    }

    public void initializeBonusPawn(FamilyPawn familyPawn) throws NullPointerException{

        if(familyPawn.getType() != FamilyPawnType.BONUS){
            throw new IllegalArgumentException("Illegal type type: " + familyPawn.getType());
        }

        validActionForFamilyPawnMap.put(familyPawn, new ArrayList<>());
    }


    public void setValidActionForFamilyPawn(FamilyPawn familyPawn){

        ArrayList<Action> temporaryActionList = loadActionListFromFile();

        checkActionOnPawn(familyPawn, temporaryActionList);

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

            if(action.isPossible()){

                validActionForFamilyPawnMap.get(familyPawnChosen).add(action);

            }
        }
    }

    /**
     * this method cleans the valid action Map for the current player.
     * It should be called before the end of the current player's turn.
     */
    public void cleanValidActionMap(){

        for(ArrayList<Action> list : validActionForFamilyPawnMap.values()){

            list.clear();
        }
    }

    public ArrayList<Action> filterActionListPerZoneType(ZoneType zoneType){

        ArrayList<Action> loadedActionList = loadActionListFromFile();

        ArrayList<Action> filteredActionList = new ArrayList<>();

        if(zoneType == ZoneType.ANYTOWER){

            for(Action action : loadedActionList){
                if(action.getZoneType() == ZoneType.GREENTOWER
                    || action.getZoneType() == ZoneType.YELLOWTOWER
                    || action.getZoneType() == ZoneType.BLUETOWER
                    || action.getZoneType() == ZoneType.PURPLETOWER){

                    filteredActionList.add(action);
                }
            }
        }

        else{

            for(Action action : loadedActionList){

                if(action.getZoneType() == zoneType){

                    filteredActionList.add(action);
                }
            }
        }

        return filteredActionList;
    }

    private ArrayList<Action> loadActionListFromFile(){

        //TODO: carica lista azioni da file, così hai sempre una lista di azioni immutabile (troppo dispendioso creare metodi copia nelle azioni)

       /*ArrayList<Action> actionList = new ArrayList<>();

        final int NUMBEROFFLOORS = 4;

        for(ZoneType zoneType : ZoneType.values()){

            if(zoneType == ZoneType.GREENTOWER || zoneType == ZoneType.YELLOWTOWER || zoneType == ZoneType.BLUETOWER || zoneType == ZoneType.PURPLETOWER){

                for (int i = 0; i < NUMBEROFFLOORS; i++){

                    actionList.add(new TowerAction(zoneType, i));
                }

            }

            if(zoneType == ZoneType.MARKET){
                if(numberOfPlayers>=)
            }
        }*/

        return new ArrayList<>();
    }


    public void fakeAddValidActionMap(FamilyPawn familyPawnChosen){

        ArrayList<Action> temporaryActionList = fakeloadActionListFromFile(familyPawnChosen);

        for(Action action : temporaryActionList){

            action.setFamiliyPawn(familyPawnChosen);

            validActionForFamilyPawnMap.get(familyPawnChosen).add(action);

        }
        int i = 0;
    }

    private ArrayList<Action> fakeloadActionListFromFile(FamilyPawn familyPawn) {

        MarketAction marketAction = new MarketAction( ShopName.MONEYSHOP);
        marketAction.setFamiliyPawn(familyPawn);
        marketAction.setPlayer(currentPlayer);

        MarketAction marketAction2 = new MarketAction( ShopName.WORKERSHOP);
        marketAction2.setFamiliyPawn(familyPawn);
        marketAction2.setPlayer(currentPlayer);

        CouncilPalaceAction councilPalaceAction = new CouncilPalaceAction();
        councilPalaceAction.setFamiliyPawn(familyPawn);
        councilPalaceAction.setPlayer(currentPlayer);

        ArrayList<Action> list1 = new ArrayList<>();
        list1.add(marketAction);
        list1.add(marketAction2);

        ArrayList<Action> list2 = new ArrayList<>();
        list2.add(councilPalaceAction);

        testVariable = testVariable == false;

        if(testVariable){
            return list1;
        }

        else{
            return list2;
        }

    }

}
