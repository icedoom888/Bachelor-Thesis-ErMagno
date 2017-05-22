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

    public static final String NAME = "Effect";
    public static final String OBTAINEFFECT = "obtainEffect";
    public static final String PAYTOOBTAINEFFECT = "payToObtainEffect";
    public static final String OBTAINONCONDITIONEFFECT = "obtainOnConditionEffect";
    public static final String ACTIONEFFECT = "ActionEffect";
    public static final String BONUSEFFECT = "BonusEffect";

    @Override
    public Effect deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        if (root.has(NAME)) {
            JsonNode jsonNode = root.get(NAME);
            if(jsonNode.asText().equals(OBTAINEFFECT)) {
                return mapper.readValue(root.toString(), ObtainEffect.class);
            } else if(jsonNode.asText().equals(PAYTOOBTAINEFFECT))
            {

                return mapper.readValue(root.toString(), PayToObtainEffect.class);
            } else if(jsonNode.asText().equals(OBTAINONCONDITIONEFFECT))
            {

                return mapper.readValue(root.toString(), ObtainOnConditionEffect.class);
            } else if(jsonNode.asText().equals(ACTIONEFFECT))
            {

                return mapper.readValue(root.toString(), ActionEffect.class);
            } else if(jsonNode.asText().equals(BONUSEFFECT))
            {

                return mapper.readValue(root.toString(), BonusEffect.class);
            }
        }
        throw context.mappingException("Failed to de-serialize effect, as name property not exist");
    }
}
