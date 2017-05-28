package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.util.HashMap;

/**
 * Created by Lorenzotara on 17/05/17.
 */

//TODO: rivedere costruttore fatto per togliere errori a caso
public class Workspace implements Cleanable {
    private HashMap<Integer,ActionSpace> fields;
    private ZoneType type;

    public Workspace (ZoneType type,int numberOfPlayers) {

        if (type != ZoneType.HARVEST && type != ZoneType.PRODUCTION){
            throw new IllegalArgumentException("Illegal area type: " + type);
        }

        this.type = type;
        this.fields = new HashMap<Integer,ActionSpace>(2);
        fields.put(0,new ActionSpace(null,1, new PawnSlot(1,true),true,false));
        if(numberOfPlayers>=3) {
            fields.put(1,new ActionSpace(null,1, new PawnSlot(numberOfPlayers,true),false,false));
        }
        else{
            fields.put(1,null);
        }
    }

    @Override
    public void clean() {

    }

    public HashMap<Integer, ActionSpace> getFields() {
        return fields;
    }

    public ZoneType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "fields=" + fields +
                ", type=" + type +
                '}';
    }
}
