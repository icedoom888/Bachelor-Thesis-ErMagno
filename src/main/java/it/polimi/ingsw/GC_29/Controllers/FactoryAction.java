package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 27/05/2017.
 */
public class FactoryAction {

    public static Action getAction(ZoneType zoneType, FamilyPawn familyPawn, PlayerStatus playerStatus){

        if(zoneType == ZoneType.GREENTOWER || zoneType == ZoneType.YELLOWTOWER || zoneType == ZoneType.BLUETOWER || zoneType == ZoneType.PURPLETOWER){

            int floorIndex = askWichFloor();

            return new TowerAction(familyPawn, zoneType, playerStatus, floorIndex);
        }

        if(zoneType == ZoneType.HARVEST || zoneType == ZoneType.PRODUCTION){

            int fieldSelected = askWichField();

            return new WorkAction(familyPawn, zoneType, playerStatus, fieldSelected);
        }

        if(zoneType == ZoneType.MARKET){

            int houseSelected = askWichHouse();

            return new MarketAction(familyPawn, playerStatus, houseSelected);
        }

        if(zoneType == ZoneType.COUNCILPALACE){

            return new CouncilPalaceAction(familyPawn, playerStatus);
        }

        else{
            throw new IllegalArgumentException("Illegal zone: " + zoneType);
        }
    }

    private static int askWichHouse() {

        return 0;
    }

    private static int askWichField() {

        return 0;
    }

    private static int askWichFloor() {

        return 0;
    }
}
