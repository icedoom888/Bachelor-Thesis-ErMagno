package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 19/05/2017.

public class WorkActionBuilder implements ActionBuilder {

    private FamilyPawn familyPawnSelected;
    private ZoneType zoneType;
    private Player player;
    private Workspace workZoneSelected;



    public WorkActionBuilder(ZoneType zoneType, FamilyPawn familyPawnSelected, Player player) {

        this.familyPawnSelected = familyPawnSelected;
        this.zoneType = zoneType;
        this.player = player;
    }

    @Override
    public Action build() {

        int field = askWichField();

        int workersSelected = askForWorkers();

        return WorkAction(familyPawnSelected, workersSelected, player, zoneType, field);
    }


    private int askWichField() {

        return 0;
    }

    private int askForWorkers() {

        return 0;
    }
}
 */