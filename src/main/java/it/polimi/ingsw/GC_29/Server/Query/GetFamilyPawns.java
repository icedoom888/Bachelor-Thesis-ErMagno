package it.polimi.ingsw.GC_29.Server.Query;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.util.List;

/**
 * Created by Lorenzotara on 19/06/17.
 */
public class GetFamilyPawns extends Query<List<FamilyPawn>> {
    @Override
    public List<FamilyPawn> perform(GameStatus model) {
        return model.getCurrentPlayer().getFamilyPawns();
    }
}
