package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Christian on 18/05/2017.
 */
public enum ActionType {

    @JsonProperty("GREENTOWER")
    GREENTOWER,
    @JsonProperty("YELLOWTOWER")
    YELLOWTOWER,
    @JsonProperty("BLUETOWER")
    BLUETOWER,
    @JsonProperty("PURPLETOWER")
    PURPLETOWER,
    @JsonProperty("MARKET")
    MARKET,
    @JsonProperty("COUNCILPALACE")
    COUNCILPALACE,
    @JsonProperty("HARVEST")
    HARVEST,
    @JsonProperty("PRODUCTION")
    PRODUCTION,
    @JsonProperty("SKIPTURN")
    SKIPTURN
}
