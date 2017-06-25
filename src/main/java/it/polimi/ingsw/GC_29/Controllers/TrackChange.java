package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Client.GUI.GuiChangeListener;
import it.polimi.ingsw.GC_29.Components.GoodType;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.List;

/**
 * Created by Lorenzotara on 25/06/17.
 */
public class TrackChange extends GUIChange {

    private PlayerColor playerColor;
    private GoodType goodType;
    private int numberOfPoints;


    public TrackChange(PlayerColor playerColor, GoodType goodType, int numberOfPoints) {
        this.playerColor = playerColor;
        this.goodType = goodType;
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public void perform(List<GuiChangeListener> listeners) {

        for (GuiChangeListener listener : listeners) {
            listener.onReadingChange(this);
        }

    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
