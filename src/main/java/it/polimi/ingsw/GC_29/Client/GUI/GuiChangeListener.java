package it.polimi.ingsw.GC_29.Client.GUI;


import it.polimi.ingsw.GC_29.Controllers.*;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public interface GuiChangeListener {

    void onReadingChange(StartGameChange startGameChange);
    void onReadingChange(ValidActionsChange validActionsChange);
    void onReadingChange(GoodSetChange goodSetChange);
    void onReadingChange(FamilyPawnChange familyPawnChange);
    void onReadingChange(CardsForWorkersChange cardsForWorkersChange);
    void onReadingChange(PayToObtainCardsChange payToObtainCardsChange);
    void onReadingChange(PossibleCostsChange possibleCostsChange);
    void onReadingChange(CouncilPrivilegeChange councilPrivilegeChange);
    void onReadingChange(BonusTileChange bonusTileChange);
    void onReadingChange(PlayerStateChange playerStateChange);
    void onReadingChange(TowerCardsChange towerCardsChange);
    void onReadingChange(PersonalCardChange personalCardChange);
    void onReadingChange(TrackChange trackChange);
    void onReadingChange(AddPawnChange addPawnChange);
}
