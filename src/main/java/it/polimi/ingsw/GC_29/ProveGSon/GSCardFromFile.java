package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.GC_29.Components.DevelopmentCard;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class GSCardFromFile {

    public static void main(String[] args) throws FileNotFoundException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ObtainEffect.class, new ObtainEffectInstanceCreator());
        Gson gson = gsonBuilder.create();

        FileReader fileReader = new FileReader("/Users/Lorenzotara/Desktop/cartaProva");

        DevelopmentCard card = gson.fromJson(fileReader, DevelopmentCard.class);

        System.out.println(card);

    }
}