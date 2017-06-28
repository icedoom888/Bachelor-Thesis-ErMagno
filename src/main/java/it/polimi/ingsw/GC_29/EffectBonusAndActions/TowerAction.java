package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class TowerAction extends Action {


    private Tower towerChosen;
    private int floorIndex;
    private GoodSet actionSpaceGoodSet; // accumula il bonus dell'actionSpace
    private GoodSet towerCost; // 3 coins
    private CardCost cardCost;
    private HashMap<Integer, Cost> possibleCardCosts;
    private int costChosen;
    private DevelopmentCard cardSelected;

    public TowerAction(
            ZoneType actionSelected,
            GameStatus gameStatus,
            int floorIndex) {

        super(actionSelected, gameStatus);
        this.towerChosen = this.gameStatus.getGameBoard().getTower(zoneType);
        this.floorIndex = floorIndex;
        this.actionSpaceSelected = towerChosen.getFloor(floorIndex).getActionSpace();
        this.actionSpaceGoodSet = new GoodSet();
        this.towerCost = new GoodSet();
        this.possibleCardCosts = new HashMap<>();

    }


    @Override
    public void execute() throws Exception {

        super.payWorkers();
        super.addPawn();
        pay();
        giveCard();
        activateCardEffects();
        update();
    }

    @Override
    public boolean isPossible() {

        return super.isPossible()
                && !checkFamilyPresence()
                && isTowerAccessPossible()
                && laneAvailable()
                && checkSufficientGoodsForCard();
    }



    /**
     * This method checks if there already is a player's familiar in the tower
     * @return true if there is a player's familiar, false otherwise
     */
    private boolean checkFamilyPresence() { // va a controllare nella torre se c'è un familiare del player

        // TODO: bonus e neutra

        boolean familiarPresent = false;
        for (Floor floor : towerChosen.getFloors()) {
            ActionSpace actionSpace = floor.getActionSpace();
            if (actionSpace.isOccupied()) {
                familiarPresent = actionSpace.getPawnPlaced().searchFamiliar(this.temporaryPawn);
                if (!familiarPresent) {
                    return false;
                }
            }
        }
        return familiarPresent;
    }

    /**
     * This method checks if the tower is occupied: in this case it controls if the player
     * has a BM that makes him access the tower; if the player has it return true, otherwise it checks if the player has enough coins
     * to enter the tower.
     * @return true if you can access the tower, false otherwise
     */
    private boolean isTowerAccessPossible() {

        if (towerChosen.isOccupied()) {

            if (!Filter.applySpecial(player, SpecialBonusAndMalus.FREETOWER)) {
                int goldCost = towerChosen.getCostIfOccupied();

                if (player.getActualGoodSet().getGoodAmount(GoodType.COINS) >= goldCost) {
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

        // TODO: inserire assertion sul tipo dell'effetto, in modo da assicurarci a livello di debug che il tipo sia un ObtainEffect

        ObtainEffect effect = (ObtainEffect) this.actionSpaceSelected.getEffect();
        GoodSet actionSpaceGoodSet = effect.getGoodsObtained();
        Filter.apply(this.player, actionSpaceGoodSet);
        this.actionSpaceGoodSet = actionSpaceGoodSet;

    }


    /**
     * After saving the potential resources that the actionSpace can give to the player,
     * this method filters the cost of the card, add the cost of the tower and subtract the resources received
     * by the actionSpace effect.
     * Then it checks if the player has enough resources in his goodSet to pay the selected card
     * @return true if there are enough resources to pay the card, false otherwise
     */
    private boolean checkSufficientGoodsForCard() {
        
        setCardSelected(towerChosen.getFloor(floorIndex).getDevelopmentCard());

        if (cardSelected == null) return false;

        setCardCost(cardSelected.getCardCost());

        if(cardCost.isWithPrice()){

            setActionSpaceEffect();

            ArrayList<Cost> costList = new ArrayList<>();
            Filter.apply(player, cardCost, costList, zoneType);

            GoodSet playerGoodSet = new GoodSet(player.getActualGoodSet());
            GoodSet necessaryGoodSet;
            GoodSet realCost;

            playerGoodSet.subGoodSet(towerCost);
            playerGoodSet.addGoodSet(actionSpaceGoodSet);

            int i = 0;
            boolean value = false;
            for (Cost cost : costList) {

                if (cost != null) {
                    necessaryGoodSet = cost.getNecessaryResources();
                    realCost = cost.getCost();

                    if (playerGoodSet.enoughResources(necessaryGoodSet)
                            && playerGoodSet.enoughResources(realCost)) {

                        possibleCardCosts.put(i,cost);
                        value = true;
                        i++;
                    }
                }

            }

            return value;

        }

        return true;


    }

    
    
    private boolean checkTerritorySlotAvailability() {
        TerritoryLane lane = player.getPersonalBoard().getTerritoryLane();
        int index = lane.getFirstFreeSlotIndex();
        return player.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS) >= lane.getSlot(index).getMilitaryPointsNeeded();
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
        CardColor type = towerChosen.getCardType();
        if (type == CardColor.GREEN
                && !Filter.applySpecial(player, SpecialBonusAndMalus.NOMILITARYFORTERRITORY)) {

            var1 = checkTerritorySlotAvailability();
        }

        return var1
                && player.getPersonalBoard().getLane(type).isFree();
    }

    /*private void payCard() throws Exception {

        GoodSet goodSetToPay;

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

            int answer = 0; // random

            goodSetToPay = possibleCardCosts.get(answer).getCost();
            goodSetToPay.setNegative();

            player.updateModelTracks(goodSetToPay);
            System.out.println("The card has been paid");
            return;
        }

        System.out.println("Applying your bonusAndMalus you have to pay: ");

        goodSetToPay = possibleCardCosts.get(0).getCost();

        System.out.println(goodSetToPay);

        goodSetToPay.setNegative();

        player.updateModelTracks(goodSetToPay);
        System.out.println("The card has been paid");
    }*/


    private void pay() {
        GoodSet goodSetToPay = new GoodSet();

        if (towerCost.getGoodAmount(GoodType.COINS) != 0) {
            goodSetToPay.addGoodSet(towerCost);
        }

        if (cardCost.isWithPrice()) {

            goodSetToPay.addGoodSet(possibleCardCosts.get(costChosen).getCost());

        }

        goodSetToPay.setNegative();

        try {
            player.updateGoodSet(goodSetToPay);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * This method removes the Card from the chosen floor and gives this card to
     * the player. From the colour of the card the method chooses where to add
     * the card (TerritoryLane, FamilyLane, BuildingLane, VenturesLane).
     */
    private void giveCard() {

        DevelopmentCard card = towerChosen.getFloor(floorIndex).removeCard();
        PersonalBoard playerBoard = player.getPersonalBoard();
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

        CardColor cardColor = card.getColor();

        gameStatus.updateTowerGUI(cardColor);
        player.updatePersonalBoardGUI(card.getSpecial(), cardColor);

    }


    /**
     * This method activates all the immediate effects of the selected card
     */
    private void activateCardEffects() throws Exception {

        List<Effect> immediateEffects = this.cardSelected.getImmediateEffect();

        for (Effect immediateEffect : immediateEffects) {

            immediateEffect.execute(player); // la execute dell'actionEffect salva l'effetto nella lista di azioni bonus per il player
        }
    }


    /**
     * This method update:
     * sets availability false for the pawn used (super)
     * sets the actionSpace as occupied (super)
     * sets permanent effects of the card in player
     * updates number of card per colour in player
     * ...
     */
    protected void update() {
        
        //TODO: dividi in più metodi

        super.update();

        towerChosen.setOccupied(true);

        for (Effect effect : cardSelected.getPermanentEffect()) {
            if (effect instanceof BonusEffect) {
                BonusEffect effect1 = (BonusEffect)effect;
                /*
                player.getBonusAndMalusOnAction().add(effect1.getBonusAndMalusOnAction());
                player.getBonusAndMalusOnCost().add(effect1.getBonusAndMalusOnCost());
                player.getBonusAndMalusOnGoods().add(effect1.getBonusAndMalusOnGoods());
                */

                if (effect1.getBonusAndMalusOnAction() != null) player.getBonusAndMalusOnAction().add(effect1.getBonusAndMalusOnAction());
                if (effect1.getBonusAndMalusOnGoods() != null) player.getBonusAndMalusOnGoods().add(effect1.getBonusAndMalusOnGoods());
                if (effect1.getBonusAndMalusOnCost() != null) player.getBonusAndMalusOnCost().add(effect1.getBonusAndMalusOnCost());
            }
        }

        int numberOfCards = player.getCardsOwned().get(cardSelected.getColor());
        numberOfCards++;
        player.getCardsOwned().put(cardSelected.getColor(), numberOfCards);

    }

    @Override
    public void reset(){
        super.reset();
        this.actionSpaceGoodSet = new GoodSet();
        this.towerCost = new GoodSet();
        this.possibleCardCosts.clear();
    }

    public void setCardCost(CardCost cardCost) {
        this.cardCost = cardCost;
    }

    public void setCardSelected(DevelopmentCard cardSelected) {
        this.cardSelected = cardSelected;
    }

    // metodi per testing


    public Tower getTowerChosen() {
        return towerChosen;
    }

    public int getFloorIndex() {
        return floorIndex;
    }

    public GoodSet getActionSpaceGoodSet() {
        return actionSpaceGoodSet;
    }

    public GoodSet getTowerCost() {
        return towerCost;
    }

    public CardCost getCardCost() {
        return cardCost;
    }

    public Map<Integer,Cost> getPossibleCardCosts() {
        return possibleCardCosts;
    }

    public DevelopmentCard getCardSelected() {
        return cardSelected;
    }

    @Override
    public String toString() {
        return "TowerAction{"
                + super.toString() + ", floorIndex=" + floorIndex
                + cardSelected.getImmediateEffect().toString() + '}';
    }

    public void setCostChosen(int costChosen) {
        this.costChosen = costChosen;
    }
}
