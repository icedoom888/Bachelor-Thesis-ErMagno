package it.polimi.ingsw.GC_29.Client.GUI.GameBoard;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.GUI.CardsChange;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Client.ClientSocket.ClientOutHandlerGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class GameBoardController {

    private HashMap<Integer, ImageView> personalGreenCards = new HashMap<>();
    private HashMap<Integer, ImageView> personalBlueCards = new HashMap<>();
    private HashMap<Integer, ImageView> personalYellowCards = new HashMap<>();
    private HashMap<Integer, ImageView> personalPurpleCards = new HashMap<>();

    private HashMap<Integer, ImageView> greenTower = new HashMap<>();
    private HashMap<Integer, ImageView> blueTower = new HashMap<>();
    private HashMap<Integer, ImageView> yellowTower = new HashMap<>();
    private HashMap<Integer, ImageView> purpleTower = new HashMap<>();

    //quando devo settare i pulsanti attivi
    private HashMap<Integer, ImageView> actionButtons = new HashMap<>();
    private HashMap<Integer,GridPane> actionGrid = new HashMap<>();
    private HashMap<Integer,HBox> actionBox = new HashMap<>();

    //quando devo sapere a che azione corrisponde il pulsante premuto
    private HashMap<ImageView, Integer> buttonAction = new HashMap<>();
    private HashMap<GridPane,Integer> gridAction = new HashMap<>();
    private HashMap<HBox,Integer> boxAction = new HashMap<>();


    private HashMap<GoodType, Text> resourceAmount = new HashMap<>();
    private ClientOutHandlerGUI view = new ClientOutHandlerGUI();
    private HashMap<Integer,ImageView> coverImages = new HashMap<>();


    //ChooseDistribution, classe che serve per parlare con il server
    private ChooseDistribution chooseDistribution;


    //Pulsanti per scegliere le pedine
    @FXML
    private ImageView whitePawn;
    @FXML
    private ImageView orangePawn;
    @FXML
    private ImageView balckPawn;
    @FXML
    private ImageView neutralPawn;

    // Valore pedine
    @FXML
    private Text whiteValue;
    @FXML
    private Text orangeValue;
    @FXML
    private Text blackValue;
    @FXML
    private Text neutralValue;

    //Pulsanti per attivare i leader
    @FXML
    private Button activate1;
    @FXML
    private Button activate2;
    @FXML
    private Button activate3;
    @FXML
    private Button activate4;

    //Pulsanti per scartare una carta leader
    @FXML
    private Button burn1;
    @FXML
    private Button burn2;
    @FXML
    private Button burn3;
    @FXML
    private Button burn4;

    @FXML
    private Button throwDices;
    @FXML
    private Button skipAction;
    @FXML
    private Button endTurn;

    //Spazi immagine carte PersonalBoard
    @FXML
    private ImageView greenCard1;
    @FXML
    private ImageView greenCard2;
    @FXML
    private ImageView greenCard3;
    @FXML
    private ImageView greenCard4;
    @FXML
    private ImageView greenCard5;
    @FXML
    private ImageView greenCard6;

    @FXML
    private ImageView blueCard1;
    @FXML
    private ImageView blueCard2;
    @FXML
    private ImageView blueCard3;
    @FXML
    private ImageView blueCard4;
    @FXML
    private ImageView blueCard5;
    @FXML
    private ImageView blueCard6;

    @FXML
    private ImageView yellowCard1;
    @FXML
    private ImageView yellowCard2;
    @FXML
    private ImageView yellowCard3;
    @FXML
    private ImageView yellowCard4;
    @FXML
    private ImageView yellowCard5;
    @FXML
    private ImageView yellowCard6;

    @FXML
    private ImageView purpleCard1;
    @FXML
    private ImageView purpleCard2;
    @FXML
    private ImageView purpleCard3;
    @FXML
    private ImageView purpleCard4;
    @FXML
    private ImageView purpleCard5;
    @FXML
    private ImageView purpleCard6;

    //Contatori per GoodSet
    @FXML
    private Text gold;
    @FXML
    private Text stone;
    @FXML
    private Text wood;
    @FXML
    private Text workers;

    //Aree immagini per carte
    @FXML
    private ImageView greenTowerCard1;
    @FXML
    private ImageView greenTowerCard2;
    @FXML
    private ImageView greenTowerCard3;
    @FXML
    private ImageView greenTowerCard4;
    @FXML
    private ImageView blueTowerCard1;
    @FXML
    private ImageView blueTowerCard2;
    @FXML
    private ImageView blueTowerCard3;
    @FXML
    private ImageView blueTowerCard4;
    @FXML
    private ImageView yellowTowerCard1;
    @FXML
    private ImageView yellowTowerCard2;
    @FXML
    private ImageView yellowTowerCard3;
    @FXML
    private ImageView yellowTowerCard4;
    @FXML
    private ImageView purpleTowerCard1;
    @FXML
    private ImageView purpleTowerCard2;
    @FXML
    private ImageView purpleTowerCard3;
    @FXML
    private ImageView purpleTowerCard4;

    //Aree azioni
    @FXML
    private ImageView greenTowerAction1;
    @FXML
    private ImageView greenTowerAction2;
    @FXML
    private ImageView greenTowerAction3;
    @FXML
    private ImageView greenTowerAction4;
    @FXML
    private ImageView blueTowerAction1;
    @FXML
    private ImageView blueTowerAction2;
    @FXML
    private ImageView blueTowerAction3;
    @FXML
    private ImageView blueTowerAction4;
    @FXML
    private ImageView yellowTowerAction1;
    @FXML
    private ImageView yellowTowerAction2;
    @FXML
    private ImageView yellowTowerAction3;
    @FXML
    private ImageView yellowTowerAction4;
    @FXML
    private ImageView purpleTowerAction1;
    @FXML
    private ImageView purpleTowerAction2;
    @FXML
    private ImageView purpleTowerAction3;
    @FXML
    private ImageView purpleTowerAction4;
    @FXML
    private GridPane councilPalace;
    @FXML
    private ImageView harvest1;
    @FXML
    private HBox harvest2;
    @FXML
    private ImageView production1;
    @FXML
    private HBox production2;
    @FXML
    private ImageView market1;
    @FXML
    private ImageView market2;
    @FXML
    private ImageView market3;
    @FXML
    private ImageView market4;



    @FXML
    private ImageView greenTowerAction1Cover;
    @FXML
    private ImageView greenTowerAction2Cover;
    @FXML
    private ImageView greenTowerAction3Cover;
    @FXML
    private ImageView greenTowerAction4Cover;
    @FXML
    private ImageView blueTowerAction1Cover;
    @FXML
    private ImageView blueTowerAction2Cover;
    @FXML
    private ImageView blueTowerAction3Cover;
    @FXML
    private ImageView blueTowerAction4Cover;
    @FXML
    private ImageView yellowTowerAction1Cover;
    @FXML
    private ImageView yellowTowerAction2Cover;
    @FXML
    private ImageView yellowTowerAction3Cover;
    @FXML
    private ImageView yellowTowerAction4Cover;
    @FXML
    private ImageView purpleTowerAction1Cover;
    @FXML
    private ImageView purpleTowerAction2Cover;
    @FXML
    private ImageView purpleTowerAction3Cover;
    @FXML
    private ImageView purpleTowerAction4Cover;
    @FXML
    private ImageView councilPalaceCover;
    @FXML
    private ImageView harvest1Cover;
    @FXML
    private ImageView harvest2Cover;
    @FXML
    private ImageView production1Cover;
    @FXML
    private ImageView production2Cover;
    @FXML
    private ImageView market1Cover;
    @FXML
    private ImageView market2Cover;
    @FXML
    private ImageView market3Cover;
    @FXML
    private ImageView market4Cover;



    @FXML
    public void initialize(){

        personalGreenCards.put(0,greenCard1);
        personalGreenCards.put(1,greenCard2);
        personalGreenCards.put(2,greenCard3);
        personalGreenCards.put(3,greenCard4);
        personalGreenCards.put(4,greenCard5);
        personalGreenCards.put(5,greenCard6);

        personalBlueCards.put(0,blueCard1);
        personalBlueCards.put(1,blueCard2);
        personalBlueCards.put(2,blueCard3);
        personalBlueCards.put(3,blueCard4);
        personalBlueCards.put(4,blueCard5);
        personalBlueCards.put(5,blueCard6);

        personalYellowCards.put(0,yellowCard1);
        personalYellowCards.put(1,yellowCard2);
        personalYellowCards.put(2,yellowCard3);
        personalYellowCards.put(3,yellowCard4);
        personalYellowCards.put(4,yellowCard5);
        personalYellowCards.put(5,yellowCard6);

        personalPurpleCards.put(0,purpleCard1);
        personalPurpleCards.put(1,purpleCard2);
        personalPurpleCards.put(2,purpleCard3);
        personalPurpleCards.put(3,purpleCard4);
        personalPurpleCards.put(4,purpleCard5);
        personalPurpleCards.put(5,purpleCard6);

        resourceAmount.put(GoodType.COINS,gold);
        resourceAmount.put(GoodType.STONE,stone);
        resourceAmount.put(GoodType.WOOD,wood);
        resourceAmount.put(GoodType.WORKERS,workers);

        greenTower.put(0,greenTowerCard1);
        greenTower.put(1,greenTowerCard2);
        greenTower.put(2,greenTowerCard3);
        greenTower.put(3,greenTowerCard4);

        blueTower.put(0,blueTowerCard1);
        blueTower.put(1,blueTowerCard2);
        blueTower.put(2,blueTowerCard3);
        blueTower.put(3,blueTowerCard4);

        yellowTower.put(0,yellowTowerCard1);
        yellowTower.put(1,yellowTowerCard2);
        yellowTower.put(2,yellowTowerCard3);
        yellowTower.put(3,yellowTowerCard4);

        purpleTower.put(0,purpleTowerCard1);
        purpleTower.put(1,purpleTowerCard2);
        purpleTower.put(2,purpleTowerCard3);
        purpleTower.put(3,purpleTowerCard4);

        actionButtons.put(0,greenTowerAction1);
        actionButtons.put(1,greenTowerAction2);
        actionButtons.put(2,greenTowerAction3);
        actionButtons.put(3,greenTowerAction4);
        actionButtons.put(4,yellowTowerAction1);
        actionButtons.put(5,yellowTowerAction2);
        actionButtons.put(6,yellowTowerAction3);
        actionButtons.put(7,yellowTowerAction4);
        actionButtons.put(8,blueTowerAction1);
        actionButtons.put(9,blueTowerAction2);
        actionButtons.put(10,blueTowerAction3);
        actionButtons.put(11,blueTowerAction4);
        actionButtons.put(12,purpleTowerAction1);
        actionButtons.put(13,purpleTowerAction2);
        actionButtons.put(14,purpleTowerAction3);
        actionButtons.put(15,purpleTowerAction4);
        actionButtons.put(16,market1);
        actionButtons.put(17,market2);
        actionButtons.put(18,market3);
        actionButtons.put(19,market4);
        actionGrid.put(20,councilPalace);
        actionButtons.put(21,harvest1);
        actionBox.put(22,harvest2);
        actionButtons.put(23,production1);
        actionBox.put(24,production2);

        buttonAction.put(greenTowerAction1,0);
        buttonAction.put(greenTowerAction2,1);
        buttonAction.put(greenTowerAction3,2);
        buttonAction.put(greenTowerAction4,3);
        buttonAction.put(yellowTowerAction1,4);
        buttonAction.put(yellowTowerAction2,5);
        buttonAction.put(yellowTowerAction3,6);
        buttonAction.put(yellowTowerAction4,7);
        buttonAction.put(blueTowerAction1,8);
        buttonAction.put(blueTowerAction2,9);
        buttonAction.put(blueTowerAction3,10);
        buttonAction.put(blueTowerAction4,11);
        buttonAction.put(purpleTowerAction1,12);
        buttonAction.put(purpleTowerAction2,13);
        buttonAction.put(purpleTowerAction3,14);
        buttonAction.put(purpleTowerAction4,15);
        buttonAction.put(market1,16);
        buttonAction.put(market2,17);
        buttonAction.put(market3,18);
        buttonAction.put(market4,19);
        gridAction.put(councilPalace,20);
        buttonAction.put(harvest1,21);
        boxAction.put(harvest2,22);
        buttonAction.put(production1,23);
        boxAction.put(production2,24);


        coverImages.put(0,greenTowerAction1Cover);
        coverImages.put(1,greenTowerAction2Cover);
        coverImages.put(2,greenTowerAction3Cover);
        coverImages.put(3,greenTowerAction4Cover);
        coverImages.put(4,yellowTowerAction1Cover);
        coverImages.put(5,yellowTowerAction2Cover);
        coverImages.put(6,yellowTowerAction3Cover);
        coverImages.put(7,yellowTowerAction4Cover);
        coverImages.put(8,blueTowerAction1Cover);
        coverImages.put(9,blueTowerAction2Cover);
        coverImages.put(10,blueTowerAction3Cover);
        coverImages.put(11,blueTowerAction4Cover);
        coverImages.put(12,purpleTowerAction1Cover);
        coverImages.put(13,purpleTowerAction2Cover);
        coverImages.put(14,purpleTowerAction3Cover);
        coverImages.put(15,purpleTowerAction4Cover);
        coverImages.put(16,market1Cover);
        coverImages.put(17,market2Cover);
        coverImages.put(18,market3Cover);
        coverImages.put(19,market4Cover);
        coverImages.put(20,councilPalaceCover);
        coverImages.put(21,harvest1Cover);
        coverImages.put(22,harvest2Cover);
        coverImages.put(23,production1Cover);
        coverImages.put(24,production2Cover);


    }

    /**
     * Gestisce l'attivazione leader card
     * @param event
     */
    public void handleActivateLeader(ActionEvent event){
        Button activateButton = (Button) event.getSource();
        if(activateButton==activate1){
            view.sendInput("activate leader card 0");
        }
        if(activateButton==activate2){
            view.sendInput("activate leader card 1");
        }
        if(activateButton==activate3){
            view.sendInput("activate leader card 2");
        }
        if(activateButton==activate4){
            view.sendInput("activate leader card 3");
        }

    }

    /**
     * Gestisce la distruzione leader card

     * @param event
     * @throws Exception
     */
    public void handleBurnLeader(ActionEvent event) throws Exception {
        Object burnButton = event.getSource();
        if (burnButton==burn1){
            view.sendInput("discard leader card 0");
        }
        if (burnButton==burn2){
            view.sendInput("discard leader card 1");
        }
        if (burnButton==burn3){
            view.sendInput("discard leader card 2");
        }
        if (burnButton==burn4){
            view.sendInput("discard leader card 3");
        }
    }

    /**
     * Gestisce l'attivazione bottone Lancia Dadi
     * @param event
     */
    public void handleThrowDices(ActionEvent event){
        view.sendInput("throw dices");
    }

    /**
     * Gestisce l'attivazione bottone Salta Azione
     * @param event
     */
    public void handleSkipAction(ActionEvent event){
        view.sendInput("skip action");
    }

    /**
     * Gestisce l'attivazione bottone Fine Turno
     * @param event
     */
    public void handleEndTurn(ActionEvent event){
        view.sendInput("end turn");
    }

    /**
     * Gestisce l'attivazione dei pulsanti seleziona pedina
     * @param event
     */
    public void handlePawnPicked(MouseEvent event) {
        if (event.getSource() == balckPawn) {
            view.sendInput("use family pawn black");
        }
        if (event.getSource() == whitePawn) {
            view.sendInput("use family pawn white");
        }
        if (event.getSource() == orangePawn) {
            view.sendInput("use family pawn orange");
        }
        if (event.getSource() == neutralPawn) {
            view.sendInput("use family pawn neutral");
        }

    }

    public void handleActionChosenImageView(MouseEvent event){
        if(event.getSource() instanceof ImageView) {
            Integer actionSelected = buttonAction.get(event.getSource());
            view.sendInput("execute action " + actionSelected.toString());
            updatePawnOnActionspace((ImageView) event.getSource());
        }

        if(event.getSource() instanceof GridPane) {
            Integer actionSelected = gridAction.get(event.getSource());
            view.sendInput("execute action " + actionSelected.toString());
            updatePawnOnActionSpace((GridPane) event.getSource());
        }

        if (event.getSource() instanceof HBox){
            Integer actionSelected = boxAction.get(event.getSource());
            view.sendInput("execute action " + actionSelected.toString());
            updatePawnOnActionSpace((HBox) event.getSource());
        }

    }

    public void updatePawnOnActionspace(ImageView imageView){

    }

    public void updatePawnOnActionSpace(GridPane gridPane) {

    }

    public void updatePawnOnActionSpace(HBox hBox) {

    }

        /**
         * Quando viene chiamata setta i vari pulsanti pedina che possono essere clickati
         * @param availability
         */
    //TODO:hashmap pawntype pawns grafiche
    public void activatePawns(Map<FamilyPawn,Boolean> availability){

        for (FamilyPawn pawn: availability.keySet()) {
            FamilyPawnType familyPawnType = pawn.getType();

            if (availability.get(pawn)) {
                setAvailable(pawn.getType());
            }

            else {
                setNotAvailable(pawn.getType());
            }

            switch (familyPawnType) {
                case BLACK:
                    break;
                case ORANGE:
                    if (availability.get(pawn)) {
                        orangePawn.setDisable(false);
                        Integer value = pawn.getActualValue();
                        orangeValue.setText(value.toString());
                    }
                    else {
                        orangePawn.setDisable(true);
                        orangeValue.setText("");
                    }
                    break;
                case WHITE:
                    if (availability.get(pawn)) {
                        whitePawn.setDisable(false);
                        Integer value = pawn.getActualValue();
                        whiteValue.setText(value.toString());
                    }
                    else{
                        whitePawn.setDisable(true);
                        whiteValue.setText("");
                    }
                    break;
                case NEUTRAL:
                    break;
                case BONUS:
                    break;
                case ANY:
                    break;
            }
            if (pawn.getType()==FamilyPawnType.WHITE){

            }
            if (pawn.getType()==FamilyPawnType.ORANGE){

            }
            if (pawn.getType()==FamilyPawnType.BLACK){
                if (availability.get(pawn)) {
                    blackValue.setDisable(false);
                    Integer value = pawn.getActualValue();
                    blackValue.setText(value.toString());
                } else {
                    balckPawn.setDisable(true);
                    blackValue.setText("");
                }
            }
            if (pawn.getType()==FamilyPawnType.NEUTRAL){
                if (availability.get(pawn)) {
                    neutralPawn.setDisable(false);
                    Integer value = pawn.getActualValue();
                    neutralValue.setText(value.toString());
                } else {
                    neutralPawn.setDisable(true);
                    neutralValue.setText("");
                }
            }
        }
    }

    private void setNotAvailable(FamilyPawnType type) {

    }

    private void setAvailable(FamilyPawnType type) {
    }

    /**
     * Quando è chiamata modifica le immagini delle carte visualizzate sulla PersonalBoard
     * @param cards
     * @param cardColor
     */
    public void updateCardsPersonalBoard(ArrayList<DevelopmentCard> cards, CardColor cardColor){
        int i =0;
        for (DevelopmentCard card:cards){
            if (card == null){
                break;
            }
            else{
                if (cardColor.equals(CardColor.YELLOW)){
                    Image image = new Image(view.getCardMap().get(card.getSpecial()));
                    personalYellowCards.get(i).setImage(image);
                    i++;
                }
                if (cardColor.equals(CardColor.BLUE)){
                    Image image = new Image(view.getCardMap().get(card.getSpecial()));
                    personalBlueCards.get(i).setImage(image);
                    i++;
                }
                if (cardColor.equals(CardColor.PURPLE)){
                    Image image = new Image(view.getCardMap().get(card.getSpecial()));
                    personalPurpleCards.get(i).setImage(image);
                    i++;
                }
                if (cardColor.equals(CardColor.GREEN)){
                    Image image = new Image(view.getCardMap().get(card.getSpecial()));
                    personalGreenCards.get(i).setImage(image);
                    i++;
                }
            }
        }
    }

    /**
     * Quando viene chiamata modifica le quantità di resourceAmount sulla PersonalBoard
     * @param newGoodSet
     */
    public void updateGoodSetPersonalBoard(GoodSet newGoodSet){
        for (GoodType type: GoodType.values()) {

            if (type!=GoodType.FAITHPOINTS &&type!=GoodType.MILITARYPOINTS &&type!=GoodType.VICTORYPOINTS) {
                Integer value = newGoodSet.getGoodAmount(type);
                resourceAmount.get(type).setText(value.toString());
            }
            else if (true) {}
                //TODO: aggiorna track - pensa a player color

        }
    }

    /**
     * Quando è chiamata attiva/disattiva tutti i pulsanti ActionSpace che si possono clickare
     * @param availability
     */
    public void updatePossibleActions(Map<Integer, String> availability){
        for (Integer i : availability.keySet()) {

            /*if(availability.get(i)){
                if (i==20){
                    actionGrid.get(i).setDisable(false);
                }
                if (i==22 || i==24){
                    actionBox.get(i).setDisable(false);
                }
                else {
                    actionButtons.get(i).setDisable(false);
                }
                coverImages.get(i).setVisible(false);
            }
            else {
                if (i==20){
                    actionGrid.get(i).setDisable(true);
                }
                if (i==22 || i==24){
                    actionBox.get(i).setDisable(true);
                }
                else {
                    actionButtons.get(i).setDisable(true);
                }
                coverImages.get(i).setVisible(true);
            }*/

        }
    }

    /**
     * Quando è chiamata modifica tutti i contatori delle track sulla PersonalBoard
     */
    public void updateTower(List<DevelopmentCard> developmentCards, CardColor cardColor){

        //TODO: update della tower con quel colore

    }

    /**
     * Quando è chiamata fissa l'immagine della BonusTile che riceve
     */
    public void updateBonusTile(BonusTile bonusTile){}

    /**
     * Quando è chiamata modifica le immagini delle carte sulle torri
     */
    public void updateTracks(){
    }


    public void updateExcomunicationTiles(ArrayList<ExcommunicationTile> tiles){
    }

    public void testGB() {
        whiteValue.setText("Ha funzionato");
    }


    public void chooseWorkers(Map<Integer, ArrayList<String>> cardsForWorkers) {

        //TODO: mostrare la schermata chooseWorkers
        //TODO: printare nel textbox indice e arrayList di stringhe (per ogni indice)
        //TODO: con textArea e submit dell'indice

    }

    public void choosePayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCards) {

        //TODO: mostrare la schermata choosePayToObtain: inizialmente mostra le carte (che contengono payToObtain)
        //TODO: con dei radio button che indicano attiva o meno

        //TODO: qua verrà cambiato lo stato del player a chooseEffect
        //TODO: e ci sarà un altro metodo che farà le cose qui sotto

        //TODO: se il radio button viene attivato, se la payToObtain contiene alternative, esse vengono mostrate
        //TODO: e allo stesso modo con un radio button si sceglie l'alternativa
        //TODO: tutte queste scelte vengono salvate (vedi InputChecker) e spedite all'Input Checker

    }

    public void chooseCost(Map<Integer, String> possibleCosts) {

        //TODO: mostrare la schermata dei costi possibili
        //TODO: esattamente come chooseWorkers eccetto che è una stringa sola e non un arrayList

    }

    public void choosePrivileges(List<Integer> councilPrivileges) {

        //TODO: non so bene come sia implementato a livello di inputChecker, ma la schermata è abbastanza ovvia
    }

    public void chooseBonusTile(Map<Integer, String> bonusTiles) {

        //TODO: mostra la schermata delle bonus Tile

    }










    public void setChooseDistribution(ChooseDistribution chooseDistribution) {
        this.chooseDistribution = chooseDistribution;
    }
}
