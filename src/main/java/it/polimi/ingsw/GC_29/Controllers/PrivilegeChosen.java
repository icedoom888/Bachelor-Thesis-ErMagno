package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilege;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 17/06/2017.
 */
public class PrivilegeChosen extends Input {

    List<Integer> councilPrivilegeEffectChosenList;

    public PrivilegeChosen(List<Integer> councilPrivilegeEffectChosenList) {

        this.councilPrivilegeEffectChosenList = councilPrivilegeEffectChosenList;
    }


    @Override
    public void perform(GameStatus model, Controller controller) throws Exception {

        CouncilPrivilege councilPrivilege = new CouncilPrivilege();

        ObtainEffect obtainEffect;

        // TODO: raddoppiare

        for (Integer integer : councilPrivilegeEffectChosenList) {

            obtainEffect = councilPrivilege.getPossibleObtainEffect().get(integer);

            obtainEffect.execute(model.getCurrentPlayer());
        }

        controller.handleEndAction();

    }
}
