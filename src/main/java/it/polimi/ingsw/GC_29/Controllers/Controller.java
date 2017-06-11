package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Filter;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Observer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Christian on 07/06/2017.
 */

/**
 * the controller class is an Observer of Input Objects, it observes the views of server side and when the views call
 * a notifyObserver(Input input) the update(Input input) of the controller is called and it performs the "perform" method
 * of the Input Object
 */
public class Controller implements Observer<Input>  {

    private final GameStatus model;
    private Integer playersPraying;


    public Controller(GameStatus model){
        this.model = model;
        playersPraying = 0;
    }

    public void update(Input input) throws Exception {
        System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
        Observer.super.update(input);
        input.perform(model, this);
    }

    @Override
    public void update() {
        // Auto-generated method stub

    }

    public void handleEndRound() throws Exception {

        int round = model.getCurrentRound();
        if (round%2 == 0) {
            //TODO: messaggio di scomunica - GAMESTATE = RELATIONSHIP
            List<Player> safePlayers = excommunicatePlayers();

            for (Player safePlayer : safePlayers) {
                safePlayer.setPlayerState(PlayerState.PRAY);
            }
        }
        else setNewRound();
    }


    /**
     *
     * @throws Exception
     */
    public void setNewRound() throws Exception {

        DevelopmentCard[] greenDeck = new DevelopmentCard[4];
        DevelopmentCard[] blueDeck = new DevelopmentCard[4];
        DevelopmentCard[] yellowDeck = new DevelopmentCard[4];
        DevelopmentCard[] purpleDeck = new DevelopmentCard[4];

        setFamilyPawnsValues();
        setNewTurnOrder();

        if (model.getCurrentRound()%2 == 0) {
            Era oldEra = model.getCurrentEra();

            switch (oldEra) {
                case FIRST:
                    model.setCurrentEra(Era.SECOND);
                    break;
                case SECOND:
                    model.setCurrentEra(Era.THIRD);
                    break;
                case THIRD:
                    endGame();
                    break;
            }
        }

        model.setCurrentTurn(1);
        model.setCurrentRound(model.getCurrentRound()+1);

        setSkippingTurnPlayers();

        model.getGameBoard().clearRound();

        for (int i = 0; i < 4; i++) {
            greenDeck[i] = model.getOrderedDecks().get(CardColor.GREEN).pop();
            blueDeck[i] = model.getOrderedDecks().get(CardColor.BLUE).pop();
            yellowDeck[i] = model.getOrderedDecks().get(CardColor.YELLOW).pop();
            purpleDeck[i] = model.getOrderedDecks().get(CardColor.PURPLE).pop();
        }

        model.getGameBoard().setTurn(greenDeck, blueDeck, yellowDeck, purpleDeck);

        model.setCurrentPlayer(model.getTurnOrder().get(0));
        //TODO: lancio dei dadi
        model.getCurrentPlayer().setPlayerState(PlayerState.DOACTION);
    }

    /**
     * setSkippingTurnPlayers finds all the players with the malus "Skip Turn" and
     * saves them in SkippedTurnPlayers
     */
    private void setSkippingTurnPlayers() {
        List<Player> players = model.getTurnOrder();

        for (Player player : players) {
            //TODO: fare bonus and malus special di turno skippato
            if (true) {
                model.getSkippedTurnPlayers().add(player);
            }
        }
    }

    /**
     * excommunicatePlayers finds the players who don't have sufficient FaithPoints and calls executeTiles on them.
     * @return a List of players that have sufficient faithPoints
     *
     */
    private List<Player> excommunicatePlayers() throws Exception {

        ArrayList<Player> safePlayers = new ArrayList<>();

        int threshold = setThreshold();

        int faithPointsNeeded = model.getGameBoard().getFaithPointsTrack().getVictoryPointsPerSlot()[threshold];

        List<Player> players = model.getTurnOrder();
        int[] victoryPoints = model.getGameBoard().getFaithPointsTrack().getVictoryPointsPerSlot();

        for (int i = 0; i < threshold; i++) {

            for (Player player : players) {

                if (player.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS) < faithPointsNeeded) {
                    player.updateGoodSet(new GoodSet(0,0,0,0,victoryPoints[i], 0, -player.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS)));
                    executeTiles(player);
                }

                else {
                    safePlayers.add(player);
                }
            }
        }

        return safePlayers;

    }


    /**
     * it's the method that assigns the excommunication Tiles to the player excommunicated
     * @param player
     * @throws RemoteException
     */
    public void executeTiles(Player player) throws Exception {

        Era currentEra = model.getCurrentEra();
        ExcommunicationTile tileToExecute = model.getGameBoard().getExcommunicationLane().getExcommunicationTile(currentEra);
        tileToExecute.execute(player);
        player.setPlayerState(PlayerState.WAITING);
    }


    /**
     * setThreshold finds the right threshold for the FaithPointsTrack of the currentEra.
     * @return an integer - the threshold
     */
    private int setThreshold() {

        int threshold=0;
        Era currentEra = model.getCurrentEra();
        switch (currentEra) {
            case FIRST:
                threshold = 3;
                break;
            case SECOND:
                threshold = 4;
                break;
            case THIRD:
                threshold = 5;
                break;
        }

    return threshold;

    }

    public synchronized Integer getPlayersPraying() {
        return playersPraying;
    }

    public synchronized void praying() {
        if (playersPraying > 0) {
            this.playersPraying--;
        }
    }

    /**
     * endGame calculates the victoryPoints of all the players and chooses the winner
     */
    public void endGame() throws Exception {

        List<Player> players = model.getTurnOrder();
        Player winner = null;
        int winningPoints = 0;

        pointsFromMilitaryPoints();

        for (Player player : players) {

            //PURPLE CARDS

            pointsFromPurpleCards(player);

            //BLUE CARDS

            pointsFromBlueCards(player);

            //GREEN CARDS

            pointsFromGreenCards(player);

            transformResourcesInPoints(player);


            int playerPoints = player.getActualGoodSet().getGoodAmount(GoodType.VICTORYPOINTS);

            if (playerPoints > winningPoints) {
                winningPoints = playerPoints;
                winner = player;
            }
        }

        //TODO: GAMESTATE = ENDED

        System.out.println("The winner is... " + winner);

        model.getGameBoard().clearAll();

    }

    private void transformResourcesInPoints(Player player) throws Exception {
        int totalResource = 0;

        GoodSet playerGoodSet = player.getActualGoodSet();

        for (GoodType goodType : GoodType.values()) {
            if (goodType != GoodType.VICTORYPOINTS && goodType != GoodType.MILITARYPOINTS && goodType != GoodType.FAITHPOINTS) {

                totalResource += playerGoodSet.getGoodAmount(goodType);
            }
        }

        player.updateGoodSet(new GoodSet(0,0,0,0,totalResource/5,0,0));


    }

    private void pointsFromMilitaryPoints() throws Exception {
        ArrayList<Player> players = new ArrayList<>();

        players.addAll(model.getTurnOrder());

        Collections.sort(players, new Comparator<Player>() { // descending order
            @Override
            public int compare(Player player2, Player player1) {
                return ((Integer)player1.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS)).compareTo((Integer)player2.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS));
            }
        });

        int firstPlayerMilitaryPoints = players.get(0).getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS);

        int i = 0;

        for (i = 0; i < players.size(); i++) {
            Player player = players.get(i+1);

            if (player.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS) != firstPlayerMilitaryPoints) {
                break;
            }

            else player.updateGoodSet(new GoodSet(0,0,0,0,5,0,0));
        }

        if (i != 0) return;

        int secondPlayerMilitaryPoints = players.get(1).getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS);

        for (i = 1; i < players.size(); i++) {

            Player player = players.get(i+1);

            if (player.getActualGoodSet().getGoodAmount(GoodType.MILITARYPOINTS) != secondPlayerMilitaryPoints) {
                break;
            }

            else player.updateGoodSet(new GoodSet(0,0,0,0,2,0,0));
        }

    }

    private void pointsFromPurpleCards(Player player) throws Exception {

        if (!Filter.applySpecial(player, CardColor.PURPLE)) {
            DevelopmentCard[] cards =  player.getPersonalBoard().getLane(CardColor.PURPLE).getCards();

            for (DevelopmentCard card : cards) {

                for (Effect effect : card.getPermanentEffect()) {
                    effect.execute(player);
                }
            }
        }
    }


    /**
     * This method calculates how many points the player receives from the number of his blue cards
     * @param player
     */
    private void pointsFromBlueCards(Player player) throws Exception {

        int numberOfBlueCards = player.getCardsOwned().get(CardColor.BLUE);

        switch (numberOfBlueCards) {

            case 1:
                player.updateGoodSet(new GoodSet(0,0,0,0,1,0,0));
                break;
            case 2:
                player.updateGoodSet(new GoodSet(0,0,0,0,3,0,0));
                break;
            case 3:
                player.updateGoodSet(new GoodSet(0,0,0,0,6,0,0));
                break;
            case 4:
                player.updateGoodSet(new GoodSet(0,0,0,0,10,0,0));
                break;
            case 5:
                player.updateGoodSet(new GoodSet(0,0,0,0,15,0,0));
                break;
            case 6:
                player.updateGoodSet(new GoodSet(0,0,0,0,21,0,0));
                break;
            default:
                break;
        }
    }


    /**
     * This method calculates how many points the player receives from the number of his green cards
     * @param player
     */
    private void pointsFromGreenCards(Player player) throws Exception {
        int numberOfGreenCards = player.getCardsOwned().get(CardColor.GREEN);
        player.updateGoodSet(new GoodSet(0,0,0,0,player.getPersonalBoard().getTerritoryLane().getSlot(numberOfGreenCards-1).getVictoryPointsGiven(),0,0));
    }




    /**
     * this method set all the availabilities of the family pawns to true and give them the right action value
     * @throws Exception
     */
    private void setFamilyPawnsValues() throws Exception {

        for (Player player : model.getTurnOrder()) {

            Dice tempDice;

            for (FamilyPawnType familyPawnType : FamilyPawnType.values()){

                if (familyPawnType != FamilyPawnType.BONUS)  {
                    player.getFamilyPawnAvailability().put(familyPawnType, true);

                    if (familyPawnType != FamilyPawnType.NEUTRAL) {
                        tempDice = model.getGameBoard().getDice(familyPawnType);
                        player.setFamilyPawnValue(familyPawnType, tempDice.getFace());
                    }

                    else player.setFamilyPawnValue(familyPawnType, 1); // neutral case

                }
            }
        }
    }


    /**
     * newTurnOrder is the turnOrderTrack from the councilPalace. This track contains the first players, but it is
     * not sure that contains them all. In case some players didn't go to the palace, they will follow the same order,
     * relatively, that they had in the previous turn. For this reason setNewTurnOrder copies all the player of the
     * oldTurnOrder following the order of the color in newTurnOrder in a temporary arrayList. While doing this process,
     * the method saves all the indices of the oldTurnOrder that point to the players that have already been copied.
     * After this first step, all the players of the oldTurnOrder are added to the temporary arrayList, skipping the
     * ones who have already been copied. Then the TurnOrder in the GameStatus is set.
     */
    private void setNewTurnOrder() {
        PlayerColor[] newTurnOrder = model.getGameBoard().getCouncilPalace().getTurnOrder();
        List<Player> oldTurnOrder = model.getTurnOrder();
        ArrayList<Player> temporaryTurnOrder = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        for (PlayerColor playerColor : newTurnOrder) {

            for (Player player : oldTurnOrder) {

                if (player.getPlayerColor() == playerColor) {
                    temporaryTurnOrder.add(player);
                    indices.add(oldTurnOrder.indexOf(player));
                }
            }
        }

        for (int i = 0; i < oldTurnOrder.size(); i++) {
            if (!indices.contains(i)) temporaryTurnOrder.add(oldTurnOrder.get(i));
        }

        model.setTurnOrder(temporaryTurnOrder);

    }

    public Player searchPlayer(PlayerColor playerColor) {
        List<Player> turnOrder = model.getTurnOrder();

        for (Player player : turnOrder) {
            if (player.getPlayerColor() == playerColor) return player;
        }

        return null;
    }



}
