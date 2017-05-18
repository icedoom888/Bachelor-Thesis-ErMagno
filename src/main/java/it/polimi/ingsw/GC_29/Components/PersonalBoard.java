package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 18/05/17.
 */
public class PersonalBoard {
    private BonusTile bonusTile;
    private Lane buildingLane;
    private Lane venturesLane;
    private Lane familyLane;
    private Lane territoryLane;
    private GoodSet goodCoffer;

    public PersonalBoard(){
    }

    public BonusTile getBonusTile() {
        return bonusTile;
    }

    public Lane getBuildingLane() {
        return buildingLane;
    }

    public Lane getVenturesLane() {
        return venturesLane;
    }

    public Lane getFamilyLane() {
        return familyLane;
    }

    public Lane getTerritoryLane() {
        return territoryLane;
    }

    public GoodSet getGoodCoffer() {
        return goodCoffer;
    }

    public void setGoodCoffer(GoodSet goodCoffer) {
        this.goodCoffer = goodCoffer;
    }
}
