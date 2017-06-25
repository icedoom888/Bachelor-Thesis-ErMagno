package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Components.FamilyPawn;

import java.util.List;

/**
 * Created by Lorenzotara on 25/06/17.
 */
public class AddPawnChange extends GUIChange {

    private FamilyPawn familyPawn;
    private int actionIndex;

    public AddPawnChange(FamilyPawn familyPawn, int actionIndex) {

        this.familyPawn = familyPawn;
        this.actionIndex = actionIndex;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }
    }

    public FamilyPawn getFamilyPawn() {
        return familyPawn;
    }

    public int getActionIndex() {
        return actionIndex;
    }
}
