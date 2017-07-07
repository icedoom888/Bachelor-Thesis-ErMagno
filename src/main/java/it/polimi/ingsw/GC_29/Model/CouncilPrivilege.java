package it.polimi.ingsw.GC_29.Model;

import java.util.ArrayList;

/**
 * Created by Lorenzotara on 20/05/17.
 */

public class CouncilPrivilege {

    private ArrayList<ObtainEffect> possibleObtainEffect;

    public CouncilPrivilege(){

        possibleObtainEffect = new ArrayList<>();

        possibleObtainEffect.add(new ObtainEffect(1,1,0,0,0,0,0));
        possibleObtainEffect.add(new ObtainEffect(0,0,0,2,0,0,0));
        possibleObtainEffect.add(new ObtainEffect(0,0,2,0,0,0,0));
        possibleObtainEffect.add(new ObtainEffect(0,0,0,0,0,2,0));
        possibleObtainEffect.add(new ObtainEffect(0,0,0,0,0,0,1));
    }

    public ArrayList<ObtainEffect> getPossibleObtainEffect() {
        return possibleObtainEffect;
    }
}


