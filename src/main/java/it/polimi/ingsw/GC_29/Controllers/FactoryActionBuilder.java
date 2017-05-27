package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.Tower;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.HashMap;

/**
 * Created by Christian on 20/05/2017.

public class FactoryActionBuilder {

    static  HashMap<CardColor,Tower> towerHashMap = GameStatus.getInstance().getGameBoard().getTowerMap();

    public static ActionBuilder getActionBuilder(ZoneType type, FamilyPawn familyPawnSelected, PlayerStatus playerStatus){

        switch (type){
            case GREENTOWER:
                return new TowerActionBuilder(ZoneType.GREENTOWER, familyPawnSelected, playerStatus, towerHashMap.get(CardColor.GREEN));

            case YELLOWTOWER:
                return  new TowerActionBuilder(ZoneType.YELLOWTOWER, familyPawnSelected, playerStatus, towerHashMap.get(CardColor.YELLOW));

            case BLUETOWER:
                return new TowerActionBuilder(ZoneType.BLUETOWER, familyPawnSelected, playerStatus,  towerHashMap.get(CardColor.BLUE));

            case PURPLETOWER:
                return new TowerActionBuilder(ZoneType.PURPLETOWER, familyPawnSelected, playerStatus,  towerHashMap.get(CardColor.PURPLE));

            case MARKET:
                return new MarketActionBuilder(familyPawnSelected, playerStatus);

            case COUNCILPALACE:
                return new CouncilPalaceActionBuilder(familyPawnSelected, playerStatus);

            case HARVEST:
                return new WorkActionBuilder(ZoneType.HARVEST, familyPawnSelected, playerStatus);

            case PRODUCTION:
                return new WorkActionBuilder(ZoneType.PRODUCTION, familyPawnSelected, playerStatus);

            default: return null;
        }
    }
}*/
