package it.polimi.ingsw.GC_29.Model;


import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzotara on 17/05/17.
 */
public class DevelopmentCard {

    private String special;
    private Era era;
    private CardCost cardCost;
    private CardColor color;
    private ArrayList<Effect> immediateEffect;
    private ArrayList<Effect> permanentEffect;
    private boolean withActionValue;
    private int actionValue;


    public DevelopmentCard(
            String special,
            Era era,
            CardCost cardCost,
            CardColor color,
            ArrayList<Effect> immediateEffect,
            ArrayList<Effect> permanentEffect,
            boolean withActionValue,
            int actionValue) {

        this.special = special;
        this.era = era;
        this.cardCost = cardCost;
        this.color = color;
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
        this.withActionValue = withActionValue;
        this.actionValue = actionValue;
    }

    public String getSpecial() {
        return special;
    }

    public Era getEra() {
        return era;
    }

    public CardCost getCardCost() { // immutable object
        return new CardCost(cardCost);
    }

    public CardColor getColor() {
        return color;
    }

    public List<Effect> getImmediateEffect() {
        return immediateEffect;
    }

    public List<Effect> getPermanentEffect() {
        return permanentEffect;
    }

    public boolean isWithActionValue() {
        return withActionValue;
    }

    public int getActionValue() {
        return actionValue;
    }

    @Override
    public String toString() {

        String returnString = "DevelopmentCard{ \n"
                + "special = '" + special + "'\n"
                + "era = " + era + "\n"
                + "color = " + color + "\n"
                + "cardCost = " + cardCost + "\n"
                + "immediateEffect = " + immediateEffect + "\n"
                + "permanentEffect = " + permanentEffect + "\n";

        if (withActionValue) {

        returnString =  returnString
                        + "actionValue = " + actionValue + "\n"
                        + '}';
        }

        return returnString;

    }

    public String toTable() {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("Name: " + special, "Era: " + era);
        asciiTable.addRule();
        asciiTable.addRow( "Color: " + color, "Action Value: " + actionValue);
        asciiTable.addRule();
        String stringImm = "Immediate Effects: \n";
        for (Effect effect : immediateEffect) {
            stringImm += effect.toString()+"\n";
        }
        String stringPer = "Permanent Effects: \n";
        for (Effect effect : permanentEffect) {
            stringPer += effect.toString()+"\n";
        }
        asciiTable.addRow(stringImm, stringPer);
        asciiTable.addRule();
        asciiTable.addRow("Main Cost: " + cardCost.getMainCost().getCost(), "Necessary Resources: " + cardCost.getMainCost().getNecessaryResources());
        asciiTable.addRow("Alternative Cost: " + cardCost.getAlternativeCost().getCost(), "Necessary Resources: " + cardCost.getAlternativeCost().getNecessaryResources());
        asciiTable.addRule();
        asciiTable.setTextAlignment(TextAlignment.CENTER);
        String rend = asciiTable.render();
        return rend;
    }

    /*@Override
    public String toString() {
        AsciiArtTable devCard = new AsciiArtTable();
        devCard.addHeadline(special);
        devCard.addHeaderCols("Era", "Color");
        ArrayList<String> row = new ArrayList<>();
        row.add(era.toString());
        row.add(color.toString());
        if (cardCost.isWithPrice())  {
            devCard.addHeaderCols("Cost");
            row.add(cardCost.getMainCost().getCost().toString());
        }
        if (cardCost.isAlternative()) {
            devCard.addHeaderCols("Alternative Cost");
            row.add(cardCost.getAlternativeCost().getCost().toString());
        }
        if (!immediateEffect.isEmpty()) {
            devCard.addHeaderCols("Immediate Effects");
            String string = null;
            for (Effect effect : immediateEffect) {
                string += effect.toString();
            }
            row.add(string);
        }
        if (!permanentEffect.isEmpty()) {
            devCard.addHeaderCols("Permanent Effects");
            String string = "";
            for (Effect effect : permanentEffect) {
                string += effect.toString();
            }
            row.add(string);
        }
        if (withActionValue) {
            devCard.addHeaderCols("ActionValue");
            row.add(Integer.toString(actionValue));
        }
        devCard.add(row);

        devCard.print(System.out);


        return devCard.getOutput();
    }*/


}