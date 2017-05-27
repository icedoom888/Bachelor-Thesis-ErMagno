package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by AlbertoPennino on 22/05/2017.
 */

public class WorkAction extends Action {

    private int fieldSelected;

    public WorkAction(FamilyPawn familyPawn, ZoneType zoneType, PlayerStatus playerStatus, int fieldSelected){
        super(familyPawn, zoneType, playerStatus);

        this.fieldSelected = fieldSelected;
    }

    @Override
    public void execute(){
    }
    @Override
    public void update(){

    }
}

