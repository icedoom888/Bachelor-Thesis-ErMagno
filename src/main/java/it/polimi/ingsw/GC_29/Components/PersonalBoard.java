package it.polimi.ingsw.GC_29.Components;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.EnumMap;
import java.util.HashMap;

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
    private EnumMap<CardColor, Lane> laneHashMap;

    public PersonalBoard(BonusTile chosenTile, int laneDimension){
        bonusTile = chosenTile;
        buildingLane = new Lane(laneDimension);
        venturesLane = new Lane(laneDimension);
        familyLane = new Lane(laneDimension);
        territoryLane = new TerritoryLane(laneDimension);
        laneHashMap = new EnumMap<CardColor, Lane>(CardColor.class);
        laneHashMap.put(CardColor.YELLOW, buildingLane);
        laneHashMap.put(CardColor.PURPLE, venturesLane);
        laneHashMap.put(CardColor.GREEN, territoryLane);
        laneHashMap.put(CardColor.BLUE, familyLane);
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
        return laneHashMap.get(cardColor);
    }

}
