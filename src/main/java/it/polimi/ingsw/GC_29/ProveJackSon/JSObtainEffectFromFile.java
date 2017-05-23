package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GC_29.Components.DevelopmentCard;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class JSObtainEffectFromFile {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("/Users/Lorenzotara/Desktop/cartaProva");
        Effect obtainEffect;

        ObjectMapper mapper = new ObjectMapper();

        obtainEffect = mapper.readValue(fileReader, ObtainEffect.class);

        System.out.println(obtainEffect);

        fileReader.close();
    }
}
