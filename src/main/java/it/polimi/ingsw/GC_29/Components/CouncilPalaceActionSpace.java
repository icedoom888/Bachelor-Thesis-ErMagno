package it.polimi.ingsw.GC_29.Components;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class CouncilPalaceActionSpace extends ActionSpace {
    private PlayerColor[] turnOrder;

    public PlayerColor[] getTurnOrder() {
        return turnOrder;
    }

    @Override
    public void addPawn(FamilyPawn pawn) {
        // TODO: impl -> metodo che aggiunge pawn a actionSpace e turnOrder
    }

    @Override
    public void removePawns() {
        // TODO: impl -> rimuove sia da actionSpace che da turnOrder
    }

    public void setTurnOrder(PlayerColor pawnColor) {
        // TODO: ciclo for
    }
}
