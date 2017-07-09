# Project Template - Prova Finale (Ingegneria del Software modified)

 GAME SETUP:

 In order to create a new game: 
 
 1) first you have to launch the main method of the Server class in the Server package. 
 
 2) Then you have to launch the main method of the Client class in the Client Package. 
 
 After you have done these two steps, the CLI of the clients launched is self-explanatory:
 
 3) choose which interface you want to use
 
 4) do the login
 
 5) choose which distribution you would like to use
 
 After this, you have to wait until the timer wakes up (it starts with the second client logged as required) or until there are four players for the match.
 
 6) you have to choose the bonus tile for the player (in CLI the valid input is "bonus tile (number)" --> example: "bonus tile 0")
 
 After this, the game has started:
 
 
 
 
 
 CLI INSTRUCTIONS:
 
 during the game, the player is always in a specific playerState, so if you want to know which instruction are allowed in your current playerState write "help".
 
 
 
 
 
 GUI INPUTS:
 
 1) ACTION: the first player of the turnOrder has to throw dices (button "throw dices"), after this the player is in the state "DO ACTION", it is its turn -->
 
 --> if you want to see all the valid action for a familyPawn when it is your turn, click on the big coloured circle (it represents the pawn with the colour of the dice), 
 
 then you will see all the available actionSpaces marked with a green "V". If you want to zoom on a card, just click on it, if you want to add the pawn in 
 
 the available actionSpace, click on the green "V" over the actionSpace. After this, the player is in the End turn --> you can only use a leader card (button "use leader card")
 
 or click on the button "end turn" to end your turn.
 
 
 
 2) CHOOSE COST: if you choose a card with an alternative price and you have the possibility to pay each cost, a pop-up will appear and it will show you the description of the cost
 
 and the cost index related to insert in order to choose that cost.
 
 
 
 3) WORKACTION: if you select a workAction (HARVEST OR PRODUCTION) and you have sufficient workers to activate different cards, a pop-up will appear asking you how many workers do you want
 
 to add to the action and for each workers amount you will see which cards you can activate (example you have 3 yellow card with action value : 3, 5, 6. You use a pawn with value 1 and you have 7 workers -->
 
 --> the pop up will display: workers amount: 2) " first card description"; workers amount: 4) "first and second card descriptions"; workers amount: 5) "first second and third card descriptions").
 
 
 
 4) CHOOSE PAY TO OBTAIN EFFECT: if you activate a payToObtain card that has two possible payToObtain Effects and you have enough resources to activate all of them, a pop-up will appear
 
 asking you which effect you prefer --> you have to insert the index of the effect in the text box (like in CHOOSE COST);
 
 
 
 5) LEADER CARDS: if you want to use a leader card (activate or discard), click on the button "use leader cards". This button will activate the buttons for each leader card (obviously the Activate 
 
 button is disabled if you do not have the right requirements). If you click on "use leader card" but you do not want to do anything with the leader card, click again on "use Leader card" 
 
 in order to go back in your main current playerState.
 
 
 
 6) PLAYER CARDS AND TRACKS: on the upper side of the personal board you can see three tabs: "tab1", "tab2", "tracks" --> if you click on it you will see the cards owned by the player
 
 and the current situation of the tracks (we decided to showing a ranking instead of adding the pawn on the three tracks because, for us, it is more intuitive to see a classification).
 
 
 
 7) EXCOMMUNICATION: if you have enough faith Points, when it is time for the CHURCH_RELATIONSHIP you will see a pop-up that asks you if you want to pray for the pope or to be excommunicated.
 
 If you do not have enough faith point you will be excommunicated without any communication.
 
 
 
 
 
 
 
 DISCONNECTION
 
 We have not implemented the advanced requirements, so we handled only the disconnection of the player due to client process closed. If you want to try the disconnection and reconnection,
 
 kill a client process, run another client and do the right login (otherwise another match will be created obviously). when you are logged, you have to wait until the server handles the end of 
 
 a player's turn, then you are back in your current game and all the clients are notified about your return. When a client is disconnected, the server sets the playerState of the client in "SUSPENDED",

 it notifies all the clients about the disconnection and it sets the disconnected client's playerState in "SUSPENDED" (the controller skips its moves).
 
 If the number of players on-line is lesser than 2, the server will launch the endGame procedure.
 
 
 
 
 
 
 END GAME
 
 When the game is ended, all the clients are notified with a message about who won, then the client process will terminate automatically after few seconds.
 
 
 
 
 
 
 
 CONFIGURATION:
 
 GameBoard : all the gameBoard files used in the setup are in the "gameBoards" directory;
 Cards: all the gameBoard files used in the setup are in the "cards" directory;
 ExcommunicationTiles: all the gameBoard files used in the setup are in the "excommunicationTiles" directory;
 LeaderCards: all the gameBoard files used in the setup are in the "LeaderCards" directory;
 BonusTiles: all the gameBoard files used in the setup are in the "bonusTiles" directory;
 
 Timer: the timer file is in the "timer" directory, if you want to change the login timer you have to modify this file (now is setted on 3 minutes);
 
 TimerMove: the timer for the player turn is in the "timerMove" directory, if you want to change the Turn timer you have to modify this file(now is setted on 5 minutes).
 
 
 
 
 
 
 
 CODE NOTES
 
 TESTS: we tested the 80% of the model package(as suggested during our discussions during the laboratory ) and other classes of the controller that where particularly critical.
 
 SONAR: we used Sonar to eliminates more bugs as possible and to have useful suggestions for our code. Then we used sonar for the elimination of duplicated code (it was very helpful and instructive).
 
 DOCUMENTATION: we tried to document more classes and methods as possible if it was needed for the comprehension of the code.
 
 
 
 
 ACKNOWLEDGEMENTS
 
 Last but not least, we sincerely want to thank you and all the tutors for the support and for all the feedbacks that we received from you during these two months. It was very useful and illuminating. 

 We hope to have maintained and respected your expectations.
 
 
 
 
 
 
 
 
 
 

  ```
