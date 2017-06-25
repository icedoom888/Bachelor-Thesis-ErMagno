package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Components.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 25/06/17.
 */
public class TowerCardsChange extends GUIChange {

    private ArrayList<String> cards;
    private CardColor cardColor;

    public TowerCardsChange(ArrayList<String> cards, CardColor cardColor) {

        this.cards = cards;
        this.cardColor = cardColor;
    }

    @Override
    public void perform(List<GuiChangeListener> guiChangeListeners) {

        for (GuiChangeListener listener : guiChangeListeners) {
            listener.onReadingChange(this);
        }

    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public CardColor getCardColor() {
        return cardColor;
    }
}
