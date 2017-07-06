package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Player.Player;

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
    public void perform(GameStatus model, Controller controller) throws RemoteException {

        player = model.getCurrentPlayer();

        FamilyPawn familyPawn = player.getFamilyPawn(familyPawnType);

        ActionChecker actionChecker = controller.getActionChecker();

        actionChecker.resetActionListExceptPlayer();

        actionChecker.setValidActionForFamilyPawn(familyPawn);

        player.setPlayerState(PlayerState.CHOOSEACTION);

    }
}
