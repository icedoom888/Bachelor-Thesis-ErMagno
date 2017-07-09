package it.polimi.ingsw.GC_29.Controllers.Input;

import it.polimi.ingsw.GC_29.Controllers.ActionChecker;
import it.polimi.ingsw.GC_29.Controllers.Controller;
import it.polimi.ingsw.GC_29.Controllers.Input.Input;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Model.FamilyPawn;
import it.polimi.ingsw.GC_29.Model.FamilyPawnType;
import it.polimi.ingsw.GC_29.Model.Model;
import it.polimi.ingsw.GC_29.Model.Player;

import java.rmi.RemoteException;

/**
 * Created by Christian on 07/06/2017.
 */
public class UsePawnChosen extends Input {


    private FamilyPawnType familyPawnType;
    private transient Player player;

    public UsePawnChosen(FamilyPawnType familyPawnType){

        this.familyPawnType = familyPawnType;
    }

    @Override
    public void perform(Model model, Controller controller) throws RemoteException {

        player = model.getCurrentPlayer();

        FamilyPawn familyPawn = player.getFamilyPawn(familyPawnType);

        ActionChecker actionChecker = controller.getActionChecker();

        actionChecker.resetActionListExceptPlayer();

        System.out.println("SONO IN USE PAWN CHOSEN COLORE " + familyPawnType + "VALORE " +familyPawn.getActualValue());

        actionChecker.setValidActionForFamilyPawn(familyPawn);

        System.out.println("SONO IN USE PAWN CHOSEN COLORE " + familyPawnType + "VALORE " +familyPawn.getActualValue());

        player.setPlayerState(PlayerState.CHOOSEACTION);

    }
}
