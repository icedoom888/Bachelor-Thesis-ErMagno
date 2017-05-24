package it.polimi.ingsw.GC_29.Components;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.HashMap;

/**
 * Created by icedoom on 18/05/17.
 */
public class PersonalBoard {
    private BonusTile bonusTile;
    private Lane buildingLane;
    private Lane venturesLane;
    private Lane familyLane;
    private TerritoryLane territoryLane;
    private HashMap<CardColor, Lane> laneHashMap;

    public PersonalBoard(BonusTile chosenTile, int laneDimension){
        bonusTile = chosenTile;
        buildingLane = new Lane(CardColor.YELLOW, laneDimension);
        venturesLane = new Lane(CardColor.PURPLE, laneDimension);
        familyLane = new Lane(CardColor.BLUE, laneDimension);
        territoryLane = new TerritoryLane(CardColor.GREEN, laneDimension);
        laneHashMap = new HashMap<CardColor, Lane>();
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
