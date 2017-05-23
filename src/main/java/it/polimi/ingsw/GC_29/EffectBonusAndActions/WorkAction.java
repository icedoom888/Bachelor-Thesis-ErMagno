package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by AlbertoPennino on 22/05/2017.
 */
public class WorkAction extends Action {
    public WorkAction(FamilyPawn pawnSelected, ActionType actionSelected, int workersSelected, boolean realAction, PlayerStatus playerStatus) {
        super(pawnSelected, actionSelected, workersSelected, realAction, playerStatus);
    }
    /*public WorkAction(){
    }*/

    @Override
    public void execute(){
    }
    @Override
    public void update(){

    }
}
