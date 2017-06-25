package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Client.InputChecker;
import it.polimi.ingsw.GC_29.Controllers.Change;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public abstract class GUIChange extends Change {

    public abstract void perform(List<GuiChangeListener> listeners);
}
