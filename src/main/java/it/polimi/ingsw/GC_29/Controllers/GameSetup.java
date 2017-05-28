package it.polimi.ingsw.GC_29.Controllers;

import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.Player.Player;

import java.util.*;

/**
 * Created by Christian on 28/05/2017.
 */
public class GameSetup {

    private final int CARDSPERDECK = 24;
    private final int NUMBEROFCARDS = 96;

    private int numberOfPlayers;

    private GameStatus gameStatus;

    private GameBoard gameBoard;

    private EnumMap<CardColor, ArrayDeque<DevelopmentCard>> orderedDecks;

    private EnumMap<Era, ArrayList<ExcommunicationTile>> excommunicationTileMap;

    private Player players[];


    public GameSetup(int numberOfPlayers) {

        this.numberOfPlayers = numberOfPlayers;
        this.gameBoard = new GameBoard(numberOfPlayers);
        this.gameStatus = GameStatus.getInstance();
        this.players = new Player[numberOfPlayers];

        this.orderedDecks = new EnumMap<>(CardColor.class);
        this.excommunicationTileMap = new EnumMap<>(Era.class);
    }



    public void init(){


        gameBoard = getGameBoardFromFile(numberOfPlayers);

        gameStatus.setGameBoard(gameBoard);

        for(CardColor color : CardColor.values()){
            this.orderedDecks.put(color, getDeckFromFile(color));
        }

        for(Era era : Era.values()){
            this.excommunicationTileMap.put(era, getExcommunicationTilesFromFile(era));
        }

        ExcommunicationTile tilefirsEra = getRandomTile(Era.FIRST);
        ExcommunicationTile tileSecondEra = getRandomTile(Era.SECOND);
        ExcommunicationTile tileThirdEra = getRandomTile(Era.THIRD);

        gameBoard.getExcommunicationLane().setExcommunicationLane(tilefirsEra, tileSecondEra, tileThirdEra);




    }

    private ExcommunicationTile getRandomTile(Era era) {

        Random randomGenerator = new Random();

        int size = excommunicationTileMap.get(era).size();

        int randomIndex = randomGenerator.nextInt(size);

        return excommunicationTileMap.get(era).get(randomIndex);
    }


    private GameBoard getGameBoardFromFile(int numberOfPlayers) {

        //TODO: carica con Gson in base al numero di giocatori, abbiamo 3 file ognuno con una gameboard già pronta in base al numero di giocatori

        return null;
    }

    public DevelopmentCard[] getCardsFromFile() { // TODO: implementare con Gson
        return null;
    }

    /**
     * this method get the specific deck from file, it shuffles the card of the deck compared to the Era and return a deck
     * with the structure of a stack, ( we have the card of the thirdEra, then the secondEra and at the end the firstEra) --> useful to pick
     * a card with the pop() method
     */
    private ArrayDeque<DevelopmentCard> getDeckFromFile(CardColor color) {

        ArrayDeque<DevelopmentCard> deck = new ArrayDeque<DevelopmentCard>();

        // TODO: assegna al deck il deck caricato con Gson

        EnumMap<Era, ArrayList<DevelopmentCard>> eraCardMap = new EnumMap<>(Era.class);

        for(DevelopmentCard card : deck){

            eraCardMap.get(card.getEra()).add(card);

        }

        for(Era era : Era.values()){

            Collections.shuffle(eraCardMap.get(era)); // carte mischiate rispetto all'era

            for(DevelopmentCard card : eraCardMap.get(era)){

                deck.addFirst(card);
            }
        }

        return deck;

    }


    private ArrayList<ExcommunicationTile> getExcommunicationTilesFromFile(Era era) {

        // TODO: carica con Gson il file delle excommunicationTile rispetto all'era

        return null;

    }

}
