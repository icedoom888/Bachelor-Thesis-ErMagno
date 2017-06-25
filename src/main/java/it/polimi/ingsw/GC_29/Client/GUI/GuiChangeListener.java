package it.polimi.ingsw.GC_29.Client.GUI;


/**
 * Created by Lorenzotara on 24/06/17.
 */
public interface GuiChangeListener {

    void onReadingChange(StartGameChange startGameChange);
    void onReadingChange(ValidActionsChange validActionsChange);
    void onReadingChange(GoodSetChange goodSetChange);
    void onReadingChange(CardsChange cardsChange);
    void onReadingChange(FamilyPawnChange familyPawnChange);
    void onReadingChange(CardsForWorkersChange cardsForWorkersChange);
    void onReadingChange(PayToObtainCardsChange payToObtainCardsChange);
    void onReadingChange(PossibleCostsChange possibleCostsChange);
    void onReadingChange(CouncilPrivilegeChange councilPrivilegeChange);
    void onReadingChange(BonusTileChange bonusTileChange);
}