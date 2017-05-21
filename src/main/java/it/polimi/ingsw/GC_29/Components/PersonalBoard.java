package it.polimi.ingsw.GC_29.Components;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
/**
 * Created by icedoom on 18/05/17.
 */
public class PersonalBoard {
    private BonusTile bonusTile;
    private Lane buildingLane;
    private Lane venturesLane;
    private Lane familyLane;
    private TerritoryLane territoryLane;
    private GoodSet goodCoffer; // TODO: serve???

    public PersonalBoard(){
        // bonusTile = new BonusTile(); //mancano gli obtainEffect da passare per creare le bonusTile
        buildingLane = new Lane(CardColor.YELLOW);
        venturesLane = new Lane(CardColor.PURPLE);
        familyLane = new Lane(CardColor.BLUE);
        territoryLane = new TerritoryLane(CardColor.GREEN);
        goodCoffer = new GoodSet(0,0,0,0,0,0,0);
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

    public GoodSet getGoodCoffer() {
        return goodCoffer;
    }

    public void setGoodCoffer(GoodSet goodCoffer) {
        this.goodCoffer = goodCoffer;
    }
}
