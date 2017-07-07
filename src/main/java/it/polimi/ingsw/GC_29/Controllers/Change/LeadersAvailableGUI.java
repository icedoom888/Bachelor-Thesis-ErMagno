package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Controllers.Change.GUIChange;

import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 04/07/17.
 */
public class LeadersAvailableGUI extends GUIChange {
    private final Map<Integer, Boolean> leadersAvailable;

    public LeadersAvailableGUI(Map<Integer, Boolean> leadersAvailable) {
        this.leadersAvailable = leadersAvailable;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        for (GuiChangeListener listener : listeners) {
            listener.sendLeaderCards(leadersAvailable);
        }
    }

    public Map<Integer, Boolean> getLeadersAvailable() {
        return leadersAvailable;
    }
}
