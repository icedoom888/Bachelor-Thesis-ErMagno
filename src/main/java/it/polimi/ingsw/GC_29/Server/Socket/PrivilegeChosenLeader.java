package it.polimi.ingsw.GC_29.Server.Socket;

/**
 * Created by Lorenzotara on 03/07/17.

public class PrivilegeChosenLeader extends Input {

    private List<Integer> councilPrivileges;
    private PlayerColor playerColor;

    public PrivilegeChosenLeader(List<Integer> councilPrivileges, PlayerColor playerColor) {
        this.councilPrivileges = councilPrivileges;
        this.playerColor = playerColor;
    }

    @Override
    public void perform(Model model, Controller controller) throws Exception {

        CouncilPrivilege councilPrivilege = new CouncilPrivilege();

        ObtainEffect obtainEffect;

        for (Integer integer : councilPrivileges) {

            System.out.println(integer);

            obtainEffect = councilPrivilege.getPossibleObtainEffect().get(integer);

            for (Player player : model.getTurnOrder()) {
                if (player.getPlayerColor() == playerColor) {
                    obtainEffect.execute(player);
                    player.setPlayerState(player.getLastState());
                    break;
                }
            }
        }
    }
} */
