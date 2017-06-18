package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Controllers.GameStatus;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetCouncilPrivileges extends Query<List<Integer>> {

    @Override
    public List<Integer> perform(GameStatus model) {

        List<Integer> parchmentAmountList = new ArrayList<>();

        for (CouncilPrivilegeEffect councilPrivilegeEffect : model.getCurrentPlayer().getCouncilPrivilegeEffectList()) {

            parchmentAmountList.add(councilPrivilegeEffect.getNumberOfCouncilPrivileges());
        }

        model.getCurrentPlayer().getCouncilPrivilegeEffectList().clear();

        return parchmentAmountList;
    }
}
