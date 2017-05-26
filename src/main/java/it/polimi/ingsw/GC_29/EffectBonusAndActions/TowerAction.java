package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class TowerAction extends Action {

    //TODO testing per nuove condizioni di isPossible
    //TODO gestire il costo della carta sia per controlli (con le alternative) sia per l'esecuzione

    private Tower towerChosen;
    private int floorIndex;
    private GoodSet temporaryGoodSet; // accumula il bonus dell'actionSpace
    private GoodSet towerCost; // 3 coins
    private CardCost cardCost;
    private ArrayList<Cost> possibleCardCosts;
    private DevelopmentCard cardSelected;

    public TowerAction(
            FamilyPawn pawnSelected,
            ActionType actionSelected,
            int workersSelected,
            boolean realAction,
            PlayerStatus playerStatus,
            Tower towerChosen,
            int floorIndex) {

        super(pawnSelected, actionSelected, workersSelected, realAction, playerStatus);
        this.towerChosen = towerChosen;
        this.floorIndex = floorIndex;
        this.actionSpaceSelected = towerChosen.getFloor(floorIndex).getActionSpace();
        this.temporaryGoodSet = new GoodSet();
        this.cardSelected = towerChosen.getFloor(floorIndex).getDevelopmentCard();
        this.cardCost = towerChosen.getFloor(floorIndex).getDevelopmentCard().getCardCost();
        this.towerCost = new GoodSet();
        this.possibleCardCosts = new ArrayList<>();

    }

    public TowerAction(FamilyPawn pawnSelected,
                       ActionType actionSelected,
                       int workersSelected,
                       boolean realAction,
                       PlayerStatus playerStatus,
                       Tower towerChosen,
                       int floorIndex,
                       GoodSet discount) {

        super(pawnSelected, actionSelected, workersSelected, realAction, playerStatus);
        this.towerChosen = towerChosen;
        this.floorIndex = floorIndex;
        this.actionSpaceSelected = towerChosen.getFloor(floorIndex).getActionSpace();
        this.temporaryGoodSet = new GoodSet();
        this.cardSelected = towerChosen.getFloor(floorIndex).getDevelopmentCard();
        this.cardCost = towerChosen.getFloor(floorIndex).getDevelopmentCard().getCardCost();
        this.towerCost = new GoodSet();
        this.possibleCardCosts = new ArrayList<>();
    }

    @Override
    public void execute() {

        super.addPawn();
        payCard();
        giveCard();
        activateCardEffects();
        update();
    }

    @Override
    public boolean isPossible() {

        if(super.isPossible()){
            if(!checkFamilyPresence()){
                if (isTowerAccessPossible()) {
                    if (laneAvailable()) {
                        if (cardCost.isWithPrice()) {
                            if (checkSufficientGoodsForCard()) return true;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
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
     * has a BM that makes him access the tower; if the player has it return true, otherwise it checks if the player has enough coins
     * to enter the tower
     * @return true if you can access the tower, false otherwise
     */
    private boolean isTowerAccessPossible() {

        if (towerChosen.isOccupied()) {

            if (!Filter.applySpecial(playerStatus, towerCost)) {
                int goldCost = towerChosen.getCostIfOccupied();

                if (playerStatus.getActualGoodSet().getGoodAmount(GoodType.COINS) >= goldCost) {
                    //This branch is taken if the player have enough coins to pay the access to the occupied tower
                    towerCost = new GoodSet(0,0,goldCost,0,0,0,0);
                    return true;

                } else {
                    System.out.println("You don't have enough coins to access to the tower!");
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * This method saves the resources of the actionSpace Effect, after being filtered, in order to make other
     * controls in the future. The effect is not activated.
     */
    private void setActionSpaceEffect() {

        ObtainEffect effect = (ObtainEffect) this.actionSpaceSelected.getEffect();
        GoodSet actionSpaceGoodSet = effect.getGoodsObtained();
        Filter.apply(this.playerStatus, actionSpaceGoodSet);
        temporaryGoodSet = actionSpaceGoodSet;

    }


    /**
     * After saving the potential resources that the actionSpace can give to the player,
     * this method filters the cost of the card, add the cost of the tower and subtract the resources received
     * by the actionSpace effect.
     * Then it checks if the player has enough resources in his goodSet to pay the selected card
     * @return true if there are enough resources to pay the card, false otherwise
     */
    private boolean checkSufficientGoodsForCard() {
        
        setActionSpaceEffect();
        
        ArrayList<Cost> costList = new ArrayList<Cost>();
        Filter.apply(playerStatus, cardCost, costList, actionType);

        GoodSet playerGoodSet = new GoodSet(playerStatus.getActualGoodSet());
        GoodSet necessaryGoodSet;
        GoodSet realCost;

        playerGoodSet.subGoodSet(towerCost);
        playerGoodSet.addGoodSet(temporaryGoodSet);

        boolean value = false;
        for (Cost cost : costList) {

            necessaryGoodSet = cost.getNecessaryResources();
            realCost = cost.getCost();

            if (playerGoodSet.enoughResources(necessaryGoodSet)
                    && playerGoodSet.enoughResources(realCost)) {

                possibleCardCosts.add(cost);
                value = true;
            }
        }

        return value;
    }

    
    
    private boolean checkTerritorySlotAvailability() {
        TerritoryLane lane = playerStatus.getPersonalBoard().getTerritoryLane();
        int index = lane.getFirstFreeSlotIndex();
        return playerStatus.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS) == lane.getSlot(index).getMilitaryPointsNeeded();
    }

    /**
     * laneAvailable checks if the lane where the card will be put has free space.
     * If the tower chosen contains Territory cards, there is another control to
     * check if the player has enough military points to put the card in the first
     * free slot
     * @return true if lane aren't full e the player has enough military points to pay
     * the necessary militaryNecessaryPoints, false otherwise
     */
    private boolean laneAvailable() {

        boolean var1 = true;
        CardColor actualColor = towerChosen.getCardType();
        if (actualColor == CardColor.GREEN) {
            var1 = checkTerritorySlotAvailability();
        }

        return var1
                && playerStatus.getPersonalBoard().getLane(actualColor).isFree();

    }

    private void payCard() {

        if(!cardCost.isWithPrice()){
            return;
        }

        if (towerCost.getGoodAmount(GoodType.COINS) != 0) {
            System.out.println("You have to pay the access to the tower because it's occupied: ");
            System.out.println("The cost is " + towerCost.getGoodAmount(GoodType.COINS) + " coins.");
        }

        System.out.println("The actual cost of the card is: \n" + cardCost);

        if (possibleCardCosts.size() > 1) {
            System.out.println("Applying your bonusAndMalus you can choose between different costs: ");

            int i = 1;
            for (Cost cost : possibleCardCosts) {
                System.out.println(i + ") " + cost);
                i++;
            }

            System.out.println("Write the number of the option chosen");
            Scanner scanner = new Scanner(System.in);
            int answer = scanner.nextInt();
            playerStatus.getActualGoodSet().subGoodSet(possibleCardCosts.get(answer).getCost());
            System.out.println("The card has been paid");
            return;
        }

        System.out.println("Applying your bonusAndMalus you have to pay: ");
        System.out.println(possibleCardCosts.get(0));

        playerStatus.getActualGoodSet().subGoodSet(possibleCardCosts.get(0).getCost());
        System.out.println("The card has been paid");
    }



    /**
     * This method removes the Card from the chosen floor and gives this card to
     * the player. From the colour of the card the method chooses where to add
     * the card (TerritoryLane, FamilyLane, BuildingLane, VenturesLane).
     */
    private void giveCard() {

        DevelopmentCard card = towerChosen.getFloor(floorIndex).removeCard();
        PersonalBoard playerBoard = playerStatus.getPersonalBoard();
        switch (card.getColor()) {
            case GREEN:
                playerBoard.getTerritoryLane().addCard(card);
                break;
            case BLUE:
                playerBoard.getFamilyLane().addCard(card);
                break;
            case YELLOW:
                playerBoard.getBuildingLane().addCard(card);
                break;
            case PURPLE:
                playerBoard.getVenturesLane().addCard(card);
                break;
            default:
                System.out.println("Ops! There has been an error!");
        }

    }


    /**
     * This method activates all the immediate effects of the selected card
     */
    private void activateCardEffects() {

        ArrayList<Effect> immediateEffects = this.cardSelected.getImmediateEffect();
        for (Effect immediateEffect : immediateEffects) {
            immediateEffect.execute(playerStatus);
        }
    }


    /**
     * This method update:
     * sets availability false for the pawn used (super)
     * sets the actionSpace as occupied (super)
     * sets permanent effects of the card in playerStatus
     * updates number of card per colour in playerStatus
     * ...
     */
    protected void update() {
        
        //TODO: dividi in più metodi
        //TODO: magari il controllo al posto che sul cardColor fallo su BonusEffect

        super.update();

        if (cardSelected.getColor() == CardColor.BLUE) {
            for (Effect effect : cardSelected.getPermanentEffect()) {
                playerStatus.getBonusAndMalusOnAction().add((BonusAndMalusOnAction) effect); //cast obbligatorio
            }
        }

        int numberOfCards = playerStatus.getCardsOwned().get(cardSelected.getColor());
        numberOfCards++;
        playerStatus.getCardsOwned().put(cardSelected.getColor(), numberOfCards);

    }


}
