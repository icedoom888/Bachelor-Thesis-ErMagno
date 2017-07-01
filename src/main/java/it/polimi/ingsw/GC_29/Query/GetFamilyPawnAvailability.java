package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;
import it.polimi.ingsw.GC_29.Components.FamilyPawnType;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorenzotara on 15/06/17.
 */
public class GetFamilyPawnAvailability extends Query<Map<FamilyPawn, Boolean>> {
    @Override
    public Map<FamilyPawn, Boolean> perform(GameStatus model) {

        Map<FamilyPawnType, Boolean> familyPawnsAvailability = model.getCurrentPlayer().getFamilyPawnAvailability();

        System.out.println(familyPawnsAvailability);

        List<FamilyPawn> familyPawns = model.getCurrentPlayer().getFamilyPawns();

        HashMap<FamilyPawn, Boolean> familyPawnsAvailable = new HashMap<>();

        for (FamilyPawn familyPawn : familyPawns) {
            familyPawnsAvailable.put(familyPawn, familyPawnsAvailability.get(familyPawn.getType()));
        }

        return familyPawnsAvailable;

    }
}
