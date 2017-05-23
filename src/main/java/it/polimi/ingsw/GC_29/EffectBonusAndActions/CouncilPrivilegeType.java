package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Lorenzotara on 20/05/17.
 */
public enum CouncilPrivilegeType {
    @JsonProperty("ONEWOOD_ONESTONE")
    ONEWOOD_ONESTONE,
    @JsonProperty("TWOWORKERS")
    TWOWORKERS,
    @JsonProperty("TWOGOLDS")
    TWOGOLDS,
    @JsonProperty("TWOMILITARYPOINTS")
    TWOMILITARYPOINTS,
    @JsonProperty("ONEFAITHPOINT")
    ONEFAITHPOINT
}
