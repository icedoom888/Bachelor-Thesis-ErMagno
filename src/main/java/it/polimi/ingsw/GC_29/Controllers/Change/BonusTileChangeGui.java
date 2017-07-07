package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;

import java.util.List;

/**
 * Created by Lorenzotara on 04/07/17.
 */
public class BonusTileChangeGui extends GUIChange {

    Integer bonusTile;

    public BonusTileChangeGui(int bonusTile) {
        this.bonusTile = bonusTile;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        for (GuiChangeListener listener : listeners) {
            listener.updateBonusTileFromDisconnection(bonusTile);
        }
    }
}
