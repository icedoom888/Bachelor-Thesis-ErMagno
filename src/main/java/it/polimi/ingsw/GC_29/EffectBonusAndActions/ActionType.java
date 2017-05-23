package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Christian on 18/05/2017.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActionType {

    GREENTOWER,
    YELLOWTOWER,
    BLUETOWER,
    PURPLETOWER,
    MARKET,
    COUNCILPALACE,
    HARVEST,
    PRODUCTION,
    SKIPTURN
}
