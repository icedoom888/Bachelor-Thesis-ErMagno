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
    private Player player;

    public UsePawnChosen(FamilyPawnType familyPawnType){

        this.familyPawnType = familyPawnType;
    }

    @Override
    public void perform(GameStatus model) throws Exception {

        player = model.getCurrentPlayer();

        // TODO: inserire qui o a lato client un controllo sulla disponibilità della pedina

        FamilyPawn familyPawn = player.getFamilyPawn(familyPawnType);

        ActionChecker.getInstance().resetActionListExceptPlayer();

        ActionChecker.getInstance().setValidActionForFamilyPawn(familyPawn);

        player.setPlayerState(PlayerState.CHOOSEACTION);

    }
}
