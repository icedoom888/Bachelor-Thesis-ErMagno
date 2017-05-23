package it.polimi.ingsw.GC_29.EffectBonusAndActions;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import it.polimi.ingsw.GC_29.ProveJackSon.EffectDeSerializer;
//import it.polimi.ingsw.GC_29.ProveJackSon.EffectDeSerializer;

/**
 * Created by Lorenzotara on 17/05/17.
 */

/* @JsonDeserialize(using = EffectDeSerializer.class) */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)
@JsonSubTypes( {
        @JsonSubTypes.Type(value = ObtainEffect.class, name = "ObtainEffect"),
        @JsonSubTypes.Type(value = ActionEffect.class, name = "ActionEffect"),
        @JsonSubTypes.Type(value = BonusEffect.class, name = "BonusEffect"),
        @JsonSubTypes.Type(value = CouncilPrivilegeEffect.class, name = "CouncilPrivilegeEffect")
})
public interface Effect {

    void execute(PlayerStatus status);
}
