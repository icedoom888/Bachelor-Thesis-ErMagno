package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class EffectsArrayListJsonDeserializer extends JsonDeserializer<ArrayList<Effect>> {

    @Override
    public ArrayList<Effect> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
