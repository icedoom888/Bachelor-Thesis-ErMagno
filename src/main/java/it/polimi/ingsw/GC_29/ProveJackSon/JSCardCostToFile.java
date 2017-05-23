package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GC_29.Components.CardCost;
import it.polimi.ingsw.GC_29.Components.GoodSet;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class JSCardCostToFile {

    public static void main(String[] args) throws IOException {
        CardCost cardCost = new CardCost(false, true, new GoodSet(4,0,0,0,0,0,0), new GoodSet(), false, new GoodSet());

        ObjectMapper mapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter("/Users/Lorenzotara/Desktop/cartaProva");

        mapper.writeValue(fileWriter, cardCost);

        fileWriter.close();
    }
}
