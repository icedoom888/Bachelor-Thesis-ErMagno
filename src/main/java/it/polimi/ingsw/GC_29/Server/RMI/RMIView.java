package it.polimi.ingsw.GC_29.Server.RMI;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientViewRemote;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Query.*;
import it.polimi.ingsw.GC_29.Controllers.LeaderAction;
import it.polimi.ingsw.GC_29.Server.View;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Christian on 07/06/2017.
 */
public class RMIView extends View implements RMIViewRemote {


    private ClientViewRemote clientView;

    private GetValidActions validActionQuery;

    private GameStatus gameStatus;

    private PlayerColor playerColor;

    private String username;


    public RMIView(GameStatus gameStatus, PlayerColor playerColor, String username){

        this.gameStatus = gameStatus;

        this.playerColor = playerColor;

        this.username = username;

        this.validActionQuery = new GetValidActions();
    }


    @Override
    public void registerClient(ClientViewRemote clientStub) throws RemoteException {
        System.out.println("CLIENT REGISTERED");
        clientView = clientStub;
    }

    @Override
    public void update(Change o)  {
        try {
            clientView.updateClient(o);

        } catch (RemoteException e) {
            //e.printStackTrace();
            gameStatus.getPlayer(playerColor).unregisterObserver(this);
            gameStatus.unregisterObserver(this);
            logoutInterface.clientDisconnected(username);
            System.out.println("CLIENT DISCONNECTED. CLIENT SUSPENDED");
            //gameStatus.getPlayer(playerColor).setPlayerState(PlayerState.SUSPENDED);
            try {
                notifyObserver(new Disconnection(playerColor));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void skipAction() throws RemoteException{
        try {
            notifyObserver(new SkipAction());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usePawnChosen(FamilyPawnType familyPawnType) throws RemoteException{
        try {
            notifyObserver(new UsePawnChosen(familyPawnType));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endTurn() throws RemoteException{
        try {
            notifyObserver(new EndTurn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pray(boolean b, PlayerColor playerColor) throws RemoteException{
        try {
            notifyObserver(new Pray(b, playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(PlayerColor playerColor) throws RemoteException {
        try {
            notifyObserver(new Initialize(playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getDevelopmentCard(CardColor cardColor) throws RemoteException {

        List<String> returnList = new ArrayList<>();

        List<DevelopmentCard> playerCards = Arrays.asList(gameStatus.getCurrentPlayer().getPersonalBoard().getLane(cardColor).getCards());

        for (DevelopmentCard playerCard : playerCards) {

            if(playerCard!= null){
                returnList.add(playerCard.toString());
            }

        }

        return returnList;
    }

    @Override
    public List<String> getTowerCards(CardColor towerCardColor) throws RemoteException{

        List<String> returnList = new ArrayList<>();

        List<Floor> floors = Arrays.asList(gameStatus.getGameBoard().getTower(towerCardColor).getFloors());

        for (Floor floor : floors) {

            returnList.add( "Floor " + floors.indexOf(floor) + "\n" + floor.getDevelopmentCard().toString());
        }

        return returnList;
    }

    @Override
    public void throwDices() throws RemoteException {
        try {
            notifyObserver(new ThrowDices());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, ArrayList<String>> getCardsForWorkers() throws RemoteException {

        Map<Integer, ArrayList<DevelopmentCard>> cardsForWorkersMap = ((WorkAction)gameStatus.getCurrentPlayer().getCurrentAction()).getCardsForWorkers();

        Map<Integer, ArrayList<String>> cardMap = new HashMap<>();

        for(Integer workersIndex : cardsForWorkersMap.keySet()){

            ArrayList<DevelopmentCard> cards = cardsForWorkersMap.get(workersIndex);

            cardMap.put(workersIndex, new ArrayList<>());

            for (DevelopmentCard card : cards) {

                cardMap.get(workersIndex).add(card.toString());
            }
        }

        return cardMap;

    }

    @Override
    public Map<Integer, String> getPossibleCosts() {
        Map<Integer, Cost> possibleCosts = ((TowerAction)gameStatus.getCurrentPlayer().getCurrentAction()).getPossibleCardCosts();
        HashMap<Integer, String> possibleCostsToString = new HashMap<>();

        for (Integer integer : possibleCosts.keySet()) {
            String cost = possibleCosts.get(integer).toString();
            possibleCostsToString.put(integer, cost);
        }

        return possibleCostsToString;

    }

    @Override
    public Map<Integer,String> getBonusTileList() throws RemoteException {
        return new GetBonusTile().perform(gameStatus);
    }

    @Override
    public void bonusTileChosen(int bonusTileChosen) throws RemoteException {
        try {
            notifyObserver(new BonusTileChosen(bonusTileChosen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<FamilyPawn, Boolean> getFamilyPawns() throws RemoteException {
        return new GetFamilyPawnAvailability().perform(gameStatus);
    }

    @Override
    public void joinGame(PlayerColor playerColor) throws RemoteException {
        try {
            notifyObserver(new JoinGame(playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaderAction(boolean b, int index, PlayerColor playerColor) throws RemoteException{
        try {
            notifyObserver(new LeaderAction(b, index, playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void privilegeLeader(List<Integer> councilPrivilegeEffectChosenList, PlayerColor playerColor) throws RemoteException{
        try {
            notifyObserver(new PrivilegeChosenLeader(councilPrivilegeEffectChosenList, playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public Map<Integer, Boolean> getLeaderCardsMap(PlayerColor playerColor) throws RemoteException {
        return new GetAvailableLeaderCards(playerColor).perform(gameStatus);
    }

    @Override
    public List<String> getLeaderCards(PlayerColor playerColor) throws RemoteException {
        return new LeaderCardsQuery(playerColor).perform(gameStatus);
    }

    @Override
    public void useLeaderCardGui(PlayerColor playerColor) throws RemoteException {
        try {
            notifyObserver(new UseLeaderCardsGUI(playerColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endGame() throws RemoteException {
        logoutInterface.getClientMatch().remove(username);

        try {
            notifyObserver(new Closed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, HashMap<Integer, String>> getPayToObtainCardsGUI() throws RemoteException {
        return new GetPayToObtainCards().perform(gameStatus);
    }

    @Override
    public void activateCards(int workersChosen) throws RemoteException {
        try {
            notifyObserver(new ActivateCards(workersChosen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, HashMap<Integer, String>> getPayToObtainCards() throws RemoteException {

        WorkAction workAction = (WorkAction)gameStatus.getCurrentPlayer().getCurrentAction();

        Map<String, HashMap<Integer, String>> payToObtainCardMap = new HashMap<>();

        for(DevelopmentCard card : workAction.getPayToObtainCardsMap().values()){

            payToObtainCardMap.put(card.toString(), new HashMap<>());

            for(Effect effect : card.getPermanentEffect()){

                if(effect instanceof PayToObtainEffect){

                    PayToObtainEffect effect1 = (PayToObtainEffect) effect;

                    if(effect1.checkSufficientGoods(gameStatus.getCurrentPlayer())){

                        //System.out.println("LA CARTA AGGIIUNTA NELLA PAY TO OBTAIN MAP E' " + cardKey);
                        int effectIndex = card.getPermanentEffect().indexOf(effect);

                        payToObtainCardMap.get(card.toString()).put(effectIndex, effect.toString());

                    }

                }


            }
        }

        return payToObtainCardMap;
    }

    @Override
    public void payToObtainCardChosen(Map<String, Integer> activatedCardMap) throws RemoteException {

        try {
            notifyObserver(new PayToObtainCardsChosen(activatedCardMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> getCouncilPrivileges() throws RemoteException {

        List<Integer> parchmentAmountList = new ArrayList<>();

        for (CouncilPrivilegeEffect councilPrivilegeEffect : gameStatus.getCurrentPlayer().getCouncilPrivilegeEffectList()) {

            parchmentAmountList.add(councilPrivilegeEffect.getNumberOfCouncilPrivileges());
        }

        gameStatus.getCurrentPlayer().getCouncilPrivilegeEffectList().clear();

        return parchmentAmountList;

    }

    @Override
    public void privilegesChosen(List<Integer> councilPrivilegeEffectChosenList) throws RemoteException {
        try {
            notifyObserver(new PrivilegeChosen(councilPrivilegeEffectChosenList));
            System.out.println("OBSERVER PRIVILEGI");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodSet getPlayerGoodset() throws RemoteException {

      return gameStatus.getCurrentPlayer().getActualGoodSet();
    }

    @Override
    public List<FamilyPawn> getPlayerPawns() throws RemoteException {

        return gameStatus.getCurrentPlayer().getFamilyPawns();
    }

    @Override
    public void chooseCost(int costChosen) {
        try {
            notifyObserver(new PayCard(costChosen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Map<Integer, String> getValidActionList() throws RemoteException {

        return validActionQuery.perform(gameStatus);
    }

    @Override
    public void doAction(int index) throws RemoteException {

        try {
            notifyObserver(new ExecuteAction(index));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<FamilyPawnType, Boolean> getFamilyPawnAvailability() throws RemoteException{
        return gameStatus.getCurrentPlayer().getFamilyPawnAvailability();
    }


    @Override
    public void update() {

    }
}
