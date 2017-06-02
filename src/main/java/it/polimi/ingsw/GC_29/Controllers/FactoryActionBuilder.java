package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 20/05/2017.

public class FactoryActionBuilder {

    static  HashMap<CardColor,Tower> towerHashMap = GameStatus.getInstance().getGameBoard().getTowerMap();

    public static ActionBuilder getActionBuilder(ZoneType type, FamilyPawn familyPawnSelected, Player player){

        switch (type){
            case GREENTOWER:
                return new TowerActionBuilder(ZoneType.GREENTOWER, familyPawnSelected, player, towerHashMap.get(CardColor.GREEN));

            case YELLOWTOWER:
                return  new TowerActionBuilder(ZoneType.YELLOWTOWER, familyPawnSelected, player, towerHashMap.get(CardColor.YELLOW));

            case BLUETOWER:
                return new TowerActionBuilder(ZoneType.BLUETOWER, familyPawnSelected, player,  towerHashMap.get(CardColor.BLUE));

            case PURPLETOWER:
                return new TowerActionBuilder(ZoneType.PURPLETOWER, familyPawnSelected, player,  towerHashMap.get(CardColor.PURPLE));

            case MARKET:
                return new MarketActionBuilder(familyPawnSelected, player);

            case COUNCILPALACE:
                return new CouncilPalaceActionBuilder(familyPawnSelected, player);

            case HARVEST:
                return new WorkActionBuilder(ZoneType.HARVEST, familyPawnSelected, player);

            case PRODUCTION:
                return new WorkActionBuilder(ZoneType.PRODUCTION, familyPawnSelected, player);

            default: return null;
        }
    }
}*/
