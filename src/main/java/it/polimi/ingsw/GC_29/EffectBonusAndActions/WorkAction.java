package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;

/**
 * Created by AlbertoPennino on 22/05/2017.
 */

/*
public class WorkAction extends Action {

    private Workspace workspaceSelected;
    private FieldType fieldSelected;
    private HashMap<Integer,ArrayList<DevelopmentCard>> cardsForWorkers;
    private ArrayList<Effect> effectsToActivate;

    public WorkAction(FamilyPawn familyPawn,
                      ZoneType zoneType,
                      Player player,
                      Workspace workspaceSelected,
                      FieldType fieldSelected){

        super(familyPawn, zoneType, player);

        this.actionSpaceSelected = workspaceSelected.getActionspace(fieldSelected);
        this.workspaceSelected = workspaceSelected;
        this.fieldSelected = fieldSelected;
        this.cardsForWorkers = new HashMap<>();
        this.effectsToActivate = new ArrayList<>();
    }

    @Override
    public boolean isPossible(){

        return super.isPossible()
                && isFieldAccessible()
                && checkFamilyPresenceInField()
                && checkNeutralRule();
    }


    /**
     * This method checks that the first Field is occupied if the fieldSelected is the second
     * @return true if first field is either selected or occupied, false otherwise

    private boolean isFieldAccessible() {
        if (fieldSelected==FieldType.FIRST){return true;}
        if(workspaceSelected.getActionspace(fieldSelected).isOccupied()){
            return true;
        }
        return false;
    }

    /**
     * This methods checks if there is already a pawn of the same Player in the selected ActionSpace, even if the actionSpace is multiple
     * @return true if there aren't any player's pawns into the actionSpace, false otherwise

    private boolean checkFamilyPresenceInField() {

        for(Pawn pawnPresent : workspaceSelected.getActionspace(fieldSelected).getPawnPlaced().getPlayerPawns()){
            if(((FamilyPawn)pawnPresent).getType()==FamilyPawnType.BONUS){

            }
            else if(!(temporaryPawn.getPlayerColor() == pawnPresent.getPlayerColor())){
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if, when trying to add a pawn into a field,
     * there isn't a pawn of the same color into the other one
     * if there is one of the two pawns must be the neutral one;
     * @return true if the neutral rule is respected, false otherwise

    private boolean checkNeutralRule() {
        if(temporaryPawn.getType()==FamilyPawnType.NEUTRAL && temporaryPawn.getType()==FamilyPawnType.BONUS){
            return true;
        }

        FieldType otherField;
        if(fieldSelected == FieldType.FIRST){
            otherField = FieldType.SECOND;
        }
        else{
            otherField = FieldType.FIRST;
        }
        for (Pawn pawnPresent : workspaceSelected.getActionspace(otherField).getPawnPlaced().getPlayerPawns()){
            if (temporaryPawn.getPlayerColor()== pawnPresent.getPlayerColor() && ((FamilyPawn)pawnPresent).getType()!=FamilyPawnType.NEUTRAL){
                return false;
            }
        }
        return true;
    }




    @Override
    public void execute(){
        buildDifferentChoices();
        makeChoice();
        super.payWorkers();
        super.addPawn();
        activateEffects();
    }


    /**
     * This method builds different arrays of cards associated to the number of workers that
     * the player would need to pay to activate their effects,
     * the arrays are created only if the resources of the player are enough to pay hte workersNeeded

    private void buildDifferentChoices() {
        Lane lane = null;
        if(zoneType==ZoneType.HARVEST){
            lane = player.getPersonalBoard().getLane(CardColor.GREEN);
        }
        if (zoneType==ZoneType.PRODUCTION){
            lane = player.getPersonalBoard().getLane(CardColor.YELLOW);
        }

        HashMap<Integer,ArrayList<DevelopmentCard>> temporaryHash = new HashMap<>();
        HashMap<Integer,ArrayList<DevelopmentCard>> finalHash = new HashMap<>();
        int maxWorkersNeeded = 0;
        int workersNeeded;

        actionSpaceSelected.getEffect().execute(player);

        for(DevelopmentCard card : lane.getCards()){

            workersNeeded = card.getActionValue() - temporaryPawn.getActualValue();
            if (workersNeeded<= player.getActualGoodSet().getGoodAmount(GoodType.WORKERS)-workers){
                temporaryHash.get(workersNeeded).add(card);
                if (workersNeeded>maxWorkersNeeded){maxWorkersNeeded = workersNeeded;}
            }
        }
        workersNeeded = 0;
        while(workersNeeded<=maxWorkersNeeded){

            finalHash.put(workersNeeded,finalHash.get(max(workersNeeded-1,0)));
            for (DevelopmentCard cardPresent : temporaryHash.get(workersNeeded)){
                finalHash.get(workersNeeded).add(cardPresent);
            }

            workersNeeded=workersNeeded+1;
        }
        cardsForWorkers = finalHash;
    }

    /**
     * This method first asks the player how many workers does he want to spend,
     * then asks the player which effects he wants to activate for each card in case of alternatives,
     * only if the player wants activate the card at all.
     * The method generates then a list of effects that need to be activated

    private void makeChoice() {
        //TODO: filtraggio sul costo in workers una volta ottenuto
        int choice = askForWorkers();
        setWorkers(workers + choice);
        ArrayList<DevelopmentCard> cardsChosen = cardsForWorkers.get(choice);

        Effect effectChosen;
        for (DevelopmentCard card : cardsChosen){

            if (!card.getPermanentEffect().isEmpty()) {

                if (zoneType==ZoneType.PRODUCTION) {
                    boolean ask = true;
                    for(Effect effect: card.getPermanentEffect()) {
                        if (!(effect.getClass().getName() == "PayToObtainEffect")){
                            ask = false;
                        }
                    }

                    if (ask) {
                        if (askForCardActivation(card)){
                                if(card.getPermanentEffect().size()>1) {
                                    effectChosen = askForEffect(card);
                                }
                                else{
                                    effectChosen = card.getPermanentEffect().get(0);
                                }
                                effectsToActivate.add(effectChosen);
                        }
                    }
                }
                else{
                    for(Effect effect : card.getPermanentEffect()){
                        effectsToActivate.add(effect);
                    }
                }
            }
        }
    }



    //Solo per testing, sarà poi nello speaker
    private boolean askForCardActivation(DevelopmentCard card) {
        /*Scanner in = new Scanner(System.in);
        System.out.println("Vuoi attivare questa carta: "+ card +"\n?");
        System.out.println("Digita y o n!");
        String answer = in.nextLine();
        if ("y".equals(answer)){
            return true;
        }
        return false;
    }


    //Solo per testing, sarà poi nello speaker
    private Effect askForEffect(DevelopmentCard card) {
       /* Scanner in = new Scanner(System.in);
        System.out.println("La carta offre diverse possibilità: "+ card.getPermanentEffect());
        System.out.println("Digita il numero dell'effetto desiderato!");
        int answer = (in.nextInt())-1;
        return card.getPermanentEffect().get(answer);
        return card.getPermanentEffect().get(3);

    }

    //Solo per testing, sarà poi nello speaker
    private int askForWorkers() {
        /*Scanner in = new Scanner(System.in);
        System.out.println("A seconda del numero di workers che aggiungerai alla tua azione" +
                "\n" + "ti sarà possibile attivare diversi effetti: \n" + cardsForWorkers);
        System.out.println("Digita il numero di workers che vuoi aggiungere!");
        int answer = (in.nextInt());
        return answer;
        return 0;
    }


    /**
     * This method activates all the effects selected by the player

    private void activateEffects() {

        GoodSet goodSet= null;
        if (zoneType==ZoneType.HARVEST) {
            player.getPersonalBoard().getBonusTile().getHarvestEffect().execute(player);
        }
        if (zoneType==ZoneType.PRODUCTION) {
            player.getPersonalBoard().getBonusTile().getProductionEffect().execute(player);
        }

        for(Effect effect : effectsToActivate){
            effect.execute(player);
        }
    }



}*/

