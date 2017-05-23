package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;

import java.io.IOException;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class ObtainEffectDeSerializer extends JsonDeserializer<ObtainEffect> {

    @Override
    public ObtainEffect deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        if (root.has("cost")) {
            return mapper.readValue(root.toString(), PayToObtainEffect.class);

        } else if (root.has("goodsForEachCondition")) {
            return mapper.readValue(root.toString(), ObtainOnConditionEffect.class);

        }

        throw context.mappingException("Failed to de-serialize effect, as name property not exist");
    }
}
