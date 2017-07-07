package it.polimi.ingsw.GC_29.Controllers;

/**
 * Created by Lorenzotara on 01/06/17.

public class GameControllerTest {

    BonusTile bonusTile = new BonusTile(new ObtainEffect(new GoodSet()), new ObtainEffect(new GoodSet()) );

    Player player1 = new Player("Lorenzo", PlayerColor.BLUE, new PersonalBoard(bonusTile, 6), null);

    Player player2 = new Player("Alberto", PlayerColor.RED, new PersonalBoard(bonusTile, 6), null);

    Player player3 = new Player("Christian", PlayerColor.YELLOW, new PersonalBoard(bonusTile, 6), null);

    Player player4 = new Player("Gianmario", PlayerColor.GREEN, new PersonalBoard(bonusTile, 6), null);

    ArrayList<Player> players = new ArrayList<>();

    @Test
    public void testEndGame() throws Exception {


        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        GameController testGameController = new GameController();

        player1.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,0,1,1));
        player2.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,2,1,1));
        player3.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,4,1,1));
        player4.getActualGoodSet().addGoodSet(new GoodSet(1,1,1,1,4,1,1));

        testGameController.endGame();

        Model.getInstance().getTurnOrder().clear();

    }

    @Test
    public void testSetNewTurnOrder() throws Exception {


        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

        GameSetup testGameSetup = new GameSetup(players);

        testGameSetup.init();

        GameController testGameController = new GameController();

        Model model = Model.getInstance();

        GameBoard gameBoard = model.getGameBoard();

        gameBoard.getCouncilPalace().setTurnOrder(player1.getPlayerColor());
        gameBoard.getCouncilPalace().setTurnOrder(player2.getPlayerColor());
        gameBoard.getCouncilPalace().setTurnOrder(player3.getPlayerColor());
        gameBoard.getCouncilPalace().setTurnOrder(player4.getPlayerColor());

        System.out.println("New TurnOrder: " );
        for (int i = 0; i < + gameBoard.getCouncilPalace().getTurnOrder().length; i++) {
            System.out.println(gameBoard.getCouncilPalace().getTurnOrder()[i]);
        }

        System.out.println("Old TurnOrder: " + model.getTurnOrder());

        testGameController.setNewTurnOrder();

        System.out.println("New TurnOrder: " + model.getTurnOrder());

        Model.getInstance().getTurnOrder().clear();

    }

}*/
