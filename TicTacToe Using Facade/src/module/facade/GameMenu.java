package module.facade;

import module.exceptions.InvalidChoiceException;
import module.gamemodel.Game;
import module.handler.InputHandler;
import module.playermodel.ComputerPlayer;
import module.playermodel.HumanPlayer;

public class GameMenu {

    private final InputHandler inputHandler;

    private static final int HUMAN_VS_HUMAN = 1;
    private static final int HUMAN_VS_COMPUTER = 2;
    private static final int EXIT = 3;

    private static final int REPLAY = 1;
    private static final int MAIN_MENU = 2;
    private static final int POST_GAME_EXIT = 3;

    public GameMenu() {
        this(new InputHandler());
    }

    public GameMenu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void start() {
        while (true) {
            try {
                System.out.println("\n==== TIC TAC TOE GAME ====");
                System.out.println("1. Human vs Human");
                System.out.println("2. Human vs Computer");
                System.out.println("3. Exit");

                int choice = inputHandler.readInt("Select option: ");
                if (choice == EXIT) {
                    System.out.println("Thank you for playing!");
                    return;
                }

                if (choice != HUMAN_VS_HUMAN && choice != HUMAN_VS_COMPUTER) {
                    throw new InvalidChoiceException("Invalid choice! Select between 1 and 3.");
                }

                int size = inputHandler.readPositiveInt("Enter board size (eg. 3 for 3x3 and 4 for 4x4): ");
                Game game = createGame(choice, size);

                while (true) {
                    game.start();

                    System.out.println("\nWhat would you like to do next?");
                    System.out.println("1. Replay");
                    System.out.println("2. Main Menu");
                    System.out.println("3. Exit");

                    int postGameChoice = inputHandler.readInt("Select option: ");
                    if (postGameChoice == REPLAY) {
                        continue;
                    }

                    if (postGameChoice == MAIN_MENU) {
                        break;
                    }

                    if (postGameChoice == POST_GAME_EXIT) {
                        System.out.println("Thank you for playing!");
                        return;
                    }

                    System.out.println("Invalid choice! Select between 1 and 3.");
                }

            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Game createGame(int choice, int size) {
        if (choice == HUMAN_VS_HUMAN) {
            String player1 = inputHandler.readPlayerName("Enter Player 1 name: ");
            String player2 = inputHandler.readPlayerName("Enter Player 2 name: ");
            return new Game(
                    new HumanPlayer(player1, 'X'),
                    new HumanPlayer(player2, 'O'),
                    size,
                    inputHandler
            );
        }

        String player = inputHandler.readPlayerName("Enter your name: ");
        return new Game(
                new HumanPlayer(player, 'X'),
                new ComputerPlayer('O'),
                size,
                inputHandler
        );
    }
}

