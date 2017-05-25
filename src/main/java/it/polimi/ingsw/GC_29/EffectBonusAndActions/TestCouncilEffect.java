package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 25/05/17.
 */
public class TestCouncilEffect {

    public static void main(String[] args) throws Exception {

        int numberOfCouncilPrivileges = 3;
        CouncilPrivilegeEffect effect = new CouncilPrivilegeEffect(numberOfCouncilPrivileges);
        PlayerStatus playerStatus = new PlayerStatus(null, new ArrayList<BonusAndMalusOnGoods>(), new GoodSet(), null, true, true, true, true);

        effect.execute(playerStatus);
        //System.out.println(effect.checkDifferentPrivileges());

    }
}
