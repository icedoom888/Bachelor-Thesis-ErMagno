package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Lorenzotara on 20/05/17.
 */
class CouncilTest {

    public static void main(String[] args) throws Exception {

        int numberOfCouncilPrivileges = 3;
        CouncilPrivilegeEffect effect = new CouncilPrivilegeEffect(numberOfCouncilPrivileges);
        PlayerStatus playerStatus = new PlayerStatus(null, null, new GoodSet(), null, true, true, true, true);

        effect.execute(playerStatus);
        System.out.println(effect.checkDifferentPrivileges());

    }
}
