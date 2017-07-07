package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.Input.Input;
import it.polimi.ingsw.GC_29.Model.CouncilPrivilege;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.ObtainEffect;

import java.util.List;

/**
 * Created by Christian on 17/06/2017.
 */
public class PrivilegeChosen extends Input {

    private List<Integer> councilPrivilegeEffectChosenList;

    public PrivilegeChosen(List<Integer> councilPrivilegeEffectChosenList) {

        this.councilPrivilegeEffectChosenList = councilPrivilegeEffectChosenList;
    }


    @Override
    public void perform(Model model, Controller controller) {

        CouncilPrivilege councilPrivilege = new CouncilPrivilege();

        ObtainEffect obtainEffect;

        for (Integer integer : councilPrivilegeEffectChosenList) {

            System.out.println(integer);

            obtainEffect = councilPrivilege.getPossibleObtainEffect().get(integer);

            obtainEffect.execute(model.getCurrentPlayer());
        }



        if(model.getCurrentPlayer().getLastState() != null){

            model.getCurrentPlayer().setPlayerState(model.getCurrentPlayer().getLastState());

            model.getCurrentPlayer().setLastState(null);

        }

        else{

            controller.handleEndAction();
        }



    }
}
