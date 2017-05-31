package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ZoneType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class GameBoard {
    private Track victoryPointsTrack;
    private Track venturesPointsTrack;
    /* private Track turnOrderTrack; */
    private FaithPointsTrack faithPointsTrack;
    private EnumMap<ZoneType,Tower> towerMap;
    private EnumMap<ZoneType, Workspace> workAreaMap;
    private ExcommunicationLane excommunicationLane;
    private CouncilPalaceActionSpace councilPalace;
    private Market market;
    private ArrayList<Dice> diceLane;

    public GameBoard(int numberOfPlayers){

        this.victoryPointsTrack = new Track(numberOfPlayers, 100);

        this.venturesPointsTrack = new Track(numberOfPlayers,26);

        /* this.turnOrderTrack = new Track(1,numberOfPlayers); */

        int[] victoryPointsForFaithTrack = {0,1,2,3,4,5,7,9,11,13,15,17,19,22,25,30};

        this.faithPointsTrack = new FaithPointsTrack(numberOfPlayers,16,victoryPointsForFaithTrack);

        this.excommunicationLane = new ExcommunicationLane(numberOfPlayers);

        this.towerMap = new EnumMap<>(ZoneType.class);

        this.workAreaMap = new EnumMap<>(ZoneType.class);

        createZones(numberOfPlayers);

        this.diceLane = new ArrayList<>();
        this.diceLane.add(new Dice(FamilyPawnType.BLACK));
        this.diceLane.add(new Dice(FamilyPawnType.WHITE));
        this.diceLane.add(new Dice(FamilyPawnType.ORANGE));

    }

    private void createZones(int numberOfPlayers) {

        for (ZoneType zoneType : ZoneType.values()){

            if(zoneType == ZoneType.GREENTOWER
                || zoneType == ZoneType.YELLOWTOWER
                || zoneType == ZoneType.BLUETOWER
                || zoneType == ZoneType.PURPLETOWER){

                towerMap.put(zoneType, new Tower(zoneType));
            }

            if(zoneType == ZoneType.HARVEST || zoneType == ZoneType.PRODUCTION){

                workAreaMap.put(zoneType, new Workspace(zoneType, numberOfPlayers));
            }

            if(zoneType == ZoneType.MARKET){
                market = new Market(numberOfPlayers);
            }

            if(zoneType == ZoneType.COUNCILPALACE){
                councilPalace = new CouncilPalaceActionSpace(numberOfPlayers);
            }
        }
    }

    public Track getVictoryPointsTrack() {
        return victoryPointsTrack;
    }

    public Track getVenturesPointsTrack() {
        return venturesPointsTrack;
    }

    /* public Track getTurnOrderTrack() {
        return turnOrderTrack;
    } */

    public FaithPointsTrack getFaithPointsTrack() {
        return faithPointsTrack;
    }

    public Map<ZoneType,Tower> getTowerMap() {
        return towerMap;
    }

    public Map<ZoneType, Workspace> getWorkAreaMap() {
        return workAreaMap;
    }

    public ExcommunicationLane getExcommunicationLane() {
        return excommunicationLane;
    }

    public CouncilPalaceActionSpace getCouncilPalace() {
        return councilPalace;
    }

    public Workspace getHarvestArea() {

        return workAreaMap.get(ZoneType.HARVEST);
    }

    public Workspace getProductionArea() {

        return workAreaMap.get(ZoneType.PRODUCTION);
    }

    public Tower getTower(ZoneType zoneType){
        if (zoneType != ZoneType.GREENTOWER && zoneType != ZoneType.YELLOWTOWER && zoneType != ZoneType.BLUETOWER && zoneType != ZoneType.PURPLETOWER){
            throw new IllegalArgumentException("Illegal tower equest: " + zoneType);
        }

        else{

            return towerMap.get(zoneType);
        }
    }

    public Workspace getWorkArea(ZoneType area){
        if (area != ZoneType.HARVEST && area != ZoneType.PRODUCTION){
            throw new IllegalArgumentException("Illegal areaRequest: " + area);
        }
        else {

            return workAreaMap.get(area);
        }

    }

    public Market getMarket() {
        return market;
    }

    public List<Dice> getDiceLane() {
        return diceLane;
    }





    public void clearAll(){

        victoryPointsTrack.clean();

        venturesPointsTrack.clean();

        /* turnOrderTrack.clean(); */

        faithPointsTrack.clean();

        for (Tower tower : towerMap.values()){
            tower.clean();
        }

        for (Workspace workspace : workAreaMap.values()){
            workspace.clean();
        }

        excommunicationLane.clean();

        market.clean();
        councilPalace.clean();

        for (Dice dice : diceLane) {
            dice = null;
        }
    }


    @Override
    public String toString() {
        return "GameBoard{" +
                "victoryPointsTrack=" + victoryPointsTrack +
                ", venturesPointsTrack=" + venturesPointsTrack +
                /* ", turnOrderTrack=" + turnOrderTrack +*/
                ", faithPointsTrack=" + faithPointsTrack +
                ", towerMap=" + towerMap +
                ", workAreaMap=" + workAreaMap +
                ", excommunicationLane=" + excommunicationLane +
                ", councilPalace=" + councilPalace +
                ", market=" + market +
                ", diceLane=" + diceLane +
                '}';
    }


    /**
     * setTurn add all the cards to the right towers
     * @param greenDeck
     * @param blueDeck
     * @param yellowDeck
     * @param purpleDeck
     */
    public void setTurn(DevelopmentCard[] greenDeck, DevelopmentCard[] blueDeck, DevelopmentCard[] yellowDeck, DevelopmentCard[] purpleDeck) {

        for (int i = 0; i < greenDeck.length; i++) {
            towerMap.get(ZoneType.GREENTOWER).getFloor(i).addCard(greenDeck[i]);
            towerMap.get(ZoneType.BLUETOWER).getFloor(i).addCard(blueDeck[i]);
            towerMap.get(ZoneType.YELLOWTOWER).getFloor(i).addCard(yellowDeck[i]);
            towerMap.get(ZoneType.PURPLETOWER).getFloor(i).addCard(purpleDeck[i]);
        }

    }
}
