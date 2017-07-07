package it.polimi.ingsw.GC_29.Controllers;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_29.Client.ClientRMI.ClientRMIView;
import it.polimi.ingsw.GC_29.Model.*;
import it.polimi.ingsw.GC_29.Model.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by Christian on 28/05/2017.
 */
public class GameSetup {

    private final int CARDSPERDECK = 24;
    private final int NUMBEROFCARDS = 96;

    private int numberOfPlayers;

    private Model model;

    private GameBoard gameBoard;

    private EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks;

    private Map<Era, List<ExcommunicationTile>> excommunicationTileMap;

    private ArrayList<LeaderCard> leaderCards;

    private ArrayList<Player> players;

    private static final Logger LOGGER  = Logger.getLogger(ClientRMIView.class.getName());



    public GameSetup(ArrayList<Player> clientList) {


        this.model = new Model();
        this.players = clientList;
        this.numberOfPlayers = players.size();
        this.orderedDecks = new EnumMap<>(CardColor.class);
        this.excommunicationTileMap = new EnumMap<>(Era.class);

    }

    /*public void setPlayers(ArrayList<Player> clientList) throws RemoteException {

        players = new ArrayList<>();

        for (Player player : clientList) {

            String name = clientRemoteInterface.getUserName();
            PlayerColor playerColor = clientRemoteInterface.getPlayerColor();

            PersonalBoard personalBoard = new PersonalBoard(new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet())), 6);

            Player player = new Player(name, playerColor, personalBoard);

            players.add(player);
        }

        this.numberOfPlayers = players.size();

    }*/


    /**
     * from the main the init method will be called, it will setup all the model, at the end the main method
     * will call the GameManager (manager for the setting of the currentPlayer, the management of the begin round, the end round
     * and the end era (relationship with the church is managed there)
     */
    public void init()  {

        try {
            this.gameBoard = loadGameBoardFromFile(numberOfPlayers);
        } catch (IOException e) {
            LOGGER.info((Supplier<String>) e);
        }

        for(CardColor color : CardColor.values()){
            if (color != CardColor.ANY) {
                try {
                    this.orderedDecks.put(color, getDeckFromFile(color));
                } catch (FileNotFoundException e) {
                    LOGGER.info((Supplier<String>) e);
                }
            }
        }




        Collections.shuffle(players);

        // setExcommunicationTiles();
        // setGoodsForPlayers();
        // commentate e rese public perchè devo prima registrare la gui e poi settare i goods e exc

        setGameStatus();

    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * this method get the specific deck from file, it shuffles the card of the deck compared to the Era and return a deck
     * with the structure of a stack, ( we have the card of the thirdEra, then the secondEra and at the end the firstEra) --> useful to pick
     * a card with the pop() method
     */
    private ArrayDeque<DevelopmentCard> getDeckFromFile(CardColor color) throws FileNotFoundException {

        ArrayDeque<DevelopmentCard> orderedDeck = new ArrayDeque<>();

        ArrayList<DevelopmentCard> deck;

        FileReader cardFileReader;

        switch (color) {
            case GREEN:

                cardFileReader = new FileReader("cards/greenCards");
                break;

            case YELLOW:

                cardFileReader = new FileReader("cards/yellowCards");
                break;

            case BLUE:

                cardFileReader = new FileReader("cards/blueCards");
                break;

            case PURPLE:

                cardFileReader = new FileReader("cards/purpleCards");
                break;

            default:
                cardFileReader = null;
        }

        deck = new ObjectDeserializer().getCardDeck(cardFileReader);

        EnumMap<Era, ArrayList<DevelopmentCard>> eraCardMap = new EnumMap<>(Era.class);
        eraCardMap.put(Era.FIRST, new ArrayList<>());
        eraCardMap.put(Era.SECOND, new ArrayList<>());
        eraCardMap.put(Era.THIRD, new ArrayList<>());

        for(DevelopmentCard card : deck){

            eraCardMap.get(card.getEra()).add(card);

        }

        for(Era era : Era.values()){

            Collections.shuffle(eraCardMap.get(era)); // carte mischiate rispetto all'era

            for(DevelopmentCard card : eraCardMap.get(era)){

                orderedDeck.add(card);
            }
        }

        return orderedDeck;

    }


    private GameBoard loadGameBoardFromFile(int numberOfPlayers) throws IOException {

        return new ObjectDeserializer().getGameBoard(numberOfPlayers);
    }


    private Map<Era, List<ExcommunicationTile>> getExcommunicationTilesFromFile() throws FileNotFoundException {

        return new ObjectDeserializer().getExcommunicationTiles();
    }

    private ArrayList<LeaderCard> getLeaderCardsFromFile() throws FileNotFoundException {

        return new ObjectDeserializer().getLeaderCards();
    }

    public void setExcommunicationTiles(){

        try {
            excommunicationTileMap = getExcommunicationTilesFromFile();
        } catch (FileNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }

        ExcommunicationTile tileFirstEra = getRandomTile(Era.FIRST);
        ExcommunicationTile tileSecondEra = getRandomTile(Era.SECOND);
        ExcommunicationTile tileThirdEra = getRandomTile(Era.THIRD);


        gameBoard.getExcommunicationLane().setExcommunicationLane(tileFirstEra, tileSecondEra, tileThirdEra);

        try {
            model.notifyObserver(new ExcommunicationChange(tileFirstEra.getUrl(), tileSecondEra.getUrl(), tileThirdEra.getUrl()));
        } catch (Exception e) {
            LOGGER.info((Supplier<String>) e);
        }
    }

    private ExcommunicationTile getRandomTile(Era era) {

        Random randomGenerator = new Random();

        int size = excommunicationTileMap.get(era).size();

        int randomIndex = randomGenerator.nextInt(size);

        return excommunicationTileMap.get(era).get(randomIndex);
    }


    public void setLeaderCards() {

        try {
            leaderCards = getLeaderCardsFromFile();
        } catch (FileNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }

        Collections.shuffle(leaderCards);

        for (Player player : model.getTurnOrder()) {

            ArrayList<LeaderCard> playerLeaderCards = new ArrayList<>();

            for (int i = 0; i < 4; i++) {

                LeaderCard leaderCard = leaderCards.remove(0);

                playerLeaderCards.add(leaderCard);

            }

            player.setLeaderCards(playerLeaderCards);

        }
    }



    public void setGoodsForPlayers() {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("goodsForPlayerSetup");
        } catch (FileNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(new TypeToken<EnumMap<GoodType, Integer>>() {
                }.getType(),
                new EnumMapInstanceCreator<GoodType, Integer>(GoodType.class)).create();

        Type listType = new TypeToken<ArrayList<GoodSet>>(){}.getType();

        ArrayList<GoodSet> goodsReceivedList =  gsonBuilder.create().fromJson(fileReader, listType);// TODO: parametrizzare i punti da assegnare, da file ricevo ArrayList di coins, dobbiamo fare un file di IMPOSTAZIONI iniziali

        for(int i = 0; i < players.size(); i++){

            players.get(i).updateGoodSet(goodsReceivedList.get(i));

        }

    }

    private void setGameStatus() {

        model.setGameBoard(gameBoard);

        model.setOrderedDecks(orderedDecks);

        model.setTurnOrder(players);

        model.setPawns(players);

        model.setCurrentPlayer(players.get(0));

        model.setCurrentEra(Era.FIRST);

        model.setCurrentRound(1);

        model.setCurrentTurn(1);

        try {
            model.setBonusTileMap(loadBonusTilesFromFile());
        } catch (FileNotFoundException e) {
            LOGGER.info((Supplier<String>) e);
        }

    }


    public Model getModel() {
        return model;
    }

    private Map<Integer, BonusTile> loadBonusTilesFromFile() throws FileNotFoundException {

        return new ObjectDeserializer().getBonusTiles();

    }
}
