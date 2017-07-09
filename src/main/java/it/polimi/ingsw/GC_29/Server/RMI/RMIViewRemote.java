package it.polimi.ingsw.GC_29.Server.RMI;


import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientViewRemote;
import it.polimi.ingsw.GC_29.Model.*;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 07/06/2017.
 */

/**
 * this interface declares all the method that the client RMI view calls from the serverStub
 * on client-side
 */

public interface RMIViewRemote extends Remote {

    void registerClient(
            ClientViewRemote clientStub)
            throws RemoteException;

    void skipAction() throws RemoteException;


    void usePawnChosen(FamilyPawnType familyPawnType) throws RemoteException;


    /**
     * da CLI basterebbe avere metodo printValidActionList (come nell'esempio printModel, lo stub printa direttamente
     * @return
     * @throws RemoteException
     */
    Map<Integer, String> getValidActionList() throws RemoteException;

    void doAction(int index) throws RemoteException;

    Map<FamilyPawnType,Boolean> getFamilyPawnAvailability() throws RemoteException;


    void endTurn() throws RemoteException;

    void pray(boolean b, PlayerColor playerColor) throws RemoteException;

    void initialize(PlayerColor playerColor) throws RemoteException;

    List<String> getDevelopmentCard(CardColor cardColor) throws RemoteException;

    List<String> getTowerCards(CardColor towerCardColor) throws RemoteException;

    void throwDices() throws RemoteException;

    Map<Integer,ArrayList<String>> getCardsForWorkers() throws RemoteException;

    void activateCards(int workersChosen) throws RemoteException;

    Map<String,HashMap<Integer,String>> getPayToObtainCards() throws RemoteException;

    void payToObtainCardChosen(Map<String, Integer> activatedCardMap) throws RemoteException;

    List<Integer> getCouncilPrivileges() throws RemoteException;

    void privilegesChosen(List<Integer> councilPrivilegeEffectChosenList) throws RemoteException;

    GoodSet getPlayerGoodset() throws RemoteException;

    List<FamilyPawn> getPlayerPawns() throws RemoteException;

    void chooseCost(int costChosen) throws RemoteException;

    Map<Integer, String> getPossibleCosts() throws RemoteException;

    Map<Integer,String> getBonusTileList() throws  RemoteException;

    void bonusTileChosen(int bonusTileChosen) throws RemoteException;

    Map<FamilyPawn,Boolean> getFamilyPawns() throws  RemoteException;

    void joinGame(PlayerColor playerCardColor) throws  RemoteException;

    void leaderAction(boolean b, int index, PlayerColor playerColor) throws RemoteException;

    //void privilegeLeader(List<Integer> councilPrivilegeEffectChosenList, PlayerColor playerColor) throws RemoteException;

    Map<Integer,Boolean> getLeaderCardsMap(PlayerColor playerColor) throws RemoteException;

    List<String> getLeaderCards(PlayerColor playerColor) throws RemoteException;

    void useLeaderCardGui(PlayerColor playerColor) throws RemoteException;

    void endGame() throws RemoteException;

    Map<String,HashMap<Integer,String>> getPayToObtainCardsGUI() throws RemoteException;

    String seeGameBoard() throws RemoteException;

    String seePersonalBoard(PlayerColor playerColor) throws RemoteException;
}
