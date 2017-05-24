package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import it.polimi.ingsw.GC_29.ProveJackSon.EffectDeSerializer;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public interface Effect {

    void execute(PlayerStatus status);
}
