package it.polimi.ingsw.GC_29.Components;

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
}
