package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.Workspace;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by AlbertoPennino on 22/05/2017.
 */

public class WorkAction extends Action {

    private Workspace workspaceSelected;
    private int fieldSelected;
    private int reductionOnSecondField;

    public WorkAction(FamilyPawn familyPawn, ZoneType zoneType, int workers, PlayerStatus playerStatus,Workspace workspaceSelected, int fieldSelected, int reductionOnSecondField){

        super(familyPawn, zoneType, workers, playerStatus);

        this.workspaceSelected = workspaceSelected;
        this.fieldSelected = fieldSelected;
        this.reductionOnSecondField = reductionOnSecondField;
    }

    @Override
    public boolean isPossible(){
        if(super.isPossible()){

        }
    }

    @Override
    public void execute(){
    }
    @Override
    public void update(){

    }
}

