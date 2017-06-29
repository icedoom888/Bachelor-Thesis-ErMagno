package it.polimi.ingsw.GC_29.Client.GUI.GameBoard;

import it.polimi.ingsw.GC_29.Client.ChooseDistribution;
import it.polimi.ingsw.GC_29.Client.GUI.BonusTile.BonusTileController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseCost.ChooseCostController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.ChooseEffectController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseEffect.PayToObtainController;
import it.polimi.ingsw.GC_29.Client.GUI.ChoosePrivilege.ChoosePrivilegeController;
import it.polimi.ingsw.GC_29.Client.GUI.ChooseWorkers.WorkersController;
import it.polimi.ingsw.GC_29.Client.GUI.Pray.PrayController;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Controllers.PlayerState;
import it.polimi.ingsw.GC_29.Player.PlayerColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.*;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class GameBoardController {
    //mappa carte per immagini
    private HashMap<String,String> cardMap = new HashMap<>();

    //mappa bonustiles per immagini
    private HashMap<String,String> bonusTilesMap = new HashMap<>();

    //mappa pedine per immagini
    private HashMap<FamilyPawnType,String> bluePawnsImagesMap = new HashMap<>();
    private HashMap<FamilyPawnType,String> greenPawnsImagesMap = new HashMap<>();
    private HashMap<FamilyPawnType,String> redPawnsImagesMap = new HashMap<>();
    private HashMap<FamilyPawnType,String> yellowPawnsImagesMap = new HashMap<>();

    //mappa spazi pedine delle Box
    private HashMap<Integer,ImageView> productionBox = new HashMap<>();
    private int productionBoxFreeSlot = 0;
    private HashMap<Integer,ImageView> harvestBox = new HashMap<>();
    private int harvestBoxFreeSlot = 0;

    //mappa testi nelle tracks
    private HashMap<GoodType,Text> greenPlayerTrack = new HashMap<>();
    private HashMap<GoodType,Text> bluePlayerTrack = new HashMap<>();
    private HashMap<GoodType,Text> redPlayerTrack = new HashMap<>();
    private HashMap<GoodType,Text> yellowPlayerTrack = new HashMap<>();


    //mappa pedine della grid
    private HashMap<Integer,ImageView> gridMap = new HashMap<>();
    private int gridFreeSlot = 0;


    private HashMap<Integer, ImageView> personalGreenCards = new HashMap<>();
    private HashMap<Integer, ImageView> personalBlueCards = new HashMap<>();
    private HashMap<Integer, ImageView> personalYellowCards = new HashMap<>();
    private HashMap<Integer, ImageView> personalPurpleCards = new HashMap<>();

    //Posizioni libere della personal
    private int greenFreeSlot=0;
    private int blueFreeSlot=0;
    private int yellowFreeSlot=0;
    private int purpleFreeSlot=0;

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


    private HashMap<GoodType,Text> resourceAmount = new HashMap<>();
    private HashMap<Integer,ImageView> coverImages = new HashMap<>();

    private ArrayList<ImageView> familyPawns;



    //ChooseDistribution, classe che serve per parlare con il server
    private ChooseDistribution sender;

    //Controller delle schermate interne
    private BonusTileController bonusTileController;
    private ChooseCostController chooseCostController;
    private PayToObtainController payToObtainController;
    private ChooseEffectController chooseEffectController;
    private ChoosePrivilegeController choosePrivilegeController;
    private PrayController prayController;
    private WorkersController workersController;

    //Pane delle schermate interne utilizzati per hidarle
    private AnchorPane bonusTilePane;
    private AnchorPane chooseCostPane;
    private AnchorPane payToObtainPane;
    private AnchorPane chooseEffectPane;
    private AnchorPane choosePrivilegePane;
    private AnchorPane prayPane;
    private AnchorPane yourTurnPane;
    private AnchorPane chooseWorkersPane;
    private AnchorPane throwDicesPane;

    @FXML
    private Text greenVictoryPoints;
    @FXML
    private Text yellowVictoryPoints;
    @FXML
    private Text redVictoryPoints;
    @FXML
    private Text blueVictoryPoints;

    @FXML
    private Text greenMilitaryPoints;
    @FXML
    private Text yellowMilitaryPoints;
    @FXML
    private Text redMilitaryPoints;
    @FXML
    private Text blueMilitaryPoints;

    @FXML
    private Text greenFaithPoints;
    @FXML
    private Text yellowFaithPoints;
    @FXML
    private Text redFaithPoints;
    @FXML
    private Text blueFaithPoints;


    @FXML
    private ImageView grid0;
    @FXML
    private ImageView grid1;
    @FXML
    private ImageView grid2;
    @FXML
    private ImageView grid3;
    @FXML
    private ImageView grid4;
    @FXML
    private ImageView grid5;
    @FXML
    private ImageView grid6;
    @FXML
    private ImageView grid7;
    @FXML
    private ImageView grid8;
    @FXML
    private ImageView grid9;
    @FXML
    private ImageView grid10;
    @FXML
    private ImageView grid11;


    @FXML
    private ImageView production2_0;
    @FXML
    private ImageView production2_1;
    @FXML
    private ImageView production2_2;
    @FXML
    private ImageView production2_3;

    @FXML
    private ImageView harvest2_0;
    @FXML
    private ImageView harvest2_1;
    @FXML
    private ImageView harvest2_2;
    @FXML
    private ImageView harvest2_3;



    @FXML
    private ImageView bonusTile;

    //Pulsanti per scegliere le pedine
    @FXML
    private ImageView whitePawn;
    @FXML
    private ImageView orangePawn;
    @FXML
    private ImageView blackPawn;
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

    private ArrayList<Button> activateLeaderCardsButtons;

    //Pulsanti per scartare una carta leader
    @FXML
    private Button burn1;
    @FXML
    private Button burn2;
    @FXML
    private Button burn3;
    @FXML
    private Button burn4;

    private ArrayList<Button> burnLeaderCardsButtons;


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
    // in HashMap
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
    public void initialize() {

        greenPlayerTrack.put(GoodType.VICTORYPOINTS,greenVictoryPoints);
        greenPlayerTrack.put(GoodType.MILITARYPOINTS,greenMilitaryPoints);
        greenPlayerTrack.put(GoodType.FAITHPOINTS,greenFaithPoints);

        redPlayerTrack.put(GoodType.VICTORYPOINTS,redVictoryPoints);
        redPlayerTrack.put(GoodType.MILITARYPOINTS,redMilitaryPoints);
        redPlayerTrack.put(GoodType.FAITHPOINTS,redFaithPoints);

        yellowPlayerTrack.put(GoodType.VICTORYPOINTS,yellowVictoryPoints);
        yellowPlayerTrack.put(GoodType.MILITARYPOINTS,yellowMilitaryPoints);
        yellowPlayerTrack.put(GoodType.FAITHPOINTS,yellowFaithPoints);

        bluePlayerTrack.put(GoodType.VICTORYPOINTS,blueVictoryPoints);
        bluePlayerTrack.put(GoodType.MILITARYPOINTS,blueMilitaryPoints);
        bluePlayerTrack.put(GoodType.FAITHPOINTS,blueFaithPoints);

        gridMap.put(0,grid0);
        gridMap.put(1,grid1);
        gridMap.put(2,grid2);
        gridMap.put(3,grid3);
        gridMap.put(4,grid4);
        gridMap.put(5,grid5);
        gridMap.put(6,grid6);
        gridMap.put(7,grid7);
        gridMap.put(8,grid8);
        gridMap.put(9,grid9);
        gridMap.put(10,grid10);
        gridMap.put(11,grid11);



        productionBox.put(0,production2_0);
        productionBox.put(1,production2_1);
        productionBox.put(2,production2_2);
        productionBox.put(3,production2_3);

        harvestBox.put(0,harvest2_0);
        harvestBox.put(1,harvest2_1);
        harvestBox.put(2,harvest2_2);
        harvestBox.put(3,harvest2_3);

        bluePawnsImagesMap.put(FamilyPawnType.BLACK,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/blueBlackPawn.png");
        bluePawnsImagesMap.put(FamilyPawnType.NEUTRAL,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/blueNeutralPawn.png");
        bluePawnsImagesMap.put(FamilyPawnType.ORANGE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/blueOrangePawn.png");
        bluePawnsImagesMap.put(FamilyPawnType.WHITE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/blueWhitePawn.png");

        greenPawnsImagesMap.put(FamilyPawnType.BLACK,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/greenBalckPawn.png");
        greenPawnsImagesMap.put(FamilyPawnType.NEUTRAL,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/greenNeutralPawn.png");
        greenPawnsImagesMap.put(FamilyPawnType.ORANGE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/greenOrangePawn.png");
        greenPawnsImagesMap.put(FamilyPawnType.WHITE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/greenWhitePawn.png");

        yellowPawnsImagesMap.put(FamilyPawnType.BLACK,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/yellowBlackPawn.png");
        yellowPawnsImagesMap.put(FamilyPawnType.NEUTRAL,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/yellowNeutralPawn.png");
        yellowPawnsImagesMap.put(FamilyPawnType.ORANGE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/yellowOrangePawn.png");
        yellowPawnsImagesMap.put(FamilyPawnType.WHITE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/yellowWhitePawn.png");

        redPawnsImagesMap.put(FamilyPawnType.BLACK,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/redBalckPawn.png");
        redPawnsImagesMap.put(FamilyPawnType.NEUTRAL,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/redNeutralPawn.png");
        redPawnsImagesMap.put(FamilyPawnType.ORANGE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/redOrangePawn.png");
        redPawnsImagesMap.put(FamilyPawnType.WHITE,"lorenzo_materiale_grafico_compr/GameboardElements/pawns/redWhitePawn.png");


        bonusTilesMap.put("0","lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/personalbonustile_4.png");
        bonusTilesMap.put("1","lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/personalbonustile_5.png");
        bonusTilesMap.put("2","lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/personalbonustile_2.png");
        bonusTilesMap.put("3","lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/personalbonustile_3.png");
        bonusTilesMap.put("4","lorenzo_materiale_grafico_compr/Lorenzo_Punchboard_CUT_compressed/personalbonustile_1.png");


        cardMap.put("Avamposto Commerciale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_1.png");
        cardMap.put("Bosco","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_2.png");
        cardMap.put("Borgo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_3.png");
        cardMap.put("Cava di Ghiaia","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_4.png");
        cardMap.put("Foresta","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_5.png");
        cardMap.put("Monastero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_6.png");
        cardMap.put("Rocca","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_7.png");
        cardMap.put("Città","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_8.png");
        cardMap.put("Miniera d'Oro","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_9.png");
        cardMap.put("Villaggio Montano","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_10.png");
        cardMap.put("Villaggio Minerario","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_11.png");
        cardMap.put("Cava di Pietra","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_12.png");
        cardMap.put("Possedimento","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_13.png");
        cardMap.put("Eremo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_14.png");
        cardMap.put("Maniero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_15.png");
        cardMap.put("Ducato","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_16.png");
        cardMap.put("Città Mercantile","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_17.png");
        cardMap.put("Tenuta","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_18.png");
        cardMap.put("Colonia","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_19.png");
        cardMap.put("Cava di Marmo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_20.png");
        cardMap.put("Provincia","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_21.png");
        cardMap.put("Santuario","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_22.png");
        cardMap.put("Castello","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_23.png");
        cardMap.put("Città Fortificata","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_24.png");

        cardMap.put("Zecca","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_25.png");
        cardMap.put("Esattoria","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_26.png");
        cardMap.put("Arco di Trionfo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_27.png");
        cardMap.put("Teatro","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_28.png");
        cardMap.put("Falegnameria","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_29.png");
        cardMap.put("Tagliapietre","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_30.png");
        cardMap.put("Cappella","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_31.png");
        cardMap.put("Residenza","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_32.png");
        cardMap.put("Mercato","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_33.png");
        cardMap.put("Tesoreria","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_34.png");
        cardMap.put("Gilda dei Pittori","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_35.png");
        cardMap.put("Gilda degli Scultori","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_36.png");
        cardMap.put("Gilda dei Costruttori","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_37.png");
        cardMap.put("Battistero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_38.png");
        cardMap.put("Caserma","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_39.png");
        cardMap.put("Fortezza","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_40.png");
        cardMap.put("Banca","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_41.png");
        cardMap.put("Fiera","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_42.png");
        cardMap.put("Giardino","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_43.png");
        cardMap.put("Castelletto","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_44.png");
        cardMap.put("Palazzo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_45.png");
        cardMap.put("Basilica","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_46.png");
        cardMap.put("Accademia Militare","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_47.png");
        cardMap.put("Cattedrale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_48.png");

        cardMap.put("Condottiero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_49.png");
        cardMap.put("Costruttore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_50.png");
        cardMap.put("Dama","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_51.png");
        cardMap.put("Cavaliere","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_52.png");
        cardMap.put("Contadino","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_53.png");
        cardMap.put("Artigiano","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_54.png");
        cardMap.put("Predicatore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_55.png");
        cardMap.put("Badessa","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_56.png");
        cardMap.put("Capitano","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_57.png");
        cardMap.put("Architetto","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_58.png");
        cardMap.put("Mecenate","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_59.png");
        cardMap.put("Eroe","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_60.png");
        cardMap.put("Fattore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_61.png");
        cardMap.put("Studioso","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_62.png");
        cardMap.put("Messo Papale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_63.png");
        cardMap.put("Messo Reale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_64.png");
        cardMap.put("Nobile","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_65.png");
        cardMap.put("Governatore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_66.png");
        cardMap.put("Cortigiana","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_67.png");
        cardMap.put("Araldo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_68.png");
        cardMap.put("Cardinale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_69.png");
        cardMap.put("Vescovo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_70.png");
        cardMap.put("Generale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_71.png");
        cardMap.put("Ambasciatore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_72.png");

        cardMap.put("Hiring Recruits","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_73.png");
        cardMap.put("Repairing the Church","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_74.png");
        cardMap.put("Building the Walls","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_75.png");
        cardMap.put("Raising a Statue","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_76.png");
        cardMap.put("Military Campaign","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_77.png");
        cardMap.put("Hosting Panhandlers","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_78.png");
        cardMap.put("Fighting Heresies","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_79.png");
        cardMap.put("Support to the Bishop","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_80.png");
        cardMap.put("Hiring Soldiers","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_81.png");
        cardMap.put("Repairing the Abbey","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_82.png");
        cardMap.put("Building the Bastions","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_83.png");
        cardMap.put("Support to the King","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_84.png");
        cardMap.put("Improving the Canals","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_85.png");
        cardMap.put("Hosting Foreigners","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_86.png");
        cardMap.put("Crusade","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_87.png");
        cardMap.put("Support to the Cardinal","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_88.png");
        cardMap.put("Hiring Mercenaries","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_89.png");
        cardMap.put("Repairing the Cathedral","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_90.png");
        cardMap.put("Building the Towers","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_91.png");
        cardMap.put("Promoting Sacred Art","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_92.png");
        cardMap.put("Military Conquest","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_93.png");
        cardMap.put("Improving the Roads","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_94.png");
        cardMap.put("Sacred War","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_95.png");
        cardMap.put("Support to the Pope","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_96.png");

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

        /*
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
        boxAction.put(production2,24);*/

        buttonAction.put(greenTowerAction1Cover,0);
        buttonAction.put(greenTowerAction2Cover,1);
        buttonAction.put(greenTowerAction3Cover,2);
        buttonAction.put(greenTowerAction4Cover,3);
        buttonAction.put(yellowTowerAction1Cover,4);
        buttonAction.put(yellowTowerAction2Cover,5);
        buttonAction.put(yellowTowerAction3Cover,6);
        buttonAction.put(yellowTowerAction4Cover,7);
        buttonAction.put(blueTowerAction1Cover,8);
        buttonAction.put(blueTowerAction2Cover,9);
        buttonAction.put(blueTowerAction3Cover,10);
        buttonAction.put(blueTowerAction4Cover,11);
        buttonAction.put(purpleTowerAction1Cover,12);
        buttonAction.put(purpleTowerAction2Cover,13);
        buttonAction.put(purpleTowerAction3Cover,14);
        buttonAction.put(purpleTowerAction4Cover,15);
        buttonAction.put(market1Cover,16);
        buttonAction.put(market2Cover,17);
        buttonAction.put(market3Cover,18);
        buttonAction.put(market4Cover,19);
        buttonAction.put(councilPalaceCover,20);
        buttonAction.put(harvest1Cover,21);
        buttonAction.put(harvest2Cover,22);
        buttonAction.put(production1Cover,23);
        buttonAction.put(production2Cover,24);

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


        familyPawns = new ArrayList<>(Arrays.asList(whitePawn, orangePawn, blackPawn, neutralPawn));

        activateLeaderCardsButtons = new ArrayList<>(Arrays.asList(activate1, activate2, activate3, activate4));

        burnLeaderCardsButtons = new ArrayList<>(Arrays.asList(burn1, burn2, burn3, burn4));

    }

    /**
     * Gestisce l'attivazione leader card
     * @param event
     */
    public void handleActivateLeader(ActionEvent event){
        Button activateButton = (Button) event.getSource();
        if(activateButton==activate1){
            sender.sendInput("activate leader card 0");
        }
        if(activateButton==activate2){
            sender.sendInput("activate leader card 1");
        }
        if(activateButton==activate3){
            sender.sendInput("activate leader card 2");
        }
        if(activateButton==activate4){
            sender.sendInput("activate leader card 3");
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
            sender.sendInput("discard leader card 0");
        }
        if (burnButton==burn2){
            sender.sendInput("discard leader card 1");
        }
        if (burnButton==burn3){
            sender.sendInput("discard leader card 2");
        }
        if (burnButton==burn4){
            sender.sendInput("discard leader card 3");
        }
    }

    /**
     * Gestisce l'attivazione bottone Lancia Dadi
     * @param event
     */
    public void handleThrowDices(ActionEvent event){
        sender.sendInput("throw dices");
    }

    /**
     * Gestisce l'attivazione bottone Salta Azione
     * @param event
     */
    public void handleSkipAction(ActionEvent event){
        sender.sendInput("skip action");
    }

    /**
     * Gestisce l'attivazione bottone Fine Turno
     * @param event
     */
    public void handleEndTurn(ActionEvent event){
        sender.sendInput("end turn");
    }

    /**
     * Gestisce l'attivazione dei pulsanti seleziona pedina
     * @param event
     */
    public void handlePawnPicked(MouseEvent event) {
        if (event.getSource() == blackPawn) {
            sender.sendInput("use family pawn black");
        }
        if (event.getSource() == whitePawn) {
            sender.sendInput("use family pawn white");
        }
        if (event.getSource() == orangePawn) {
            sender.sendInput("use family pawn orange");
        }
        if (event.getSource() == neutralPawn) {
            sender.sendInput("use family pawn neutral");
        }

    }


    public void handleActionChosenImageView(MouseEvent event){
        Integer actionSelected = buttonAction.get(event.getSource());
        sender.sendInput("execute action " + actionSelected.toString());
    }

    public void updatePawn(FamilyPawn familyPawn, int actionIndex) {
        PlayerColor playerColor = familyPawn.getPlayerColor();
        FamilyPawnType pawnType = familyPawn.getType();
        Image image = null;
        switch (playerColor){
            case YELLOW:
                image = new Image(yellowPawnsImagesMap.get(pawnType));
                break;
            case BLUE:
                image = new Image(bluePawnsImagesMap.get(pawnType));
                break;
            case GREEN:
                image = new Image(greenPawnsImagesMap.get(pawnType));
                break;
            case RED:
                image = new Image(redPawnsImagesMap.get(pawnType));
                break;
        }
        if (actionIndex == 20){
            updatePawnOnGrid(image,actionIndex);
        }

        else if (actionIndex == 22 || actionIndex == 24){
            updatePawnOnBox(image,actionIndex);
        }
        else {
            updatePawnOnActionspace(image,actionIndex);
        }
    }

    private void updatePawnOnActionspace(Image image, int actionIndex) {
        actionButtons.get(actionIndex).setImage(image);
        for (Integer integer : coverImages.keySet()){
            coverImages.get(integer).setVisible(false);
        }
    }

    private void updatePawnOnBox(Image image, int actionIndex) {
        if (actionIndex==22){
            harvestBox.get(harvestBoxFreeSlot).setImage(image);
            harvestBoxFreeSlot++;
        }
        else if (actionIndex==24){
            productionBox.get(productionBoxFreeSlot).setImage(image);
            productionBoxFreeSlot++;
        }
    }

    private void updatePawnOnGrid(Image image, int actionIndex) {
        gridMap.get(gridFreeSlot).setImage(image);
        gridFreeSlot++;
    }

    public void removeAllPawns(){
        for (Integer integer : actionButtons.keySet()) {
            Image image = null;
            actionButtons.get(integer).setImage(image);
        }
        for (Integer integer : harvestBox.keySet()){
            Image image = null;
            harvestBox.get(integer).setImage(image);
        }
        for (Integer integer : productionBox.keySet()){
            Image image = null;
            productionBox.get(integer).setImage(image);
        }
    }



    /**
         * Quando viene chiamata setta i vari pulsanti pedina che possono essere clickati
         * @param availability
         */
    public void activatePawns(Map<FamilyPawn,Boolean> availability){

        for (FamilyPawn pawn: availability.keySet()) {

            if (availability.get(pawn)) {
                setPawnAvailable(pawn);

            }

            else {
                setNotAvailable(pawn.getType());
            }
        }
    }

    private void setNotAvailable(FamilyPawnType type) {
        switch (type){
            case BLACK:
                blackValue.setVisible(false);
                blackPawn.setVisible(false);
                break;
            case ORANGE:
                orangeValue.setVisible(false);
                orangePawn.setVisible(false);
                break;
            case WHITE:
                whiteValue.setVisible(false);
                whitePawn.setVisible(false);
                break;
            case NEUTRAL:
                neutralValue.setVisible(false);
                neutralPawn.setVisible(false);
                break;
        }
    }

    private void setPawnAvailable(FamilyPawn pawn) {

        FamilyPawnType type = pawn.getType();
        Integer value = pawn.getActualValue();

        switch (type){

            case BLACK:
                blackValue.setVisible(true);
                blackPawn.setVisible(true);
                blackPawn.setDisable(false);
                blackValue.setText(value.toString());
                break;

            case ORANGE:
                orangeValue.setVisible(true);
                orangePawn.setVisible(true);
                orangePawn.setDisable(false);
                orangeValue.setText(value.toString());
                break;

            case WHITE:
                whiteValue.setVisible(true);
                whitePawn.setVisible(true);
                whitePawn.setDisable(false);
                whiteValue.setText(value.toString());
                break;

            case NEUTRAL:
                neutralValue.setVisible(true);
                neutralPawn.setVisible(true);
                neutralPawn.setDisable(false);
                neutralValue.setText(value.toString());
                break;
        }
    }

    /**
     * Quando è chiamata modifica le immagini delle carte visualizzate sulla PersonalBoard
     * @param cardColor
     */
    public void updateCardsPersonalBoard(String name, CardColor cardColor){
        Image image = new Image(cardMap.get(name));
        switch (cardColor){
            case BLUE:
                personalBlueCards.get(blueFreeSlot).setImage(image);
                blueFreeSlot ++;
                break;
            case GREEN:
                personalGreenCards.get(greenFreeSlot).setImage(image);
                greenFreeSlot++;
                break;
            case PURPLE:
                personalPurpleCards.get(purpleFreeSlot).setImage(image);
                purpleFreeSlot++;
                break;
            case YELLOW:
                personalYellowCards.get(yellowFreeSlot).setImage(image);
                yellowFreeSlot++;
                break;
        }
    }

    /**
     * Quando viene chiamata modifica le quantità di resourceAmount sulla PersonalBoard
     * @param newGoodSet
     */
    public void updatePersonalGoodSet(GoodSet newGoodSet/*, PlayerColor playerColor*/){
        for (GoodType type: GoodType.values()) {
            if (type==GoodType.COINS){
                Integer value = newGoodSet.getGoodAmount(type);
                String valueString = value.toString();
                gold.setText(valueString);
            }
            else if(type==GoodType.WOOD){
                Integer value = newGoodSet.getGoodAmount(type);
                String valueString = value.toString();
                wood.setText(valueString);
            }
            else if(type==GoodType.STONE){
                Integer value = newGoodSet.getGoodAmount(type);
                String valueString = value.toString();
                stone.setText(valueString);
            }
            else if(type==GoodType.WORKERS){
                Integer value = newGoodSet.getGoodAmount(type);
                String valueString = value.toString();
                workers.setText(valueString);
            }
        }
    }

    /**
     * Quando è chiamata attiva/disattiva tutti i pulsanti ActionSpace che si possono clickare
     * @param availability
     */
    public void updatePossibleActions(Map<Integer, String> availability){
        for (Integer i : availability.keySet()) {
            /*if (i == 20) {
                actionGrid.get(i).setDisable(false);
            }
            else if (i == 22 || i == 24) {
                actionBox.get(i).setDisable(false);
            }
            else if (i!=20 && i!=22 && i!=24){
                actionButtons.get(i).setDisable(false);
            }*/
            coverImages.get(i).setVisible(true);
        }

        for(int r=0;r<25;r++) {

            if (!availability.containsKey(r)) {
                /*

                if (r==20) {
                    actionGrid.get(r).setDisable(true);
                }

                else if (r==22 || r==24) {
                    actionBox.get(r).setDisable(true);
                }

                else if (r!=20 && r!=22 && r!=24) {
                    actionButtons.get(r).setDisable(true);
                }
                */
                coverImages.get(r).setVisible(false);
            }
        }
    }

    /**
     * modifica il contenuto delle tracks
     * @param playerColor
     * @param goodType
     * @param numberOfPoints
     */
    public void updateTrack(PlayerColor playerColor, GoodType goodType, int numberOfPoints) {

        String numberOfPointString;
        int value;
        int sum;

        switch (playerColor){
            case BLUE:
                value = Integer.parseInt(bluePlayerTrack.get(goodType).getText());
                sum = value + numberOfPoints;
                numberOfPointString = String.valueOf(sum);
                bluePlayerTrack.get(goodType).setText(numberOfPointString);
                break;
            case GREEN:
                value = Integer.parseInt(greenPlayerTrack.get(goodType).getText());
                sum = value + numberOfPoints;
                numberOfPointString = String.valueOf(sum);
                greenPlayerTrack.get(goodType).setText(numberOfPointString);
                break;
            case RED:
                value = Integer.parseInt(redPlayerTrack.get(goodType).getText());
                sum = value + numberOfPoints;
                numberOfPointString = String.valueOf(sum);
                redPlayerTrack.get(goodType).setText(numberOfPointString);
                break;
            case YELLOW:
                value = Integer.parseInt(yellowPlayerTrack.get(goodType).getText());
                sum = value + numberOfPoints;
                numberOfPointString = String.valueOf(sum);
                yellowPlayerTrack.get(goodType).setText(numberOfPointString);
                break;
        }
    }

    /**
     * Quando è chiamata modifica tutte le carte sulle torri
     */
    public void updateTower(ArrayList<String> cardNames, CardColor cardColor){
        for (int i =0;i<4;i++){
            String name = cardNames.get(i);
            switch (cardColor){
                case GREEN:
                    if (!name.contentEquals("null")) {
                        Image image = new Image(cardMap.get(cardNames.get(i)));

                        greenTower.get(i).setImage(image);
                    }
                    else {
                        greenTower.get(i).setImage(null);
                    }
                    break;

                case BLUE:
                    if (!name.contentEquals("null")) {
                        Image image = new Image(cardMap.get(cardNames.get(i)));
                        blueTower.get(i).setImage(image);
                    }
                    else {
                        blueTower.get(i).setImage(null);
                    }
                    break;

                case PURPLE:
                    if (!name.contentEquals("null")) {
                        Image image = new Image(cardMap.get(cardNames.get(i)));
                        purpleTower.get(i).setImage(image);
                    }
                    else {
                        purpleTower.get(i).setImage(null);
                    }
                    break;

                case YELLOW:
                    if (!name.contentEquals("null")) {
                        Image image = new Image(cardMap.get(cardNames.get(i)));
                        yellowTower.get(i).setImage(image);
                    }
                    else {
                        yellowTower.get(i).setImage(null);
                    }
                    break;
            }
        }
    }

    /**
     * Quando è chiamata fissa l'immagine della BonusTile che riceve
     */
    public void updateBonusTile(String bonusTileIndex){
        Image image = new Image(bonusTilesMap.get(bonusTileIndex));
        bonusTile.setImage(image);
    }

    public void updateExcomunicationTiles(ArrayList<ExcommunicationTile> tiles){
        //TODO
    }

    public void testGB() {
        whiteValue.setText("Ha funzionato");
    }


    public void chooseWorkers(Map<Integer, ArrayList<String>> cardsForWorkers) {

        System.out.println(cardsForWorkers);

        ArrayList<Integer> choices = new ArrayList<>();
        chooseWorkersPane.setVisible(true);
        StringBuilder newWorkers= new StringBuilder();

        for (Integer index:cardsForWorkers.keySet()) {
            choices.add(index);
            newWorkers.append(index.toString()).append(") ");

            for (int i = 0;i < (cardsForWorkers.get(index)).size(); i++) {
                newWorkers.append((cardsForWorkers.get(index)).get(i));
            }

            newWorkers.append("\n");
        }
        workersController.updateShownCosts(newWorkers.toString());
        workersController.setChoices(choices);
    }

    public void choosePayToObtainCards(Map<String, HashMap<Integer, String>> payToObtainCards) {

        System.out.println("choose payToObtain gameboard controller");

        payToObtainController.chooseCards(payToObtainCards);




        //TODO: mostrare la schermata choosePayToObtain: inizialmente mostra le carte (che contengono payToObtain)
        //TODO: con dei radio button che indicano attiva o meno

        //TODO: qua verrà cambiato lo stato del player a chooseEffect
        //TODO: e ci sarà un altro metodo che farà le cose qui sotto

        //TODO: se il radio button viene attivato, se la payToObtain contiene alternative, esse vengono mostrate
        //TODO: e allo stesso modo con un radio button si sceglie l'alternativa
        //TODO: tutte queste scelte vengono salvate (vedi InputChecker) e spedite all'Input Checker

    }



    public void chooseCost(Map<Integer, String> possibleCosts) {

        chooseCostPane.setVisible(true);
        StringBuilder newCosts= new StringBuilder();

        for (Integer index : possibleCosts.keySet()) {
            newCosts.append(index.toString()).append(") ").append(possibleCosts.get(index)).append("\n");
        }

        chooseCostController.updateShownCosts(newCosts.toString());
    }


    //TODO: deve essere chiamata passandogli la lista di interi selezionabili ogni volta che ho un privilege da scegliere, devono sempre avere la stessa numerazione
    public void choosePrivileges(List<Integer> councilPrivileges) {
        choosePrivilegePane.setVisible(true);
        choosePrivilegeController.updatePrivilege(councilPrivileges);
    }

    //TODO: arrivano solo quelle da mostrare, con indici crescenti, mi servono sempre con numero fisso da 0 a 4
    public void chooseBonusTile(Map<Integer, String> bonusTiles) {
        bonusTilePane.setVisible(true);
        bonusTileController.setBonusTiles(bonusTiles);
        //bonusTilePane.setDisable(false);
    }

    public void yourTurn() throws InterruptedException {
        try {
            yourTurnPane.setVisible(true);
            Thread.sleep(3000);
            yourTurnPane.setVisible(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void pray(String excommunication){
        prayPane.setVisible(true);
        prayController.updatePray(excommunication);
    }



    public void setState(PlayerState newPlayerState) {

        closeWindows();

        switch (newPlayerState) {

            case CHOOSE_BONUS_TILE:

                setActions(false);

                setLeaderButtons(false);

                setFamilyPawns(false);

                setThrowDices(false);

                setSkipAction(false);

                setEndTurn(false);


                break;


            case THROWDICES:

                setActions(false);

                setLeaderButtons(false);

                setFamilyPawns(false);

                setThrowDices(true);

                setSkipAction(false);

                setEndTurn(false);

                throwDicesPane.setVisible(true);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                throwDicesPane.setVisible(false);

                break;





            case DOACTION:

                setActions(false);

                setLeaderButtons(true);

                setFamilyPawns(true);

                setThrowDices(false);

                setSkipAction(true);

                setEndTurn(true);

                yourTurnPane.setVisible(true);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                yourTurnPane.setVisible(false);


                break;

            case BONUSACTION:

                setActions(false);

                setLeaderButtons(true);

                setFamilyPawns(false);

                setThrowDices(false);

                setSkipAction(true);

                setEndTurn(true);


                break;


            case CHOOSEACTION:

                setActions(false);

                setLeaderButtons(true);

                setFamilyPawns(true);

                setThrowDices(false);

                setSkipAction(true);

                setEndTurn(true);



                break;


            case CHOOSEWORKERS:
            case ACTIVATE_PAY_TO_OBTAIN_CARDS:
            case CHOOSE_EFFECT:
            case CHOOSECOST:
            case CHOOSE_COUNCIL_PRIVILEGE:
            case PRAY:
            case WAITING:

                noButtonsAble();

                break;



            case ENDTURN:

                setActions(false);

                setLeaderButtons(true);

                setFamilyPawns(false);

                setThrowDices(false);

                setSkipAction(false);

                setEndTurn(true);


                break;





        }

    }

    private void closeWindows() {

        bonusTilePane.setVisible(false);
        chooseCostPane.setVisible(false);
        payToObtainPane.setVisible(false);
        chooseEffectPane.setVisible(false);
        choosePrivilegePane.setVisible(false);
        chooseWorkersPane.setVisible(false);
        prayPane.setVisible(false);
        yourTurnPane.setVisible(false);

    }

    public void closeWindow(Pane pane) {
        pane.setVisible(false);
    }

    public void openWindow(Pane pane) {
        pane.setVisible(true);
    }


    private void setEndTurn(boolean b) {

        endTurn.setDisable(!b);
    }

    private void setSkipAction(boolean b) {

        skipAction.setDisable(!b);

    }

    private void setThrowDices(boolean b) {

        throwDices.setDisable(!b);

    }

    private void setFamilyPawns(boolean b) {

        for (ImageView familyPawn : familyPawns) {
            familyPawn.setDisable(!b);
        }

    }

    private void setLeaderButtons(boolean b) {

        for (Button activateLeaderCardsButton : activateLeaderCardsButtons) {
            activateLeaderCardsButton.setDisable(!b);
        }

        for (Button burnLeaderCardsButton : burnLeaderCardsButtons) {
            burnLeaderCardsButton.setDisable(!b);
        }

    }

    private void setActions(boolean b) {

        for (ImageView imageView : actionButtons.values()) {
            imageView.setDisable(!b);
        }
        for (GridPane gridPane : actionGrid.values()) {
            gridPane.setDisable(!b);
        }
        for (HBox hBox : actionBox.values()) {
            hBox.setDisable(!b);
        }
    }

    public void noButtonsAble() {

        setActions(false);

        setLeaderButtons(false);

        setFamilyPawns(false);

        setThrowDices(false);

        setSkipAction(false);

        setEndTurn(false);
    }





    public void setBonusTilePane(AnchorPane bonusTilePane) {
        this.bonusTilePane = bonusTilePane;
    }

    public void setChooseCostPane(AnchorPane chooseCostPane) {
        this.chooseCostPane = chooseCostPane;
    }

    public void setPayToObtainPane(AnchorPane payToObtainPane) {
        this.payToObtainPane = payToObtainPane;
    }

    public void setChooseEffectPane(AnchorPane chooseEffectPane) {
        this.chooseEffectPane = chooseEffectPane;
    }

    public void setChoosePrivilegePane(AnchorPane choosePrivilegePane) {
        this.choosePrivilegePane = choosePrivilegePane;
    }

    public void setPrayPane(AnchorPane prayPane) {
        this.prayPane = prayPane;
    }

    public void setYourTurnPane(AnchorPane yourTurnPane) {
        this.yourTurnPane = yourTurnPane;
    }

    public void setThrowDicesPane(AnchorPane childDices) {
        this.throwDicesPane = childDices;
    }

    public void setChooseWorkersPane(AnchorPane chooseWorkersPane) {
        this.chooseWorkersPane = chooseWorkersPane;
    }

    public void setBonusTileController(BonusTileController bonusTileController) {
        this.bonusTileController = bonusTileController;
    }

    public void setChooseCostController(ChooseCostController chooseCostController) {
        this.chooseCostController = chooseCostController;
    }

    public void setPrayController(PrayController prayController) {
        this.prayController = prayController;
    }

    public void setChooseDistribution(ChooseDistribution chooseDistribution) {
        this.sender = chooseDistribution;
    }

    public void setChoosePrivilegeController(ChoosePrivilegeController choosePrivilegeController) {
        this.choosePrivilegeController = choosePrivilegeController;
    }

    public void setWorkersController(WorkersController workersController) {
        this.workersController = workersController;
    }

    public void setPayToObtainController(PayToObtainController payToObtainController) {
        this.payToObtainController = payToObtainController;
    }

    public void setChooseEffectController(ChooseEffectController chooseEffectController) {
        this.chooseEffectController = chooseEffectController;
    }

    public AnchorPane getPayToObtainPane() {
        return payToObtainPane;
    }

    public AnchorPane getChooseEffectPane() {
        return chooseEffectPane;
    }

    public String getCard(String currentPayToObtainCard) {

        return cardMap.get(currentPayToObtainCard);

    }
}
