package it.polimi.ingsw.GC_29.Client.GUI;

import it.polimi.ingsw.GC_29.Components.FamilyPawn;

import java.util.Map;

/**
 * Created by Lorenzotara on 24/06/17.
 */
public class FamilyPawnChange {

    private Map<FamilyPawn, Boolean> familyPawns;

    public FamilyPawnChange(Map<FamilyPawn, Boolean> familyPawns) {
        this.familyPawns = familyPawns;
    }

    public Map<FamilyPawn, Boolean> getFamilyPawns() {
        return familyPawns;
    }
}
