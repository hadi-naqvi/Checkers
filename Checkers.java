/**
 * [ICS4U] Checkers | Checkers.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, Andrew Kwok
 * Teacher: Mr. Ho
 */

public class Checkers {
    public static void main(String[] args) {
        // Initialization of Game object
        Game checkers = new Game();

        // Main program loop
        do {
            // Introductory message
            System.out.println("Welcome to Checkers!\nInfo/Rules:\n1. You must enter coordinates as (row,column)\n2. Forced-jumps are enabled\n3. You can only move diagonally");

            // Initialization of key game variables which control if the game is running andwhich player's turn it is
            boolean gameRunning = true;
            boolean playerOneTurn = true;
            String marker;

            // Initializes the board and prints it at the start of a new game
            checkers.initializeBoard();
            checkers.printBoard();

            // Main game loop
            while (gameRunning) {
                // Determines the marker of the current player's turn
                if (playerOneTurn) {
                    marker = "\u001B[36mO\u001B[0m";
                } else {
                    marker = "\u001B[33mO\u001B[0m";
                }

                // Prompts the user to enter coordinates for a piece they would like to move and where they would like to move it
                Piece piece = checkers.getMovePiece(marker);
                // If the player can jump, they are forced to keep jumping until no more jumps can be made (Forced jumps and multi-jumps)
                if (checkers.canJump(piece)) {
                    do {
                        System.out.println("NOTE: You have an outstanding jump you must make.");
                        checkers.makeMove(piece, checkers.getMovePos(piece));
                        // Prints the game-board to reflect the changes made after the jump
                        checkers.printBoard();

                    } while (checkers.canJump(piece));
                } else {
                    checkers.makeMove(piece, checkers.getMovePos(piece));
                    // Prints the game-board to reflect the changes made after the move
                    checkers.printBoard();
                }

                // After the player makes a move/jump(s) the game checks if the player has won the game, and displays the results and exits the main gameloop if they won
                if (checkers.hasPlayerWon(marker)) {
                    checkers.printBoard();
                    System.out.println("Player " + marker + " has won the game!");
                    gameRunning = false;
                }

                // Changes the turn of the current player making a move (Next turn/other player moves next)
                playerOneTurn = !playerOneTurn;

            }
        } while (checkers.replay());

        // Closes the scanner object used for user-input in the program
        checkers.closeScanner();
    }
}