package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 21/05/2017.
 */
public class ExecuteActionState implements State {
    @Override
    public void executeState(PlayerController wrapper) {

        wrapper.getPlayerStatus().getCurrentAction().execute();
        
        if(wrapper.checkPresenceBonusActionEffect()){

            wrapper.setCurrentState(new BonusActionState());
        }

        wrapper.setCurrentState(new EndTurnState());
    }
}
