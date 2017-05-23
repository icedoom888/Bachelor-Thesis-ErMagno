package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GC_29.Components.CardCost;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class JSCardCostFromFile {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("/Users/Lorenzotara/Desktop/cartaProva");
        CardCost cardCost;

        ObjectMapper mapper = new ObjectMapper();

        cardCost = mapper.readValue(fileReader, CardCost.class);

        System.out.println(cardCost);

        fileReader.close();
    }
}
