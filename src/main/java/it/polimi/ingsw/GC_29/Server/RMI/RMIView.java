package it.polimi.ingsw.GC_29.Server.RMI;

import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
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
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Christian on 07/06/2017.
 */
public class RMIView extends View implements RMIViewRemote {


    private ClientViewRemote clientView;

    private GetValidActions validActionQuery;

    private GameStatus gameStatus;

    private PlayerColor playerColor;

    private String username;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());



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
            gameStatus.getPlayer(playerColor).unregisterObserver(this);
            gameStatus.unregisterObserver(this);
            logoutInterface.clientDisconnected(username);
            notifyObserver(new Disconnection(playerColor));


            LOGGER.info((Supplier<String>) e);
        }
    }

    @Override
    public void skipAction() throws RemoteException{

        notifyObserver(new SkipAction());

    }

    @Override
    public void usePawnChosen(FamilyPawnType familyPawnType) throws RemoteException{

        notifyObserver(new UsePawnChosen(familyPawnType));


    }

    @Override
    public void endTurn() throws RemoteException{

        notifyObserver(new EndTurn());

    }

    @Override
    public void pray(boolean b, PlayerColor playerColor) throws RemoteException{

        notifyObserver(new Pray(b, playerColor));

    }

    @Override
    public void initialize(PlayerColor playerColor) throws RemoteException {

        notifyObserver(new Initialize(playerColor));

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

        notifyObserver(new ThrowDices());

    }

    @Override
    public Map<Integer, ArrayList<String>> getCardsForWorkers() throws RemoteException {

        return new GetCardsForWorkers().perform(gameStatus);

    }

    @Override
    public Map<Integer, String> getPossibleCosts() {
        Map<Integer, Cost> possibleCosts = ((TowerAction)gameStatus.getCurrentPlayer().getCurrentAction()).getPossibleCardCosts();
        HashMap<Integer, String> possibleCostsToString = new HashMap<>();

        for(Map.Entry<Integer,Cost> entry : possibleCosts.entrySet()){

            String cost = entry.getValue().toString();
            possibleCostsToString.put(entry.getKey(), cost);
        }

        return possibleCostsToString;

    }

    @Override
    public Map<Integer,String> getBonusTileList() throws RemoteException {
        return new GetBonusTile().perform(gameStatus);
    }

    @Override
    public void bonusTileChosen(int bonusTileChosen) throws RemoteException {

        notifyObserver(new BonusTileChosen(bonusTileChosen));

    }

    @Override
    public Map<FamilyPawn, Boolean> getFamilyPawns() throws RemoteException {
        return new GetFamilyPawnAvailability().perform(gameStatus);
    }

    @Override
    public void joinGame(PlayerColor playerColor) throws RemoteException {

        notifyObserver(new JoinGame(playerColor));

    }

    @Override
    public void leaderAction(boolean b, int index, PlayerColor playerColor) throws RemoteException{

        notifyObserver(new LeaderAction(b, index, playerColor));

    }

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

        notifyObserver(new UseLeaderCardsGUI(playerColor));

    }

    @Override
    public void endGame() throws RemoteException {
        logoutInterface.getClientMatch().remove(username);

        notifyObserver(new Closed());

    }

    @Override
    public Map<String, HashMap<Integer, String>> getPayToObtainCardsGUI() throws RemoteException {
        return new GetPayToObtainCards().perform(gameStatus);
    }

    @Override
    public void activateCards(int workersChosen) throws RemoteException {

        notifyObserver(new ActivateCards(workersChosen));
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

        notifyObserver(new PayToObtainCardsChosen(activatedCardMap));

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

        notifyObserver(new PrivilegeChosen(councilPrivilegeEffectChosenList));

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

        notifyObserver(new PayCard(costChosen));

    }


    @Override
    public Map<Integer, String> getValidActionList() throws RemoteException {

        return validActionQuery.perform(gameStatus);
    }

    @Override
    public void doAction(int index) throws RemoteException {

        notifyObserver(new ExecuteAction(index));

    }

    @Override
    public Map<FamilyPawnType, Boolean> getFamilyPawnAvailability() throws RemoteException{
        return gameStatus.getCurrentPlayer().getFamilyPawnAvailability();
    }


    @Override
    public void update() {

    }
}
