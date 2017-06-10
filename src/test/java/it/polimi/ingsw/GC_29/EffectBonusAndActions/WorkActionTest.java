package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Controllers.GameSetup;
import org.testng.annotations.Test;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.*;
import it.polimi.ingsw.GC_29.ProveGSon.MainGSonFromFile;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Created by AlbertoPennino on 02/06/2017.

public class WorkActionTest {

    BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

    Player player1 = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6));

    Player player2 = new Player("Alberto", PlayerColor.RED, new PersonalBoard(bonusTile, 6));

    Player player3 = new Player("Christian", PlayerColor.YELLOW, new PersonalBoard(bonusTile, 6));

    Player player4 = new Player("Gianmario", PlayerColor.GREEN, new PersonalBoard(bonusTile, 6));

    ArrayList<Player> players = new ArrayList<Player>();
    // Creation of the gameboard


    // Creation of playerStatuses

    /*Player player1 = new Player("Player 1",PlayerColor.BLUE,personalBoard1);

    Player player2 = new Player("Player 2", PlayerColor.GREEN,personalBoard2);

    FamilyPawn selectedPawn1 = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 1);
    FamilyPawn selectedPawn2 = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.ORANGE, 3);
    FamilyPawn selectedPawn3 = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.NEUTRAL, 0);

    FamilyPawn selectedPawn4 = new FamilyPawn(PlayerColor.GREEN, FamilyPawnType.NEUTRAL, 0);
    FamilyPawn selectedPawn5 = new FamilyPawn(PlayerColor.GREEN, FamilyPawnType.WHITE, 4);


    FieldType fieldSelected1 = FieldType.FIRST;
    FieldType fieldSelected2 = FieldType.SECOND;

    ArrayList<DevelopmentCard> greenCards = null;
    ArrayList<DevelopmentCard> yellowCards = null;

    @Test
    public void isPossibleTest() throws FileNotFoundException {
        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        player1.getActualGoodSet().addGoodSet(new GoodSet(0,0,0,10,0,0,0));
        WorkAction action1 = new WorkAction(ZoneType.HARVEST,fieldSelected2);
        WorkAction action2 = new WorkAction(ZoneType.HARVEST,fieldSelected1);
        WorkAction action3 = new WorkAction(ZoneType.HARVEST,fieldSelected1);
        WorkAction action4 = new WorkAction(ZoneType.HARVEST,fieldSelected2);
        WorkAction action5 = new WorkAction(ZoneType.HARVEST,fieldSelected2);

        action1.setFamiliyPawn(selectedPawn1);
        action1.setPlayer(player1);
        action2.setFamiliyPawn(selectedPawn1);
        action2.setPlayer(player1);
        action3.setFamiliyPawn(selectedPawn2);
        action3.setPlayer(player1);
        action4.setFamiliyPawn(selectedPawn2);
        action4.setPlayer(player1);
        action5.setFamiliyPawn(selectedPawn3);
        action5.setPlayer(player1);

        GameStatus.getInstance().setCurrentAction(action1);
        System.out.println("executing action 1");
        action1.execute();
        GameStatus.getInstance().setCurrentAction(action2);
        System.out.println("executing action 2");
        action2.execute();
        GameStatus.getInstance().setCurrentAction(action3);
        System.out.println("executing action 3");
        action3.execute();
        GameStatus.getInstance().setCurrentAction(action4);
        System.out.println("executing action 4");
        action4.execute();
        GameStatus.getInstance().setCurrentAction(action5);
        System.out.println("executing action 5");
        action5.execute();

        GoodSet finalAmmount = player1.getActualGoodSet();
        System.out.println(finalAmmount);
    }

    @Test
    public void WorkActionTest1() throws FileNotFoundException {
        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        greenCards = MainGSonFromFile.cards().get(CardColor.GREEN);

        player1.getPersonalBoard().getTerritoryLane().addCard(greenCards.get(0));
        player1.getPersonalBoard().getTerritoryLane().addCard(greenCards.get(2));
        player1.getPersonalBoard().getTerritoryLane().addCard(greenCards.get(3));

        System.out.println(player1.getActualGoodSet());
        player1.getActualGoodSet().addGoodSet(new GoodSet(0,0,0,2,0,0,0));
        System.out.println(player1.getActualGoodSet());

        WorkAction action = new WorkAction(ZoneType.HARVEST,fieldSelected1);
        action.setFamiliyPawn(selectedPawn1);
        action.setPlayer(player1);

        GameStatus.getInstance().setCurrentAction(action);
        System.out.println("executing action..");
        action.execute();

        GoodSet amount = player1.getActualGoodSet();
        System.out.println(amount);

    }

    @Test
    public void WorkActionTest2() throws FileNotFoundException{
        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        yellowCards = MainGSonFromFile.cards().get(CardColor.YELLOW);

        player1.getPersonalBoard().getBuildingLane().addCard(yellowCards.get(0));
        player1.getPersonalBoard().getBuildingLane().addCard(yellowCards.get(2));
        player1.getPersonalBoard().getBuildingLane().addCard(yellowCards.get(3));

        System.out.println(player1.getActualGoodSet());
        player1.getActualGoodSet().addGoodSet(new GoodSet(0,0,0,0,0,0,0));
        System.out.println(player1.getActualGoodSet());

        WorkAction action = new WorkAction(ZoneType.PRODUCTION,fieldSelected1);
        action.setFamiliyPawn(selectedPawn1);
        action.setPlayer(player1);

        GameStatus.getInstance().setCurrentAction(action);
        System.out.println("executing action..");
        action.execute();

        GoodSet amount = player1.getActualGoodSet();
        System.out.println(amount);


    }

    @Test
    public void testIsPossible1() throws Exception {
    }
}*/