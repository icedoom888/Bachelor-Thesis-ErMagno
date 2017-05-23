package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Lorenzotara on 20/05/17.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CouncilPrivilegeType {
    ONEWOOD_ONESTONE,
    TWOWORKERS,
    TWOGOLDS,
    TWOMILITARYPOINTS,
    ONEFAITHPOINT
}
