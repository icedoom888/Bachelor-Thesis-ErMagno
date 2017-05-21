package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class TowerAction extends Action {

    private Tower towerChosen;
    private int floorIndex;
    private GoodSet temporaryGoodSet; // accumula il bonus dell'actionSpace
    private GoodSet towerCost;
    private Cost cardCost;
    private GoodSet discount; // discount che c'è se non è realAction e viene passato dall'ActionEffect già selezionato se c'è l'alternativa

    public TowerAction(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction, PlayerStatus playerStatus, Tower towerChosen, int floorIndex) {
        super(pawnSelected, actionSelected, workersSelected, realAction, playerStatus);
        this.towerChosen = towerChosen;
        this.floorIndex = floorIndex;
        this.actionSpaceSelected = towerChosen.getFloor(floorIndex).getActionSpace();
        this.temporaryGoodSet = new GoodSet();
        this.cardCost = towerChosen.getFloor(floorIndex).getDevelopmentCard().getCost();

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
        return super.isPossible() && !checkFamilyPresence() && checkSufficientGoodsForCard();
    }

    /**
     * This method checks if there already is a player's familiar in the tower
     * @return true if there is a player's familiar, false otherwise
     */
    private boolean checkFamilyPresence() { // va a controllare nella torre se c'è un familiare del player

        boolean familiarPresent = false;
        for (Floor floor : towerChosen.getFloors()) {
            ActionSpace actionSpace = floor.getActionSpace();
            if (actionSpace.isOccupied()) {
                familiarPresent = actionSpace.getPawnPlaced().searchFamiliar(this.pawnSelected);
            }
        }
        return familiarPresent;
    }

    /**
     * This method checks if the tower is occupied: in this case it controls if the player
     * has a BM that make him access the tower, otherwise it checks if the player has enough coins
     * to enter the tower
     * @return true if you can access the tower, false otherwise
     */
    private boolean isOccupied() {

        if (towerChosen.isOccupied()) {
            Filter.apply(playerStatus, towerCost); // da rivedere, passo un int al filter
            if (playerStatus.getActualGoodSet().getGoodAmount(GoodType.COINS) >= towerChosen.getGoldCostIfOccupied()) {
                //This branch is taken if the player have enough coins to pay the access to the occupied tower
                towerCost.addGoodSet(new GoodSet(0,0,towerChosen.getGoldCostIfOccupied(),0,0,0,0));
                return true;
            }
            else {
                System.out.println("You don't have enough coins to acces to the tower!");
                return false;
            }
        }

        return true;
    }

    private void setActionSpaceEffect() { // prendo l'effetto dell'action space, se esiste, lo filtro (senza usare l'effect.execute) e lo assegno al temporaryGoodSet
    }

    private void filterCardCost() { // ricopia il costo della carta in cardCost, filtro di B&M sugli eventuali sconti o malus modificando cardCost
        // vedo se è realAction per gestire eventuale discount su carta - gestisci bene
        // poi fa cardCost = cardCost - towerCost + temporaryGoodSet
    }

    private boolean checkSufficientGoodsForCard() {
                                                            // confronta cardCost con le risorse del player -> true/false
        Filter.apply(playerStatus, cardCost);

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
