package it.polimi.ingsw.GC_29.EffectBonusAndActions;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.GameStatus;

import java.util.*;

import static java.lang.Math.max;

/**
 * Created by AlbertoPennino on 22/05/2017.
 */

public class WorkAction extends Action {

    private Workspace workspaceSelected;
    private FieldType fieldSelected;
    private Map<Integer,ArrayList<DevelopmentCard>> cardsForWorkers;
    private ArrayList<Effect> effectsToActivate;
    private Map<String,DevelopmentCard> payToObtainCardsMap;


    public WorkAction(ZoneType zoneType,
                      GameStatus gameStatus,
                      FieldType fieldSelected){

        super(zoneType, gameStatus);
        this.workspaceSelected = this.gameStatus.getGameBoard().getWorkArea(zoneType);

        this.actionSpaceSelected = workspaceSelected.getActionSpace(fieldSelected);
        this.fieldSelected = fieldSelected;
        this.cardsForWorkers = new HashMap<>();
        this.effectsToActivate = new ArrayList<>();
        this.payToObtainCardsMap = new HashMap<>();
    }

    @Override
    public boolean isPossible(){

        return super.isPossible()
                && isActionSpaceAccessible()
                && isFieldAccessible()
                && checkFamilyPresenceInField()
                && checkNeutralRule();
    }

    @Override
    public void execute() {

        super.payWorkers();
        super.addPawn();
        activateEffects();
        super.update();

    }

    private boolean isActionSpaceAccessible() {
        if(actionSpaceSelected.isOccupied() && actionSpaceSelected.isSingle()){
           System.out.println("The field is full!");
           return false;
        }
        return true;
    }


    /**
     * This method checks that the first Field is occupied if the fieldSelected is the second
     * @return true if first field is either selected or occupied, false otherwise
     */
    private boolean isFieldAccessible() {
        if (fieldSelected==FieldType.FIRST){
            System.out.println("You can place here");
            return true;
        }
        FieldType other = FieldType.FIRST;
        if(workspaceSelected.getActionSpace(other).isOccupied()){
            System.out.println("The first field is free");
            return true;
        }
        System.out.println("The first field is empty!");
        return false;
    }

    /**
     * This methods checks if there is already a pawn of the same Player in the selected ActionSpace, even if the actionSpace is multiple
     * @return true if there aren't any player's pawns into the actionSpace, false otherwise
     */
    private boolean checkFamilyPresenceInField() {

        for(Pawn pawnPresent : workspaceSelected.getActionSpace(fieldSelected).getPawnPlaced().getPlayerPawns()){
            if(((FamilyPawn)pawnPresent).getType()==FamilyPawnType.BONUS){

            }
            else if(!(temporaryPawn.getPlayerColor() == pawnPresent.getPlayerColor())){
                System.out.println("The Field has a "+temporaryPawn.getPlayerColor()+" pawns already!");
                return false;
            }
        }
        System.out.println("The Field is free of "+temporaryPawn.getPlayerColor()+" pawns");
        return true;
    }

    /**
     * This method checks if, when trying to add a pawn into a field,
     * there isn't a pawn of the same color into the other one
     * if there is one of the two pawns must be the neutral one;
     * @return true if the neutral rule is respected, false otherwise
     */
    private boolean checkNeutralRule() {

        if (gameStatus.getTurnOrder().size() < 4) return true;

        if(temporaryPawn.getType()==FamilyPawnType.NEUTRAL){
            System.out.println("The neutral rule is respected");
            return true;
        }

        FieldType otherField;
        if(fieldSelected == FieldType.FIRST){
            otherField = FieldType.SECOND;
        }
        else{
            otherField = FieldType.FIRST;
        }

        for (Pawn pawnPresent : workspaceSelected.getActionSpace(otherField).getPawnPlaced().getPlayerPawns()){
            if (temporaryPawn.getPlayerColor()== pawnPresent.getPlayerColor() && ((FamilyPawn)pawnPresent).getType()!=FamilyPawnType.NEUTRAL){
                System.out.println("The neutral rule is NOT respected");
                return false;
            }
        }
        System.out.println("The neutral rule is respected");
        return true;
    }


    public void buildDifferentChoices() {

        payWorkers();
        workers = 0;

        activateBonusTile();

        Lane lane = new Lane(6);

        if(zoneType == ZoneType.PRODUCTION){

            lane = player.getPersonalBoard().getLane(CardColor.YELLOW);

        }

        if (zoneType == ZoneType.HARVEST){

            lane = player.getPersonalBoard().getLane(CardColor.GREEN);
        }

        for (DevelopmentCard card : lane.getCards()) {

            if(card==null){
                break;
            }

            int workersNeeded = card.getActionValue() - temporaryPawn.getActualValue();

            if(workersNeeded < player.getActualGoodSet().getGoodAmount(GoodType.WORKERS)){

                workersNeeded = max(0, workersNeeded);
            }

            if(!(cardsForWorkers.containsKey(workersNeeded))){

                cardsForWorkers.put(workersNeeded, new ArrayList<>());
                cardsForWorkers.get(workersNeeded).add(card);
            }

            else {

                cardsForWorkers.get(workersNeeded).add(card);
            }

        }

        recollectCardsForWorkers();

    }

    private void recollectCardsForWorkers() {

        Set<Integer> workersSet = cardsForWorkers.keySet();

        ArrayList<Integer> workersList = new ArrayList<Integer>(Arrays.asList(workersSet.toArray(new Integer[workersSet.size()])));

        Collections.sort(workersList);

        for (int index = 0; index < workersList.size() - 1; index++) {

            int workersKey = workersList.get(index);
            int nextWorkersKey = workersList.get(index + 1);

            ArrayList<DevelopmentCard> developmentCards = cardsForWorkers.get(workersKey);

            for (DevelopmentCard developmentCard : developmentCards) {

                cardsForWorkers.get(nextWorkersKey).add(developmentCard);
            }
        }
    }


    /**
     * This method builds different arrays of cards associated to the number of workers that
     * the player would need to pay to activate their effects,
     * the arrays are created only if the resources of the player are enough to pay hte workersNeeded

    public void buildDifferentChoices() throws Exception {

        activateBonusTile();

        Lane lane = null;

        if(zoneType==ZoneType.HARVEST){
            lane = player.getPersonalBoard().getTerritoryLane();
        }

        if (zoneType==ZoneType.PRODUCTION){
            lane = player.getPersonalBoard().getLane(CardColor.YELLOW);
        }

        HashMap<Integer,ArrayList<DevelopmentCard>> temporaryHash = new HashMap<>();

        HashMap<Integer,ArrayList<DevelopmentCard>> finalHash = new HashMap<>();

        int maxWorkersNeeded = 0;

        int workersNeeded = 0;

        if(actionSpaceSelected.getEffect()!= null) {

            actionSpaceSelected.getEffect().execute(player);
        }

        if(lane.getFirstFreeSlotIndex()!=0){

            for (DevelopmentCard card : lane.getCards()) {

                if(card==null){
                    break;
                }

                System.out.println("IL VALORE DELLA CARTA E' " + card.getActionValue() + "\n");
                System.out.println("IL VALORE DELLA PAWN E' " + temporaryPawn.getActualValue()+ "\n");
                System.out.println("IL VALORE DEI WORKERS E' " + workers);



                workersNeeded = card.getActionValue() - temporaryPawn.getActualValue() - workers;

                if (workersNeeded < 0) workersNeeded = 0;

                if (workersNeeded <= player.getActualGoodSet().getGoodAmount(GoodType.WORKERS) - workers) {

                    if (temporaryHash.get(workersNeeded) == null) {
                        ArrayList<DevelopmentCard> templist = new ArrayList<>();
                        templist.add(card);
                        temporaryHash.put(workersNeeded, templist);
                    }

                    else {
                        temporaryHash.get(workersNeeded).add(card);
                    }

                    if (workersNeeded > maxWorkersNeeded) {
                        maxWorkersNeeded = workersNeeded;
                    }
                }

            }

            workersNeeded = 0;

            while (workersNeeded <= maxWorkersNeeded) {

                finalHash.put(workersNeeded,new ArrayList<>());

                if (workersNeeded!=0) {

                    ArrayList<DevelopmentCard> temp = new ArrayList<>(finalHash.get(workersNeeded-1));

                    finalHash.put(workersNeeded, temp);
                }

                if (temporaryHash.get(workersNeeded)!=null) {

                    for (DevelopmentCard cardPresent : temporaryHash.get(workersNeeded)) {

                        if(cardPresent==null){
                            break;
                        }

                        finalHash.get(workersNeeded).add(cardPresent);
                    }
                }

                workersNeeded = workersNeeded + 1;
            }
        }

        System.out.println("WORKERS ALLA FINE CHE SERVONO " + workersNeeded);
        cardsForWorkers = finalHash;

    }*/


    public Boolean handlePayToObtainCards(int workersChosen){

        setWorkers(workers + workersChosen);

        System.out.println("DENTRO HANDLE PAY CARDS, NUMERO WORKERS " + workersChosen + "\n");



        List<DevelopmentCard> cardsToActivateList = cardsForWorkers.get(workersChosen);

        Boolean isPayToObtain = false;

        for (DevelopmentCard card : cardsToActivateList) {



            String cardKey = card.getSpecial();

            for(Effect effect : card.getPermanentEffect()){

                if(effect instanceof PayToObtainEffect){

                    PayToObtainEffect effect1 = (PayToObtainEffect)effect;

                    /**
                     * TODO: IN QUESTO MODO NON AGGIUNGE LA CARTA NELLE RICHIESTE DA ATTIVARE
                     * però nella richiesta dei workers per l'attivazione delle carte questa carta è ancora visibile
                     * SISTEMARE...
                     */


                    if(effect1.checkSufficientGoods(player)){

                        System.out.println("LA CARTA AGGIIUNTA NELLA PAY TO OBTAIN MAP E' " + cardKey);
                        payToObtainCardsMap.put(cardKey, card);
                        isPayToObtain = true;
                    }

                }

                else{

                    effectsToActivate.add(effect);
                }

            }
        }

        return  isPayToObtain;

    }

    /**
     * This method first asks the player how many workers does he want to spend,
     * then asks the player which effects he wants to activate for each card in case of alternatives,
     * only if the player wants activate the card at all.
     * The method generates then a list of effects that need to be activated

    private void makeChoice() {
        //TODO: filtraggio sul costo in workers una volta ottenuto
        int choice;
        //choice = askForWorkers();
        //
        choice=0;
        setWorkers(workers + choice);
        ArrayList<DevelopmentCard> cardsChosen = cardsForWorkers.get(choice);

        Effect effectChosen;
        for (DevelopmentCard card : cardsChosen){
            if (card==null){
                break;
            }

            if (!card.getPermanentEffect().isEmpty()) {

                if (zoneType==ZoneType.PRODUCTION) {
                    boolean ask = true;
                    for(Effect effect : card.getPermanentEffect()) {

                        System.out.println(effect.getClass().getName());

                        if(!(effect instanceof PayToObtainEffect)){ // TODO: rifare, ne basta solo uno per chiedere
                        //if (!(effect.getClass().getSpecial()=="it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect")){
                            ask = false;
                        }
                    }

                    if (ask) {
                        if (/*askForCardActivation(card)true){
                                if(card.getPermanentEffect().size()>1) {
                                    //effectChosen = askForEffect(card);
                                    effectChosen=card.getPermanentEffect().get(0);
                                }
                                else{
                                    effectChosen = card.getPermanentEffect().get(0);
                                }
                                effectsToActivate.add(effectChosen);
                        }
                    }
                }
                else{

                    effectsToActivate.addAll(card.getPermanentEffect());

                }
            }
        }
        System.out.println();
        System.out.println(effectsToActivate);

    }*/



    /*
    //Solo per testing, sarà poi nello speaker
    private boolean askForCardActivation(DevelopmentCard card) {
        Scanner in = new Scanner(System.in);
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
        Scanner in = new Scanner(System.in);
        System.out.println("La carta offre diverse possibilità: "+ card.getPermanentEffect());
        System.out.println("Digita il numero dell'effetto desiderato!");
        int answer = (in.nextInt())-1;
        return card.getPermanentEffect().get(answer);

    }

    //Solo per testing, sarà poi nello speaker
    private int askForWorkers() {
        System.out.println("A seconda del numero di workers che aggiungerai alla tua azione" +
                "\n" + "ti sarà possibile attivare diversi effetti: \n" + cardsForWorkers+"\n");
        System.out.println("Digita il numero di workers che vuoi aggiungere! \n");
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        return answer;
    }

    */
    /**
     * This method activates all the effects selected by the player
     */
    private void activateEffects() {

        System.out.println("activate effects");

        if(!effectsToActivate.isEmpty()){

            System.out.println("effectsToActivate not empty");

            for(Effect effect : effectsToActivate){

                System.out.println(effect.getClass());

                effect.execute(player);
            }
        }
    }

    private void activateBonusTile() {

        player.getPersonalBoard().getBonusTile().getEffect(zoneType).execute(player);


    }

    public Map<Integer, ArrayList<DevelopmentCard>> getCardsForWorkers() {
        return cardsForWorkers;
    }

    public Map<String , DevelopmentCard> getPayToObtainCardsMap() {
        return payToObtainCardsMap;
    }



    public void setPayToObtainCardsChosen(Map<String,Integer> payToObtainCardsChosen) {

        Set<String> cardMapKeys = payToObtainCardsChosen.keySet();

        for(String cardKey : cardMapKeys){

            if(payToObtainCardsMap.containsKey(cardKey)){

                DevelopmentCard card = payToObtainCardsMap.get(cardKey);

                effectsToActivate.add(card.getPermanentEffect().get(payToObtainCardsChosen.get(cardKey)));
            }

            else{

                throw new IllegalArgumentException("Illegal card key" + cardKey);
            }
        }

        System.out.println("WORKACTION - effects to activate: " + effectsToActivate);
    }

    public boolean handlePayToObtainCards() {
        return handlePayToObtainCards(workers);
    }
}

