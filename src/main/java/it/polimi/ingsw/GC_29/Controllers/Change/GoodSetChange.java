package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Model.GoodSet;

import java.util.List;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class GoodSetChange extends GUIChange {

    private GoodSet goodSet;

    public GoodSetChange(GoodSet goodSet) {

        this.goodSet = goodSet;
    }

    public GoodSet getGoodSet() {
        return goodSet;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }
    }

}
