package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.Workspace;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Action;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 19/05/2017.

public class WorkActionBuilder implements ActionBuilder {

    private FamilyPawn familyPawnSelected;
    private ZoneType zoneType;
    private PlayerStatus playerStatus;
    private Workspace workZoneSelected;



    public WorkActionBuilder(ZoneType zoneType, FamilyPawn familyPawnSelected, PlayerStatus playerStatus) {

        this.familyPawnSelected = familyPawnSelected;
        this.zoneType = zoneType;
        this.playerStatus = playerStatus;
    }

    @Override
    public Action build() {

        int field = askWichField();

        int workersSelected = askForWorkers();

        return WorkAction(familyPawnSelected, workersSelected, playerStatus, zoneType, field);
    }


    private int askWichField() {

        return 0;
    }

    private int askForWorkers() {

        return 0;
    }
}
 */