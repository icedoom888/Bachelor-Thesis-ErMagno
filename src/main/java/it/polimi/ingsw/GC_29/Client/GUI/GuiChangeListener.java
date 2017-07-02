package it.polimi.ingsw.GC_29.Client.GUI;


import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Controllers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public interface GuiChangeListener {

    //void onReadingChange(StartGameChange startGameChange);
    void onReadingChange(GoodSetChange goodSetChange);
    void onReadingChange(TowerCardsChange towerCardsChange);
    void onReadingChange(PersonalCardChange personalCardChange);
    void onReadingChange(TrackChange trackChange);
    void onReadingChange(AddPawnChange addPawnChange);
    void onReadingChange(ClearPawns clearPawns);
    void onReadingChange(ExcommunicationChange excommunicationChange);

    void pray(String excommunicationUrl);

    void changeState(PlayerState currentPlayerState);

    void validActions(Map<Integer, String> validActionList);

    void updatePawns(Map<FamilyPawn, Boolean> familyPawns);

    void cardsForWorkers(Map<Integer, ArrayList<String>> cardsForWorkers);

    void payToObtainCard(Map<String, HashMap<Integer,String>> payToObtainCard);

    void possibleCosts(Map<Integer, String> possibleCosts);

    void councilPrivilege(List<Integer> councilPrivileges);

    void bonusTile(Map<Integer, String> bonusTiles);


}
