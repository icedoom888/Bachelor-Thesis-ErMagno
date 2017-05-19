package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import javax.smartcardio.Card;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class TowerAction extends Action {

    private Tower towerChosen;
    private int floorIndex;
    private GoodSet temporaryGoodSet; // accumula il bonus dell'actionSpace
    private GoodSet towerCost;
    private GoodSet cardCost;
    private GoodSet discount; // discount che c'è se non è realAction e viene passato dall'ActionEffect già selezionato se c'è l'alternativa

    public TowerAction(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction, PlayerStatus playerStatus, Tower towerChosen, int floorIndex) {
        super(pawnSelected, actionSelected, workersSelected, realAction, playerStatus);
        this.towerChosen = towerChosen;
        this.floorIndex = floorIndex;
        this.actionSpaceSelected = towerChosen.getFloor(floorIndex).getActionSpace();
        this.temporaryGoodSet = new GoodSet();


    }

    @Override
    public void execute() {

        // isPossible

        // ramo true della isPossible
        super.addPawn();

        // payCard
        // getCard
        // handleCard

        // update
    }

    @Override
    protected boolean isPossible() {
        return super.isPossible() && checkFamilyPresence() && checkSufficientGoodsForCard();
    }

    private boolean checkFamilyPresence() { // va a controllare nella torre se c'è un familiare del player
        return true;
    }

    private boolean isOccupied() { // se torre occupata controllo che il player abbia 3 monete e se ce le ha salvo nel towerCost
                                   // se non ce le ha controllo B&M carta leader, se nemmeno chiudo
        return true;
    }

    private void setActionSpaceEffect() { // prendo l'effetto dell'action space, se esiste, lo filtro (senza usare l'effect.execute) e lo assegno al temporaryGoodSet
    }

    private void filterCardCost() { // ricopia il costo della carta in cardCost, filtro di B&M sugli eventuali sconti o malus modificando cardCost
        // vedo se è realAction per gestire eventuale discount su carta - gestisci bene
        // poi fa cardCost = cardCost - towerCost + temporaryGoodSet
    }

    private boolean checkSufficientGoodsForCard() {
        filterCardCost();
                                                            // confronta cardCost con le risorse del player -> true/false

        return true;
    }



    /*
    * Fino a qui tutto contenuto in isPossible
     */

    private void payCard() {
    }

    /*
    * private Card getCard() { // prende e posiziona carta
    *    return Card;
    * }

     */

    private void activateCardEffects() {
        // se la carta è blu vado a salvare i permanenti
    }






    protected void update() {

    }

}
