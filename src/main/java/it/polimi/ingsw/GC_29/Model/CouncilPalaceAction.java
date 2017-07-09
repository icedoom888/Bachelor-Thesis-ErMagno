package it.polimi.ingsw.GC_29.Model;

/**
 * Created by Lorenzotara on 19/05/17.
 */
public class CouncilPalaceAction extends Action {

    public CouncilPalaceAction(Model model) {

        super(ZoneType.COUNCILPALACE, model);
        this.actionSpaceSelected = this.model.getGameBoard().getCouncilPalace();

    }


    /**
     * executes the action by paying the workers, adding the pawn on the actionSpace,
     * executing the CouncilPrivilege Effect, adding the player in the turn order.
     */
    @Override
    public void execute() {

        super.payWorkers();
        super.addPawn();
        executeCouncilPrivilegeEffect();
        setOrder();
        super.update();
    }


    /**
     * setOrder sets the turn order only if the familyPawnType is not Neutral and the Action that
     * the player is making is real. If there are no other pawns of the player in the turnOrder track, then
     * the pawn is put in the first free space.
     */
    private void setOrder() {
        if (temporaryPawn.getType() != FamilyPawnType.NEUTRAL && temporaryPawn.getType() != FamilyPawnType.BONUS) {
            PlayerColor[] turnOrder = ((CouncilPalaceActionSpace) actionSpaceSelected).getTurnOrder();
            PlayerColor currentPlayerColor = temporaryPawn.getPlayerColor();

            int firstFreeSpace = 0;
            for (PlayerColor playerColor : turnOrder) {
                if (playerColor == currentPlayerColor || playerColor == null) return;
                firstFreeSpace++;
            }

            turnOrder[firstFreeSpace] = currentPlayerColor;
        }
    }

    private void executeCouncilPrivilegeEffect() {
        ((CouncilPalaceActionSpace) actionSpaceSelected).getEffect_2().execute(player);
    }

    @Override
    public String toString() {
        return "CouncilPalaceAction{" + super.toString() + "}";
    }

}
