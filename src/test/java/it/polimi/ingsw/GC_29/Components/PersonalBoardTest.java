package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import org.junit.Test;

/**
 * Created by AlbertoPennino on 06/07/2017.
 */
public class PersonalBoardTest {
    @Test
    public void TestToTable(){

        ObtainEffect testEffect = new ObtainEffect(4,2,0,0,5,0,1);
        BonusTile bonusTile = new BonusTile(testEffect,testEffect);

        DevelopmentCard card = new DevelopmentCard("ciao",Era.FIRST,null,CardColor.BLUE,null,null,true,5);

        PersonalBoard personalBoard = new PersonalBoard(bonusTile,6);
        personalBoard.getLane(CardColor.BLUE).addCard(card);
        personalBoard.getLane(CardColor.GREEN).addCard(card);
        personalBoard.getLane(CardColor.YELLOW).addCard(card);
        personalBoard.getLane(CardColor.PURPLE).addCard(card);

        System.out.print(personalBoard.toTable());

    }
}
