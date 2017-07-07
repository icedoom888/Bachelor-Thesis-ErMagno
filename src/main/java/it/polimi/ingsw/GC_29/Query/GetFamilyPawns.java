package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Model.FamilyPawn;
import it.polimi.ingsw.GC_29.Controllers.Model;

import java.util.List;

/**
 * Created by Lorenzotara on 19/06/17.
 */
public class GetFamilyPawns extends Query<List<FamilyPawn>> {
    @Override
    public List<FamilyPawn> perform(Model model) {
        return model.getCurrentPlayer().getFamilyPawns();
    }
}
