package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class JSArrayOfCardsToFile {

    public static void main(String[] args) throws IOException {

        ArrayList<DevelopmentCard> cards = new ArrayList<DevelopmentCard>();

        // Ospitare i mendicanti

        ArrayList<Effect> immediateEffectsOIM = new ArrayList<Effect>();
        immediateEffectsOIM.add(new ObtainEffect(0,0,0,4,0,0,0));

        ArrayList<Effect> permanentEffectsOIM = new ArrayList<Effect>();
        permanentEffectsOIM.add(new ObtainEffect(0,0,0,0,4,0,0));


        DevelopmentCard ospitareIMendicanti = new DevelopmentCard(
                "Ospitare i Mendicanti",
                "descrizione",
                Era.FIRSTERA,
                new CardCost(false, true, new GoodSet(4,0,0,0,0,0,0), new GoodSet(), false, new GoodSet()),
                CardColor.PURPLE,
                immediateEffectsOIM,
                permanentEffectsOIM,
                false,
                0);

        cards.add(ospitareIMendicanti);

        // Combattere le Eresie

        ArrayList<Effect> immediateEffectsCLE = new ArrayList<Effect>(1);
        immediateEffectsCLE.add(new ObtainEffect(0,0,0,0,0,2,0));

        ArrayList<Effect> permanentEffectsCLE = new ArrayList<Effect>(1);
        permanentEffectsCLE.add(new ObtainEffect(0,0,0,0,5,0,0));


        DevelopmentCard combattereLeEresie = new DevelopmentCard(
                "Combattere le Eresie",
                "descrizione",
                Era.FIRSTERA,
                new CardCost(false, true, new GoodSet(4,0,0,0,0,0,0), new GoodSet(), false, new GoodSet()),
                CardColor.PURPLE,
                immediateEffectsCLE,
                permanentEffectsCLE,
                false,
                0);

        cards.add(combattereLeEresie);

        // JACKSON

        ObjectMapper mapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter("/Users/Lorenzotara/Desktop/cartaProva");

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(fileWriter, cards);

        fileWriter.close();

    }
}
