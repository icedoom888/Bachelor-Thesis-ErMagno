package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.util.Map;

/**
 * Created by Lorenzotara on 15/06/17.
 */
public class GetFamilyPawnAvailability extends Query<Map<FamilyPawnType, Boolean>> {
    @Override
    public Map<FamilyPawnType, Boolean> perform(GameStatus model) {
        return model.getCurrentPlayer().getFamilyPawnAvailability();

    }
}
