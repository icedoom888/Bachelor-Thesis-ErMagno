package it.polimi.ingsw.GC_29.Query;

import it.polimi.ingsw.GC_29.Components.CardColor;
import it.polimi.ingsw.GC_29.Components.Floor;
import it.polimi.ingsw.GC_29.Controllers.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lorenzotara on 18/06/17.
 */
public class GetTowerCard extends Query<List<String>> {

    private CardColor towerCardColor;

    public GetTowerCard(CardColor towerCardColor) {
        this.towerCardColor = towerCardColor;
    }

    @Override
    public List<String> perform(Model model) {

        List<String> returnList = new ArrayList<>();

        List<Floor> floors = Arrays.asList(model.getGameBoard().getTower(towerCardColor).getFloors());

        for (Floor floor : floors) {

            returnList.add( "Floor " + floors.indexOf(floor) + "\n" + floor.getDevelopmentCard().toString());
        }

        return returnList;
    }
}
