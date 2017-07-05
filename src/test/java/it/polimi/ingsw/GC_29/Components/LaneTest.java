package it.polimi.ingsw.GC_29.Components;

import org.junit.Test;

/**
 * Created by AlbertoPennino on 06/07/2017.
 */
public class LaneTest {
    @Test
    public void TestToTable(){
        Lane lane1 = new Lane(6);
        DevelopmentCard card = new DevelopmentCard("ciao",Era.FIRST,null,CardColor.BLUE,null,null,true,5);
        TerritoryLane territoryLane = new TerritoryLane(6);
        lane1.addCard(card);
        territoryLane.addCard(card);
        System.out.print(lane1.toTable());
        System.out.print(territoryLane.toTable());

    }
}
