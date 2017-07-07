package it.polimi.ingsw.GC_29.Controllers.Change;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 02/07/17.
 */
public class ExcommunicationChange extends GUIChange {

    private ArrayList<String> excommunicationTiles;

    public ExcommunicationChange(String tileFirstEra, String tileSecondEra, String tileThirdEra) {

        this.excommunicationTiles = new ArrayList<>();
        excommunicationTiles.add(tileFirstEra);
        excommunicationTiles.add(tileSecondEra);
        excommunicationTiles.add(tileThirdEra);
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }
    }

    public ArrayList<String> getExcommunicationTiles() {
        return excommunicationTiles;
    }
}
