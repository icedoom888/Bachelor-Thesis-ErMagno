package it.polimi.ingsw.GC_29.Server.Socket;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.Controllers.Input;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilege;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;

import java.util.List;

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
    public void perform(GameStatus model, Controller controller) throws Exception {

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
