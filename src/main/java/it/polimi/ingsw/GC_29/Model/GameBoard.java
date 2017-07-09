package it.polimi.ingsw.GC_29.Model;

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

    public GameBoard(int numberOfPlayers, FaithPointsTrack faithPointsTrack){

        this.victoryPointsTrack = new Track(numberOfPlayers, 100);

        this.venturesPointsTrack = new Track(numberOfPlayers,26);

        /* this.turnOrderTrack = new Track(1,numberOfPlayers); */

        this.faithPointsTrack = faithPointsTrack;

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
            throw new IllegalArgumentException(String.format("Illegal tower request: %s", zoneType));
        }

        else{

            return towerMap.get(zoneType);
        }
    }

    public Tower getTower(CardColor cardColor){

        for(Tower tower : towerMap.values()){

            if(tower.getCardType() == cardColor){
                return tower;
            }
        }

        throw new IllegalArgumentException(String.format("Illegal tower request: %s", cardColor));

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

    public Dice getDice(FamilyPawnType familyPawnType)  {

        if (familyPawnType==FamilyPawnType.BONUS || familyPawnType==FamilyPawnType.NEUTRAL) {
            System.out.println(("Wrong FamilyType"));
        }

        for (Dice dice : diceLane) {

            if (dice.getColor() == familyPawnType) {
                return dice;
            }
        }
        return null;
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
    }

    public void clearRound(){

        for (Tower tower : towerMap.values()){
            tower.clean();
        }

        for (Workspace workspace : workAreaMap.values()){
            workspace.clean();
        }

        market.clean();
        councilPalace.clean();
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

    public String toTable() {

        StringBuilder bld = new StringBuilder();
        String finalString = "";

        for (Tower tower : towerMap.values()) {
            bld.append(tower.toTable());
        }
        bld.append("\n\n\n");
        for (Workspace workspace : workAreaMap.values()) {
            bld.append(workspace.toTable());
        }
        bld.append("\n\n\n");
        bld.append(market.toTable());
        bld.append("\n\n\n");
        bld.append(councilPalace.toTable());
        bld.append("\n\n\n");
        bld.append( excommunicationLane.toTable());
        bld.append("\n\n\n");
        bld.append(victoryPointsTrack.toTable());
        bld.append("\n\n\n");
        bld.append(venturesPointsTrack.toTable());
        bld.append("\n\n\n");
        bld.append(faithPointsTrack.toTable());


        return bld.toString();
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
