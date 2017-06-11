package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Christian on 21/05/2017.
 */
public class ExecuteActionTurnState implements TurnState {
    @Override
    public void executeState(PlayerController wrapper) throws Exception {

        wrapper.getPlayerStatus().getCurrentAction().execute();
        
        if(wrapper.checkPresenceBonusActionEffect()){

            wrapper.setCurrentTurnState(new BonusActionTurnState());
        }

        else{

            wrapper.setCurrentTurnState(new EndTurnState());
        }
    }
}
