package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class JSObtainEffectToFile {

    public static void main(String[] args) throws IOException {
        Effect obtainEffect = new ObtainEffect(new GoodSet(1,1,1,1,1,1,1));

        ObjectMapper mapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter("/Users/Lorenzotara/Desktop/cartaProva");

        mapper.writeValue(fileWriter, obtainEffect);

        fileWriter.close();
    }
}
