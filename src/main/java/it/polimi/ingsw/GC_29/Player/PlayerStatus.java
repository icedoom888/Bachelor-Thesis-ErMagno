package it.polimi.ingsw.GC_29.Player;

import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnAction;
import it.polimi.ingsw.GC_29.Controllers.BonusAndMalusOnEffect;

import java.util.ArrayList;

/**
 * Created by Christian on 17/05/2017.
 */
public class PlayerStatus {
    private ArrayList<BonusAndMalusOnAction> bonusAndMalusOnActionList;
    private ArrayList<BonusAndMalusOnEffect> bonudAndMalusEffectList;
    private boolean blackPawnAvailability;
    private boolean whitePawnAvailability;
    private boolean orangePawnAvailability;
    private boolean neutralPawnAvailability;

    public ArrayList<BonusAndMalusOnAction> getBonusAndMalusOnActionList() {
        return bonusAndMalusOnActionList;
    }

    public ArrayList<BonusAndMalusOnEffect> getBonudAndMalusEffectList() {
        return bonudAndMalusEffectList;
    }

    public boolean isBlackPawnAvailability() {
        return blackPawnAvailability;
    }

    public boolean isWhitePawnAvailability() {
        return whitePawnAvailability;
    }

    public boolean isOrangePawnAvailability() {
        return orangePawnAvailability;
    }

    public boolean isNeutralPawnAvailability() {
        return neutralPawnAvailability;
    }

    public void setBlackPawnAvailability(boolean blackPawnAvailability) {
        this.blackPawnAvailability = blackPawnAvailability;
    }

    public void setWhitePawnAvailability(boolean whitePawnAvailability) {
        this.whitePawnAvailability = whitePawnAvailability;
    }

    public void setOrangePawnAvailability(boolean orangePawnAvailability) {
        this.orangePawnAvailability = orangePawnAvailability;
    }

    public void setNeutralPawnAvailability(boolean neutralPawnAvailability) {
        this.neutralPawnAvailability = neutralPawnAvailability;
    }
}
