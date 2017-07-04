package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.List;

/**
 * Created by Lorenzotara on 04/07/17.
 */
public class TrackReset extends GUIChange {

    private PlayerColor playerColor;
    private GoodType goodType;
    private Integer numberOfPoints;

    public TrackReset(PlayerColor playerColor, GoodType goodType, int numberOfPoints) {
        this.playerColor = playerColor;
        this.goodType = goodType;
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {
        for (GuiChangeListener listener : listeners) {
            listener.resetTrack(playerColor, goodType, numberOfPoints);
        }
    }


}
