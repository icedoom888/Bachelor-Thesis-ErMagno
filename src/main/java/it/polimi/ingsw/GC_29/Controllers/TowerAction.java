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
    private GoodSet temporaryGoodSet;
    private GoodSet towerCost;
    private GoodSet cardCost;

    public TowerAction(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction, Tower towerChosen, int floorIndex) {
        super(pawnSelected, actionSelected, workersSelected, realAction);
        this.towerChosen = towerChosen;
        this.floorIndex = floorIndex;
        this.actionSpaceSelected = towerChosen.getFloor(floorIndex).getActionSpace();
        this.temporaryGoodSet = new GoodSet();


    }

    @Override
    public void execute(PlayerStatus playerStatus) {

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

    private boolean checkSufficientGoodsForCard() {
        filterCardCost();
                                                            // confronta cardCost con le risorse del player -> true/false
        return true;
    }

    private void filterCardCost() { // ricopia il costo della carta in cardCost, filtro di B&M sugli eventuali sconti o malus modificando cardCost
                                    // poi fa cardCost = cardCost - towerCost + temporaryGoodSet
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
        // se la carta è blu vado a salvare i permanent
    }






    protected void update(PlayerStatus playerStatus) {

    }

}
