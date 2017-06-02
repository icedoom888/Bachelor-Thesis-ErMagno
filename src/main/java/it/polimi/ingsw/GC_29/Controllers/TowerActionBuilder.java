package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 19/05/2017.

public class TowerActionBuilder implements ActionBuilder {

    private ZoneType towerColor;
    private Player player;
    private Tower tower;
    private FamilyPawn familyPawnSelected;

    public TowerActionBuilder(ZoneType type, FamilyPawn familyPawnSelected, Player player, Tower tower) {

        this.towerColor = towerColor;
        this.tower = tower;
        this.player = player;
        this.familyPawnSelected = familyPawnSelected;
    }

    @Override
    public TowerAction build() {

        int floorIndex = askFloor(); // se fai qui il controllo sui workers necessari porti la logica del metodo isPossible() (interno all'azione già costruita) qui nel momento in cui stai ancora costruendo l'azione

        int workersSelected = askForWorkers();

        return new TowerAction(familyPawnSelected, towerColor, workersSelected, player, tower, floorIndex);
    }

    private int askFloor() {
        return 0;
    }

    private int askForWorkers() {
        return 0;
    }
}*/
