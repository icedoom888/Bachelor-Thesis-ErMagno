package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Model.PlayerColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 08/07/17.
 */
public class PlayerNames extends GUIChange {

    private Map<PlayerColor, String> playerNames;

    public PlayerNames(Map<PlayerColor, String> playerNames) {
        this.playerNames = playerNames;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        for (GuiChangeListener listener : listeners) {
            listener.sendPlayerNames(playerNames);
        }
    }
}
