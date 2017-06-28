package it.polimi.ingsw.GC_29.Components;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;

import java.util.Arrays;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class Tower implements Cleanable {
    private Floor[] floors;
    private final int NUMBEROFFLOORS = 4;
    private ZoneType zoneType;
    private boolean occupied;
    private int costIfOccupied;

    public Tower(ZoneType zoneType){

        if (zoneType != ZoneType.GREENTOWER && zoneType != ZoneType.YELLOWTOWER && zoneType != ZoneType.BLUETOWER && zoneType != ZoneType.PURPLETOWER){
            throw new IllegalArgumentException("Illegal tower type: " + zoneType);
        }

        this.floors = new Floor[NUMBEROFFLOORS];
        floors[0] = new Floor(new ObtainEffect(0,0,0,0,0,0,0),1);
        floors[1] = new Floor(new ObtainEffect(0,0,0,0,0,0,0),3);
        if(zoneType == ZoneType.GREENTOWER){
            floors[2] = new Floor(new ObtainEffect(1,0,0,0,0,0,0),5);
            floors[3] = new Floor(new ObtainEffect(2,0,0,0,0,0,0),7);
        }
        if(zoneType == ZoneType.BLUETOWER){
            floors[2] = new Floor(new ObtainEffect(0,1,0,0,0,0,0),5);
            floors[3] = new Floor(new ObtainEffect(0,2,0,0,0,0,0),7);
        }
        if(zoneType == ZoneType.YELLOWTOWER){
            floors[2] = new Floor(new ObtainEffect(0,0,0,0,0,1,0),5);
            floors[3] = new Floor(new ObtainEffect(0,0,0,0,0,2,0),7);
        }
        if(zoneType == ZoneType.PURPLETOWER){
            floors[2] = new Floor(new ObtainEffect(0,0,1,0,0,0,0),5);
            floors[3] = new Floor(new ObtainEffect(0,0,2,0,0,0,0),7);
        }

        this.zoneType = zoneType;
        this.occupied = false;
        this.costIfOccupied = 3;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public void clean() {
        for (int i = 0; i < NUMBEROFFLOORS; i++){
            floors[i].clean();
            occupied = false;
        }
    }

    @Override
    public String toString() {
        return "Tower{" +
                "floors=" + Arrays.toString(floors) +
                ", cardType=" + zoneType +
                ", occupied=" + occupied +
                ", costIfOccupied=" + costIfOccupied +
                '}';
    }

    /*@Override
    public String toString() {
        AsciiArtTable towerTable = new AsciiArtTable();
        towerTable.addHeadline(zoneType);
        towerTable.addHeaderCols("Floor", "Card", "ActionSpace");

        for (int i = 0; i < floors.length; i++) {
            Floor floor = floors[i];
            towerTable.add(i, floor.getDevelopmentCard().toString(), floor.getActionSpace().toString());
        }

        return towerTable.getOutput();
    }*/

    public Floor[] getFloors() {
        return floors;
    }

    public Floor getFloor(int index) {
        return floors[index];
    }

    public ZoneType getTowerType(){

        return zoneType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getCostIfOccupied() {
        return costIfOccupied;
    }

    public CardColor getCardType() {

        if(zoneType == ZoneType.GREENTOWER){
            return CardColor.GREEN;
        }

        else if(zoneType == ZoneType.YELLOWTOWER){
            return CardColor.YELLOW;
        }

        else if(zoneType == ZoneType.BLUETOWER){
            return CardColor.BLUE;
        }

        else {
            return CardColor.PURPLE;
        }
    }

    public String toTable() {
        /*AsciiTable heading = new AsciiTable();
        heading.addRule();
        heading.addRow(zoneType);
        String header = heading.render();*/
        AsciiTable towerTable = new AsciiTable();
        for (int i = 0; i < floors.length; i++) {
            towerTable.addRule();
            towerTable.addRow("Floor: " + i, "Card: \n" + floors[i].getDevelopmentCard().toString(), "Action Space:\n" + floors[i].getActionSpace().toString());
        }
        towerTable.setTextAlignment(TextAlignment.CENTER);
        return "\n\n\n" + zoneType + "\n" + towerTable.render();
    }
}
