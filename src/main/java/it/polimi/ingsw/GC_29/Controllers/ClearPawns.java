package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;

import java.util.List;

/**
 * Created by Lorenzotara on 28/06/17.
 */
public class ClearPawns extends GUIChange {
    @Override
    public void perform(List<GuiChangeListener> listeners) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }
    }
}
