package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Observer;

import java.rmi.RemoteException;
import java.util.*;

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
    private ActionChecker actionChecker;


    public Controller(GameStatus model){
        this.model = model;
        playersPraying = 0;
        actionChecker = new ActionChecker(model);

        setCardsOnTowers();

        createActions();
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
            model.setGameState(GameState.CHURCHRELATION);
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

        setFamilyPawnsAndLeaderValues();
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
                    return;

            }
        }

        model.setCurrentTurn(0);
        model.setCurrentRound(model.getCurrentRound()+1);

        setSkippingTurnPlayers();

        model.getGameBoard().clearRound();

        setCardsOnTowers();

        model.getTurnOrder().get(0).setPlayerState(PlayerState.THROWDICES);

        //TODO: al posto che queste ultime righe usare chooseCurrentPlayer(0), ma si creano problemi.
        /*List<Player> turnOrder = model.getTurnOrder();

        model.setCurrentPlayer(turnOrder.get(0));
        model.getCurrentPlayer().setPlayerState(PlayerState.DOACTION);*/
    }

    private void setCardsOnTowers(){

        DevelopmentCard[] greenDeck = new DevelopmentCard[4];
        DevelopmentCard[] blueDeck = new DevelopmentCard[4];
        DevelopmentCard[] yellowDeck = new DevelopmentCard[4];
        DevelopmentCard[] purpleDeck = new DevelopmentCard[4];

        for (int i = 0; i < 4; i++) {
            greenDeck[i] = model.getOrderedDecks().get(CardColor.GREEN).pop();
            blueDeck[i] = model.getOrderedDecks().get(CardColor.BLUE).pop();
            yellowDeck[i] = model.getOrderedDecks().get(CardColor.YELLOW).pop();
            purpleDeck[i] = model.getOrderedDecks().get(CardColor.PURPLE).pop();
        }

        model.getGameBoard().setTurn(greenDeck, blueDeck, yellowDeck, purpleDeck);

    }

    private void setLeaderValues(Player player) {

        HashMap<LeaderCard, Boolean> playerLeaderCards = player.getOncePerRoundLeaders();

        if(!playerLeaderCards.isEmpty()){
            for (LeaderCard leaderCard : playerLeaderCards.keySet()) {
                playerLeaderCards.put(leaderCard, true);
            }
        }

    }

    public void chooseCurrentPlayer(Integer index) throws Exception {
        Player firstPlayer = model.getTurnOrder().get(index);

        model.setCurrentPlayer(firstPlayer);

        if (Filter.applySpecial(firstPlayer, SpecialBonusAndMalus.SKIPFIRSTTURN)) {
            firstPlayer.setPlayerState(PlayerState.ENDTURN);
        }


        else
        {
            actionChecker.resetActionList();

            actionChecker.setCurrentPlayer();

            firstPlayer.setPlayerState(PlayerState.DOACTION);
        }
    }

    /**
     * setSkippingTurnPlayers finds all the players with the malus "Skip Turn" and
     * saves them in SkippedTurnPlayers
     */
    private void setSkippingTurnPlayers() {
        List<Player> players = model.getTurnOrder();

        for (Player player : players) {
            if (Filter.applySpecial(player, SpecialBonusAndMalus.SKIPFIRSTTURN)) {
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

        for (int i = 0; i < threshold; i++) {

            for (Player player : players) {

                if (player.getActualGoodSet().getGoodAmount(GoodType.FAITHPOINTS) < faithPointsNeeded) {
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

        model.setGameState(GameState.ENDED);

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

        if (!Filter.applySpecial(player, SpecialBonusAndMalus.NOVICTORYFROMPURPLE)) {
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

        if (!Filter.applySpecial(player, SpecialBonusAndMalus.NOVICTORYFROMBLUE)) {
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
    }


    /**
     * This method calculates how many points the player receives from the number of his green cards
     * @param player
     */
    private void pointsFromGreenCards(Player player) throws Exception {
        if (!Filter.applySpecial(player, SpecialBonusAndMalus.NOVICTORYFROMGREEN)) {
            int numberOfGreenCards = player.getCardsOwned().get(CardColor.GREEN);
            player.updateGoodSet(new GoodSet(0,0,0,0,player.getPersonalBoard().getTerritoryLane().getSlot(numberOfGreenCards-1).getVictoryPointsGiven(),0,0));
        }
    }




    /**
     * this method set all the availabilities of the family pawns to true and give them the right action value
     * @throws Exception
     */
    public void setFamilyPawnsAndLeaderValues() throws Exception {

        for (Player player : model.getTurnOrder()) {

            if (Filter.applySpecial(player, SpecialBonusAndMalus.CHANGEVALUEOFEVERYPAWN)) {
                player.getFamilyPawnAvailability().put(FamilyPawnType.BLACK, true);
                player.getFamilyPawnAvailability().put(FamilyPawnType.ORANGE, true);
                player.getFamilyPawnAvailability().put(FamilyPawnType.WHITE, true);
                player.getFamilyPawnAvailability().put(FamilyPawnType.NEUTRAL, true);

                player.setFamilyPawnValue(FamilyPawnType.BLACK, 5);
                player.setFamilyPawnValue(FamilyPawnType.ORANGE, 5);
                player.setFamilyPawnValue(FamilyPawnType.WHITE, 5);
                player.setFamilyPawnValue(FamilyPawnType.NEUTRAL, 0);
            }



            else {

                Dice tempDice;

                for (FamilyPawnType familyPawnType : FamilyPawnType.values()){

                    if (familyPawnType != FamilyPawnType.BONUS && familyPawnType != FamilyPawnType.ANY)  {
                        player.getFamilyPawnAvailability().put(familyPawnType, true);

                        if (familyPawnType != FamilyPawnType.NEUTRAL) {
                            tempDice = model.getGameBoard().getDice(familyPawnType);
                            player.setFamilyPawnValue(familyPawnType, tempDice.getFace());
                        }

                        else player.setFamilyPawnValue(familyPawnType, 0); // neutral case

                    }
                }
            }

            setLeaderValues(player);
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


    public ActionChecker getActionChecker() {
        return actionChecker;
    }

    private void createActions() {

        ArrayList<Action> actionList = new ArrayList<>();

        final int NUMBER_OF_FLOORS = 4;

        for(ZoneType zoneType : ZoneType.values()){

            if(zoneType == ZoneType.GREENTOWER || zoneType == ZoneType.YELLOWTOWER || zoneType == ZoneType.BLUETOWER || zoneType == ZoneType.PURPLETOWER){

                for (int i = 0; i < NUMBER_OF_FLOORS; i++){
                    actionList.add(new TowerAction(zoneType, model, i));
                }

            } else if(zoneType == ZoneType.MARKET) {

                for (ShopName shopName : ShopName.values()) {
                    MarketAction marketAction = new MarketAction(shopName, model);

                    if ((shopName == ShopName.MILITARYANDCOINSSHOP || shopName == ShopName.PRIVILEGESHOP) && model.getTurnOrder().size() < 4) {
                        marketAction.setEnable(false);
                    }

                    actionList.add(marketAction);
                }

            } else if (zoneType == ZoneType.COUNCILPALACE) {
                actionList.add(new CouncilPalaceAction(model));

            } else if (zoneType == ZoneType.HARVEST) {

                //TODO: impl

            } else if (zoneType == ZoneType.PRODUCTION) {

                //TODO: impl

            }

        }

        actionChecker.setActionList(actionList);
    }

    public void handleEndAction(){

        if(!model.getCurrentPlayer().getCouncilPrivilegeEffectList().isEmpty()){

            model.getCurrentPlayer().setPlayerState(PlayerState.CHOOSE_COUNCIL_PRIVILEGE);
            return;
        }

        if (!model.getCurrentPlayer().getCurrentBonusActionList().isEmpty()) {

            ActionEffect currentBonusAction = model.getCurrentPlayer().getCurrentBonusActionList().removeFirst();

            // temporary bonusMalusOn cost setted in the player
            if (currentBonusAction.getBonusAndMalusOnCost() != null) {

                model.getCurrentPlayer().getCurrentBonusActionBonusMalusOnCostList().add(currentBonusAction.getBonusAndMalusOnCost());

            }


            actionChecker.resetActionListExceptPlayer();

            FamilyPawn familyPawn = new FamilyPawn(model.getCurrentPlayer().getPlayerColor(), FamilyPawnType.BONUS, currentBonusAction.getActionValue());

            actionChecker.setValidActionForFamilyPawn(familyPawn, currentBonusAction.getType());

            model.getCurrentPlayer().setPlayerState(PlayerState.BONUSACTION);

        } else {

            // TODO: qui ci va la logica (chiamando opportuni metodi di questa classe) del GameController sulla gestione di fine giro

            model.getCurrentPlayer().setPlayerState(PlayerState.ENDTURN);

        }
    }
}
