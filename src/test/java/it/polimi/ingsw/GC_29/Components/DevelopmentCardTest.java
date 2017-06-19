package it.polimi.ingsw.GC_29.Components;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Lorenzotara on 19/06/17.
 */
public class DevelopmentCardTest {
    @Test
    public void testToString() throws Exception {
        DevelopmentCard developmentCard = new DevelopmentCard("ciao", Era.FIRST, null, null, null, null, false,0);
        System.out.println(developmentCard.toString());
        toTable(developmentCard);
    }

    public void toTable(DevelopmentCard developmentCard) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("name" + developmentCard.getSpecial());
        asciiTable.addRule();
        asciiTable.setTextAlignment(TextAlignment.CENTER);
        String rend = asciiTable.render();
        System.out.println(rend);
    }

}