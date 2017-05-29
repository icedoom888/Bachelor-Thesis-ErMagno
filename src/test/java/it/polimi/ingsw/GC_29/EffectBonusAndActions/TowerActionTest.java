package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.Player;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.GC_29.Player.PlayerColor.*;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class TowerActionTest {

    @Test
    public void testIsPossible1() throws Exception {

        // Creation of the gameboard

        int numberOfPlayers1 = 4;
        ExcommunicationTile tile_1 = new ExcommunicationTile(Era.FIRST,"sei",null,null,"777");
        ExcommunicationTile tile_2 = new ExcommunicationTile(Era.SECOND,"un",null,null,"su ogni");
        ExcommunicationTile tile_3 = new ExcommunicationTile(Era.THIRD,"bufu",null,null,"cosa");
        GameBoard gameBoard = new GameBoard(numberOfPlayers1,tile_1,tile_2,tile_3);

        // Creation of personalBoard1

        BonusTile bonusTile = new BonusTile(new ObtainEffect(1,0,0,0,0,0,0),new ObtainEffect(0,1,0,0,0,0,0));
        PersonalBoard personalBoard1 = new PersonalBoard(bonusTile,6);

        // Creation of playerStatus1

        PlayerStatus playerStatus1 = new PlayerStatus(PlayerColor.BLUE, personalBoard1);
        Player player1 = new Player("Player1", PlayerColor.BLUE, gameBoard, personalBoard1, playerStatus1);
        playerStatus1.getActualGoodSet().addGoodSet(new GoodSet(10,10,10,10,10,10,10));

        // Creation of the TowerAction

        FamilyPawn familyPawnSelected = new FamilyPawn(PlayerColor.BLUE, FamilyPawnType.BLACK, 3);

        ArrayList<Effect> immediateEffectsBlueCard = new ArrayList<Effect>();
        ActionEffect purpleSix = new ActionEffect(ZoneType.PURPLETOWER, 6);
        CouncilPrivilegeEffect councilPrivilegeEffect = new CouncilPrivilegeEffect(1);

        immediateEffectsBlueCard.add(purpleSix);
        immediateEffectsBlueCard.add(councilPrivilegeEffect);

        ArrayList<Effect> permanentEffectsBlueCard = new ArrayList<Effect>();
        BonusAndMalusOnAction bonusAndMalusOnAction = new BonusAndMalusOnAction(ZoneType.BLUETOWER, 2);
        BonusAndMalusOnCost bonusAndMalusOnCost = new BonusAndMalusOnCost(ZoneType.BLUETOWER, new GoodSet(1,0,0,0,0,0,0), new GoodSet(0,1,0,0,0,0,0), true);
        BonusEffect bonusEffect = new BonusEffect(bonusAndMalusOnAction, null, bonusAndMalusOnCost);
        permanentEffectsBlueCard.add(bonusEffect);

        playerStatus1.getBonusAndMalusOnCost().add(bonusAndMalusOnCost);

        DevelopmentCard blueCard = new DevelopmentCard("Costruttore Fake",
                Era.FIRST,
                new CardCost(true, true,
                        new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet(1,1,1,1,1,1,1)),
                        new Cost(new GoodSet(0,0,4,0,0,0,0), new GoodSet(1,1,1,1,1,1,1))),
                CardColor.BLUE,
                immediateEffectsBlueCard,
                permanentEffectsBlueCard,
                false,
                0);

        gameBoard.getTower(ZoneType.BLUETOWER).getFloor(3).setDevelopmentCard(blueCard);
        //gameBoard.getTower(ZoneType.BLUETOWER)

        TowerAction towerAction = new TowerAction(familyPawnSelected, ZoneType.BLUETOWER, playerStatus1, 3, gameBoard.getTower(ZoneType.BLUETOWER));

        System.out.println("The player has to pay: \n" +
                "cost of the tower: " + towerAction.getTowerCost() + "\n" +
                "cost of the card: " + towerAction.getCardSelected().getCardCost() + "\n\n" +
                "The player has in his actual goodset: " + towerAction.getPlayerStatus().getActualGoodSet().toString() +"\n" +
                "The value of the action is: " + towerAction.getActionSpaceSelected().getActionCost() + "\n" +
                "The value of the pawnSelected is: " + towerAction.getTemporaryPawn().getActualValue() + "\n" +
                "The number of workers of the player is: " + playerStatus1.getActualGoodSet().getGoodAmount(GoodType.WORKERS));

        System.out.println(towerAction.isPossible());
        System.out.println("Workers to pay: " + towerAction.getWorkers());
        System.out.println("Value of the pawn after BM: " + towerAction.getTemporaryPawn().getActualValue());
        System.out.println(towerAction.getPossibleCardCosts());

        return;
    }

    @Test
    public void testUpdate() throws Exception {
    }

    @Test
    public void testExecute() throws Exception {

    }

    @Test
    public void testIsPossible() throws Exception {
        FamilyPawn familyPawnBlueOrange = new FamilyPawn(BLUE, FamilyPawnType.ORANGE, 3);
        FamilyPawn familyPawnRedBlack = new FamilyPawn(GREEN, FamilyPawnType.BLACK, 3);
        ZoneType zoneType = ZoneType.BLUETOWER;
        boolean realAction = true;
        PlayerStatus playerStatus = new PlayerStatus(PlayerColor.BLUE, new ArrayList<BonusAndMalusOnAction>(), new ArrayList<BonusAndMalusOnGoods>(), null, new GoodSet(1,2,3,4,5,6,7), new HashMap<CardColor, Integer>(), true, true,true,true);
        Tower tower = new Tower(ZoneType.BLUETOWER);
        int floorIndex = 2;
        tower.getFloors()[floorIndex].setDevelopmentCard(new DevelopmentCard("a", Era.FIRST, new CardCost(false, true, new Cost(new GoodSet(4,0,0,0,0,0,0), new GoodSet()), new Cost(new GoodSet(), new GoodSet())), CardColor.BLUE, new ArrayList<Effect>(), new ArrayList<Effect>(), false, 0));
        TowerAction towerAction = new TowerAction(familyPawnBlueOrange, zoneType, playerStatus, floorIndex);

        tower.getFloor(floorIndex-1).getActionSpace().addPawn(familyPawnRedBlack);

        System.out.println(towerAction.isPossible());
    }

}