package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.Arrays;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class CouncilPalaceActionSpace extends ActionSpace {
    private int numberOfPlayers;
    private PlayerColor[] turnOrder;

    public CouncilPalaceActionSpace(Effect effect, int actionCost, PawnSlot pawnPlaced, boolean single, boolean occupied, int numberOfPlayers) {
        super(effect, actionCost, pawnPlaced, single, occupied);
        this.numberOfPlayers = numberOfPlayers;
        this.turnOrder = new PlayerColor[numberOfPlayers]; // it should have all the values initialized to null by default
    }

    public PlayerColor[] getTurnOrder() {

        return turnOrder;
    }

    @Override
    public String toString() {
        return "CouncilPalaceActionSpace{" +
                "the next turnOrder is:" + Arrays.toString(turnOrder) +
                '}';
    }

    @Override
    public void addPawn(FamilyPawn pawn) {
        // TODO: impl -> metodo che aggiunge pawn a actionSpace e turnOrder
    }

    @Override
    public void removePawn() {
        // TODO: impl -> rimuove sia da actionSpace che da turnOrder
    }

    public void setTurnOrder(PlayerColor pawnColor) {
        // TODO: ciclo for
    }
}
