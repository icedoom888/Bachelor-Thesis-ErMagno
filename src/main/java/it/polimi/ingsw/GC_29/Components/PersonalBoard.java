package it.polimi.ingsw.GC_29.Components;

import java.util.EnumMap;

/**
 * Created by icedoom on 18/05/17.
 */
public class PersonalBoard {

    // TODO: sistemare le Lane, c'è una EnumMap rispetto al colore quando in realtà le lane hanno già un attributo colore, si può far meglio

    private BonusTile bonusTile;
    private Lane buildingLane;
    private Lane venturesLane;
    private Lane familyLane;
    private TerritoryLane territoryLane;
    private EnumMap<CardColor, Lane> laneMap;

    public PersonalBoard(BonusTile chosenTile, int laneDimension){
        bonusTile = chosenTile;
        buildingLane = new Lane(CardColor.YELLOW, laneDimension);
        venturesLane = new Lane(CardColor.PURPLE, laneDimension);
        familyLane = new Lane(CardColor.BLUE, laneDimension);
        territoryLane = new TerritoryLane(CardColor.GREEN, laneDimension);
        laneMap = new EnumMap<CardColor, Lane>(CardColor.class);
        laneMap.put(CardColor.YELLOW, buildingLane);
        laneMap.put(CardColor.PURPLE, venturesLane);
        laneMap.put(CardColor.GREEN, territoryLane);
        laneMap.put(CardColor.BLUE, familyLane);
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

    public TerritoryLane getTerritoryLane() {
        return territoryLane;
    }

    public Lane getLane(CardColor cardColor) {
        return laneMap.get(cardColor);
    }

}
