package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.ShopName;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.Player;

/**
 * Created by Christian on 27/05/2017.

public class FactoryAction {

    private static int floor; // for playerControllerTest

    public static Action getAction(ZoneType zoneType, FamilyPawn familyPawn, Player player){

        if(zoneType == ZoneType.GREENTOWER || zoneType == ZoneType.YELLOWTOWER || zoneType == ZoneType.BLUETOWER || zoneType == ZoneType.PURPLETOWER){

            int floorIndex = askWichFloor();

            return new TowerAction(familyPawn, zoneType, player, floorIndex);
        }

        if(zoneType == ZoneType.HARVEST || zoneType == ZoneType.PRODUCTION){

            //int fieldSelected = askWichField();

            //return new WorkAction(familyPawn, zoneType, player, fieldSelected);
        }

        if(zoneType == ZoneType.MARKET){

            ShopName houseSelected = askWichHouse();

            return new MarketAction(familyPawn, player, houseSelected);
        }

        if(zoneType == ZoneType.COUNCILPALACE){

            return new CouncilPalaceAction(familyPawn, player);
        }

        else{
            throw new IllegalArgumentException("Illegal zone: " + zoneType);
        }
    }

    private static ShopName askWichHouse() {

        return null;
    }

    private static int askWichField() {

        return 0;
    }

    private static int askWichFloor() {

        return floor++ ;
    }

    //metodo per test PlayerController. Mi serve solo per resettare la variabile di test floor
    public static void resetFloor(){
        floor = 0;
    }
}*/
