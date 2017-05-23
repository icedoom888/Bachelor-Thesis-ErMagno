package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.io.IOException;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class EffectDeSerializer extends JsonDeserializer<Effect> {

    public static final String OBTAINEFFECT = "obtainEffect";
    public static final String PAYTOOBTAINEFFECT = "payToObtainEffect";
    public static final String OBTAINONCONDITIONEFFECT = "obtainOnConditionEffect";
    public static final String ACTIONEFFECT = "actionEffect";
    public static final String BONUSEFFECT = "bonusEffect";

    @Override
    public Effect deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        if (root.has("goodsObtained")) {
            if (root.has("goodsForEachCondition")) {
                return mapper.readValue(root.toString(), ObtainOnConditionEffect.class);
            } else if (root.has("cost")) {
                return mapper.readValue(root.toString(), PayToObtainEffect.class);
            }
            return mapper.readValue(root.toString(), ObtainEffect.class);
        } else if (root.has("type")) {
            return mapper.readValue(root.toString(), ActionEffect.class);
        } else if (root.has("bonusAndMalusOnAction")) {
            return mapper.readValue(root.toString(), BonusEffect.class);
        } else if (root.has("numberOfCouncilPrivileges")) {
            return mapper.readValue(root.toString(), CouncilPrivilegeEffect.class);
        }

        throw context.mappingException("Failed to de-serialize effect, as name property not exist");
    }
}
