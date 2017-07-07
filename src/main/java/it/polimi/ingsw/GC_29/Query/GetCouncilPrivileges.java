package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.CouncilPrivilegeEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetCouncilPrivileges extends Query<List<Integer>> {

    @Override
    public List<Integer> perform(Model model) {

        List<Integer> parchmentAmountList = new ArrayList<>();

        for (CouncilPrivilegeEffect councilPrivilegeEffect : model.getCurrentPlayer().getCouncilPrivilegeEffectList()) {

            parchmentAmountList.add(councilPrivilegeEffect.getNumberOfCouncilPrivileges());
        }

        model.getCurrentPlayer().getCouncilPrivilegeEffectList().clear();

        return parchmentAmountList;
    }
}
