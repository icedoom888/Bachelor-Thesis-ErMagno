package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;

import java.util.List;

/**
 * Created by Lorenzotara on 02/07/17.
 */
public class ActivateLeader extends GUIChange {

    boolean isPossible;
    boolean permanent;

    public ActivateLeader(Boolean isPossible, boolean permanent) {
        this.isPossible = isPossible;
        this.permanent = permanent;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        /*for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }*/
    }

    public boolean isPossible() {
        return isPossible;
    }

    public boolean isPermanent() {
        return permanent;
    }
}
