/*package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.*;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

*
 * Created by Christian on 28/05/2017.

public class PlayerControllerTest {

    @Test
    public void testInit() {


        PlayerStatus currentPlayerStatus = new PlayerStatus(null, null);

        PersonalBoard testPersonalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        currentPlayerStatus.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        currentPlayerStatus.setPersonalBoard(testPersonalBoard);

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        Tower testTower = new Tower(ZoneType.GREENTOWER);

        testTower.getFloor(0).setDevelopmentCard(testCard);

        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        GameStatus.getInstance().setGameBoard(gameBoard);

        GameStatus.getInstance().getGameBoard().getTowerMap().put(ZoneType.GREENTOWER, testTower);

        Player testPlayer = new Player("pippo", PlayerColor.BLUE, gameBoard, testPersonalBoard, currentPlayerStatus );

        GameStatus.getInstance().setCurrentPlayer(testPlayer);

        PlayerController testController = new PlayerController();

        testController.init();

        FactoryAction.resetFloor();
    }

    @Test
    public void testInit1() {


        PlayerStatus currentPlayerStatus = new PlayerStatus(null, null);

        PersonalBoard testPersonalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        currentPlayerStatus.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        currentPlayerStatus.setPersonalBoard(testPersonalBoard);

        ArrayList<Effect> immediateEffect = new ArrayList<Effect>();

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, immediateEffect, new ArrayList<Effect>() , false, 0);

        DevelopmentCard testCard1 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        Tower testTower = new Tower(ZoneType.GREENTOWER);

        testTower.getFloor(0).setDevelopmentCard(testCard);
        testTower.getFloor(1).setDevelopmentCard(testCard1);


        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        GameStatus.getInstance().setGameBoard(gameBoard);

        GameStatus.getInstance().getGameBoard().getTowerMap().put(ZoneType.GREENTOWER, testTower);

        Player testPlayer = new Player("pippo", PlayerColor.BLUE, gameBoard, testPersonalBoard, currentPlayerStatus );

        GameStatus.getInstance().setCurrentPlayer(testPlayer);

        PlayerController testController = new PlayerController();

        testController.init();

        FactoryAction.resetFloor();
    }


    @Test
    public void testInit2() {


        PlayerStatus currentPlayerStatus = new PlayerStatus(null, null);

        PersonalBoard testPersonalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

        currentPlayerStatus.updateGoodSet(new GoodSet(100,100,100,100,100,100,100));

        currentPlayerStatus.setPersonalBoard(testPersonalBoard);

        ArrayList<Effect> immediateEffect = new ArrayList<Effect>();

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        immediateEffect.add(new ActionEffect(ZoneType.GREENTOWER, 6));

        DevelopmentCard testCard = new DevelopmentCard("test", Era.FIRST, new CardCost(), CardColor.GREEN, immediateEffect, new ArrayList<Effect>() , false, 0);

        DevelopmentCard testCard1 = new DevelopmentCard("test2", Era.FIRST, new CardCost(), CardColor.GREEN, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0);

        Tower testTower = new Tower(ZoneType.GREENTOWER);

        testTower.getFloor(0).setDevelopmentCard(testCard);
        testTower.getFloor(1).setDevelopmentCard(testCard1);


        ExcommunicationTile tile1 = new ExcommunicationTile(Era.FIRST, "name1", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom1");
        ExcommunicationTile tile2 = new ExcommunicationTile(Era.FIRST, "name2", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom2");
        ExcommunicationTile tile3 = new ExcommunicationTile(Era.FIRST, "name3", new BonusAndMalusOnAction(ZoneType.BLUETOWER, 3), new BonusAndMalusOnGoods(new GoodSet()), "descriptiom3");

        GameBoard gameBoard = new GameBoard(4);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tile1, tile2, tile3);

        GameStatus.getInstance().setGameBoard(gameBoard);

        GameStatus.getInstance().getGameBoard().getTowerMap().put(ZoneType.GREENTOWER, testTower);

        Player testPlayer = new Player("pippo", PlayerColor.BLUE, gameBoard, testPersonalBoard, currentPlayerStatus );

        GameStatus.getInstance().setCurrentPlayer(testPlayer);

        PlayerController testController = new PlayerController();

        testController.init();

        FactoryAction.resetFloor();
    }


}*/