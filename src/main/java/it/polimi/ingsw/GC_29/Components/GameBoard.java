package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ActionType;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class GameBoard {
    private Track victoryPointsTrack;
    private Track venturesPointsTrack;
    private Track turnOrderTrack;
    private FaithPointsTrack faithPointsTrack;
    private HashMap<CardColor,Tower> towerMap;
    private ExcommunicationLane excommunicationLane;
    private CouncilPalaceActionSpace councilPalace;
    private Workspace harvestArea;
    private Workspace productionArea;
    private Market market;
    private Dice[] diceLane;

    public GameBoard(int numberOfPlayers,ExcommunicationTile tile_1,ExcommunicationTile tile_2,ExcommunicationTile tile_3){

        this.victoryPointsTrack = new Track(numberOfPlayers, 100);
        this.venturesPointsTrack = new Track(numberOfPlayers,26);
        this.turnOrderTrack = new Track(1,numberOfPlayers);
        int[] victoryPointsForFaithTrack = {0,1,2,3,4,5,7,9,11,13,15,17,19,22,25,30};
        this.faithPointsTrack = new FaithPointsTrack(numberOfPlayers,16,victoryPointsForFaithTrack);
        this.towerMap = new HashMap<CardColor,Tower>();
        for (CardColor color : CardColor.values()){
            towerMap.put(color,new Tower(color));
        }
        this.excommunicationLane = new ExcommunicationLane(numberOfPlayers,tile_1,tile_2,tile_3);
        this.councilPalace = new CouncilPalaceActionSpace(numberOfPlayers);
        this.harvestArea = new Workspace(ActionType.HARVEST,numberOfPlayers);
        this.productionArea = new Workspace(ActionType.PRODUCTION,numberOfPlayers);
        this.market = new Market(numberOfPlayers);
        this.diceLane = new Dice[3];


    }

    public Track getVictoryPointsTrack() {
        return victoryPointsTrack;
    }

    public Track getVenturesPointsTrack() {
        return venturesPointsTrack;
    }

    public Track getTurnOrderTrack() {
        return turnOrderTrack;
    }

    public FaithPointsTrack getFaithPointsTrack() {
        return faithPointsTrack;
    }

    public HashMap<CardColor,Tower> getTowerMap() {
        return towerMap;
    }

    public ExcommunicationLane getExcommunicationLane() {
        return excommunicationLane;
    }

    public CouncilPalaceActionSpace getCouncilPalace() {
        return councilPalace;
    }

    public Workspace getHarvestArea() {
        return harvestArea;
    }

    public Workspace getProductionArea() {
        return productionArea;
    }

    public Market getMarket() {
        return market;
    }

    public Dice[] getDiceLane() {
        return diceLane;
    }

    public void setTowerMap(HashMap<CardColor, Tower> towerMap) { // per il test sul playerController
        this.towerMap = towerMap;
    }

    public void clearAll() {
        victoryPointsTrack.clean();
        venturesPointsTrack.clean();
        turnOrderTrack.clean();
        faithPointsTrack.clean();
        towerMap.clear();
        excommunicationLane.clean();
        harvestArea.clean();
        productionArea.clean();
        market.clean();
        // councilPalace.clean()
        for (Dice dice : diceLane) {
            dice = null;
        }
    }



    @Override
    public String toString() {
        return "GameBoard{" + "victoryPointsTrack=" + victoryPointsTrack + ", venturesPointsTrack=" + venturesPointsTrack + ", turnOrderTrack=" + turnOrderTrack + ", faithPointsTrack=" + faithPointsTrack + ", towerMap=" + towerMap + ", excommunicationLane=" + excommunicationLane + ", councilPalace=" + councilPalace + ", harvestArea=" + harvestArea + ", productionArea=" + productionArea + ", market=" + market + ", diceLane=" + Arrays.toString(diceLane) + '}';
    }
}
