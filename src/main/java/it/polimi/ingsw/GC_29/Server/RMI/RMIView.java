package it.polimi.ingsw.GC_29.Server.RMI;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientViewRemote;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.WorkAction;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Server.Query.GetValidActions;
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


    public RMIView(GameStatus gameStatus){

        this.gameStatus = gameStatus;

        this.validActionQuery = new GetValidActions();
    }


    @Override
    public void registerClient(ClientViewRemote clientStub) throws RemoteException {
        System.out.println("CLIENT REGISTERED");
        clientView = clientStub;
    }

    @Override
    public void update(Change o) throws RemoteException {
        clientView.updateClient(o);
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

        DevelopmentCard playerCards[] = gameStatus.getCurrentPlayer().getPersonalBoard().getLane(cardColor).getCards();

        for (DevelopmentCard playerCard : playerCards) {

            returnList.add(playerCard.toString());
        }

        return returnList;
    }

    @Override
    public List<String> getTowertCards(CardColor towerCardColor) throws RemoteException{

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

                int effectIndex = card.getPermanentEffect().indexOf(effect);

                payToObtainCardMap.get(card.toString()).put(effectIndex, effect.toString());

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
