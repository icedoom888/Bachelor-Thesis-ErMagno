package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GC_29.Components.DevelopmentCard;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Lorenzotara on 23/05/17.
 */
public class JSArrayOfCardsFromFile {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("/Users/Lorenzotara/Desktop/cartaProva");
        DevelopmentCard[] cards;

        ObjectMapper mapper = new ObjectMapper();


        cards = mapper.readValue(fileReader, DevelopmentCard[].class);

        for (DevelopmentCard card : cards) {
            System.out.println(card);
        }

        fileReader.close();
    }
}
