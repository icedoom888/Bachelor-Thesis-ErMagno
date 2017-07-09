package it.polimi.ingsw.GC_29.Model;

import de.vandermeer.asciitable.AsciiTable;

import java.util.Arrays;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class CouncilPalaceActionSpace extends ActionSpace {

    private Effect effect_2;
    private int numberOfPlayers;
    private PlayerColor[] turnOrder;

    public CouncilPalaceActionSpace(int numberOfPlayers) {
        super(new ObtainEffect(0,0,1,0,0,0,0),1, new PawnSlot(4*numberOfPlayers,true),false,false);
        this.effect_2 = new CouncilPrivilegeEffect(1);
        this.numberOfPlayers = numberOfPlayers;
        this.turnOrder = new PlayerColor[numberOfPlayers]; // it should have all the values initialized to null by default
    }

    public PlayerColor[] getTurnOrder() {

        return turnOrder;
    }

    public Effect getEffect_2() {
        return effect_2;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public String toString() {
        return "CouncilPalaceActionSpace{" +
                "the next turnOrder is:" + Arrays.toString(turnOrder) +
                '}';
    }

    /**
     * adding the pawn will probably creates differences in the turn order.
     * The methods calls the super.addPawn() and then setTurnOrder()
     * @param pawn
     */
    @Override
    public void addPawn(FamilyPawn pawn) {
        super.addPawn(pawn);
        setTurnOrder(pawn.getPlayerColor());
    }

    /**
     * It removes the pawn from the turnOrder
     * @param familyPawn
     */
    @Override
    public void removePawn(FamilyPawn familyPawn){
        super.removePawn(familyPawn);
        for(int r=0; r<turnOrder.length; r++){
            if (turnOrder[r]==familyPawn.getPlayerColor()){
                turnOrder[r] = null;
            }
        }
    }

    @Override
    public void clean(){
        super.clean();

        for (int r=0; r <turnOrder.length; r++){
            turnOrder[r]= null;
        }
    }

    /**
     * The playerColor is put in the array only if the turnOrder does not already contains
     * that color
     * @param pawnColor
     */
    public void setTurnOrder(PlayerColor pawnColor) {

        if (!turnOrderContains(pawnColor)) {

            for (int i = 0; i < turnOrder.length; i++) {
                if (turnOrder[i] == null) {
                    turnOrder[i] = pawnColor;
                    break;
                }
            }
        }
    }

    private boolean turnOrderContains(PlayerColor pawnColor) {

        for (PlayerColor playerColor : turnOrder) {
            if (pawnColor == playerColor) return true;
        }

        return false;

    }

    public String toTable() {

        AsciiTable councilTable = new AsciiTable();

        int i = 0;
        for (PlayerColor playerColor : turnOrder) {
            councilTable.addRule();
            councilTable.addRow(i + ")", playerColor);
        }

        return "\n\n\n" + "COUNCIL PALACE \n\n" + councilTable.render();
    }
}
