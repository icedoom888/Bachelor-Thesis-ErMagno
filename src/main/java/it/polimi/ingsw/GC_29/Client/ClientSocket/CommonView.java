package it.polimi.ingsw.GC_29.Client.ClientSocket;

import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameState;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lorenzotara on 15/06/17.
 */
public class CommonView {

    private FamilyPawnType familyPawnChosen;
    private int actionIndex;
    private HashMap<Integer,String> validActionList;
    private PlayerState currentPlayerState;
    private GameState currentGameState;
    private PlayerColor playerColor;
    private InputChecker inputChecker;

    public CommonView() {
    }

    public FamilyPawnType getFamilyPawnChosen() {
        return familyPawnChosen;
    }

    public void setFamilyPawnChosen(FamilyPawnType familyPawnChosen) {
        this.familyPawnChosen = familyPawnChosen;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(int actionIndex) {
        this.actionIndex = actionIndex;
    }

    public HashMap<Integer, String> getValidActionList() {
        return validActionList;
    }

    public void setValidActionList(HashMap<Integer, String> validActionList) {
        this.validActionList = validActionList;
    }

    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setCurrentPlayerState(PlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public InputChecker getInputChecker() {
        return inputChecker;
    }

    public void setInputChecker(InputChecker inputChecker) {
        this.inputChecker = inputChecker;
    }
}
