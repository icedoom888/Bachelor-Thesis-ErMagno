package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.ActionType;
import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.Tower;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.
 */
public class TowerActionBuilder implements ActionBuilder {

    private ActionType towerColor;
    private PlayerStatus playerStatus;
    private Tower tower;
    private boolean realAction;

    public TowerActionBuilder(ActionType towerColor, Tower tower, PlayerStatus playerStatus) {
        this.towerColor = towerColor;
        this.tower = tower;
    }

    private FamilyPawn askFamilyPawn(){

        return null;
    }
    @Override
    public TowerAction build() {

        FamilyPawn familyPawnSelected = askFamilyPawn();
        int workersSelected = askForWorkers();
        // realAction TODO: devo capire come collegare le azioni bouns ai builder
        int floorIndex = askFloor();

        return new TowerAction(familyPawnSelected, towerColor, workersSelected, realAction, playerStatus, tower, floorIndex);
    }

    private int askFloor() {
        return 0;
    }

    private int askForWorkers() {
        return 0;
    }
}
