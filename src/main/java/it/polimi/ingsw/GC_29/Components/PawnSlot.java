package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class PawnSlot{
    protected ArrayList<Pawn> playerPawns;

    /*
    * this field needs to know how many Pawns can stay inside the slot
    * in case of B&M that let the player put the Pawn in slots already occupied the controller will not use this attribute
     */
    private int maxNumberOfPawns;

    private boolean free;

    public PawnSlot(int maxNumberOfPawns, boolean free) {
        this.playerPawns = new ArrayList<>(); // correct use of arrayList, we need to be capable of altering the length of the list
        this.maxNumberOfPawns = maxNumberOfPawns;
        this.free = free;
    }


    public List<Pawn> getPlayerPawns() {
        return playerPawns;
    }

    public int getMaxNumberOfPawns() {
        return maxNumberOfPawns;
    }


    protected void addPawn(Pawn pawn) {

        if (playerPawns.size() < maxNumberOfPawns) {

            playerPawns.add(pawn);

            this.free = false;

        } else {

            System.out.println("You can't add a pawn, maximum number of pawns reached");

        }
    }

    protected void removePawn(Pawn pawn) {

        playerPawns.remove(pawn);

        if (playerPawns.isEmpty()) free = true;
    }

    protected Pawn findPawn(Pawn pawn) {

        if (playerPawns.contains(pawn)) {
            int index = playerPawns.indexOf(pawn);
            return playerPawns.get(index);
        }

        else return null;
    }

    protected Pawn findPawn(PlayerColor playerColor) {

        for (Pawn playerPawn : playerPawns) {
            if (playerPawn.getPlayerColor() == playerColor) return playerPawn;
        }

        return null;
    }



    protected void clearSlot(){

        playerPawns.clear(); // more efficient vs removeAll() method. Clear() is O(n), removeAll is O(n^2)

        free = true;
    }


    /**
     * This method search if there is a Pawn of the same colour of the parameter
     * @param familyPawn
     * @return
     */
    public boolean searchFamiliar(FamilyPawn familyPawn) {

        boolean familiarPresent = false;
        for (Pawn playerPawn : playerPawns) {
            if (playerPawn.getPlayerColor() == familyPawn.getPlayerColor()
                    && familyPawn.getType() != FamilyPawnType.NEUTRAL
                    && familyPawn.getType() != FamilyPawnType.BONUS)
                familiarPresent = true;
        }
        return familiarPresent;
    }

    public boolean isFree() {
        return free;
    }

    @Override
    public String toString() {
        return "PawnSlot{" +
                "playerPawns=" + playerPawns +
                ", maxNumberOfPawns=" + maxNumberOfPawns +
                ", free=" + free +
                '}';
    }
}
