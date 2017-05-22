package it.polimi.ingsw.GC_29.ProveJackSon;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.GC_29.Components.DevelopmentCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class MainJackSonFromFile {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("/Users/Lorenzotara/Desktop/cartaProva");
        DevelopmentCard card;

        ObjectMapper mapper = new ObjectMapper();

        card = mapper.readValue(fileReader, DevelopmentCard.class);

        fileReader.close();
    }
}
