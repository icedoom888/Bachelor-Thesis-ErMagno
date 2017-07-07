package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Controllers.Change.GUIChange;
import it.polimi.ingsw.GC_29.Model.CardColor;

import java.util.List;

/**
 * Created by Lorenzotara on 25/06/17.
 */
public class PersonalCardChange extends GUIChange {

    private String cardName;
    private CardColor cardColor;

    public PersonalCardChange(String special, CardColor cardColor) {

        this.cardName = special;
        this.cardColor = cardColor;

    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }
    }

    public String getCardName() {
        return cardName;
    }

    public CardColor getCardColor() {
        return cardColor;
    }
}
