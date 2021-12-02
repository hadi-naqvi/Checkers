/**
 * [ICS4U] Checkers | Checkers.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

public class Checkers {
    public static void main(String[] args) {
        Game checkers = new Game();
        boolean playerOneTurn = true;

        // Main program/game-loop
        do {
            checkers.initializeBoard();
            checkers.printBoard();
            while (true) {
                String marker = "";
                if (playerOneTurn) {
                    marker = "\u001B[33mO\u001B[0m";
                }
                else {
                    marker = "\u001B[36mO\u001B[0m";
                }

                checkers.printBoard();
                Piece piece = checkers.getMovePiece(marker);
                if (checkers.canJump(piece)) {
                    do {
                        checkers.printBoard();
                        System.out.println("You have outstanding jump(s) to make.");
                        int[] coordinates = checkers.getMovePos(piece);
                        checkers.makeMove(piece, coordinates);
                    } while(checkers.canJump(piece));
                }
                else {
                    checkers.printBoard();
                    int[] coordinates = checkers.getMovePos(piece);
                    checkers.makeMove(piece, coordinates);
                }

                if (checkers.checkWin(marker)) {
                    checkers.printBoard();
                    System.out.println("Player " + marker + " has won the game!");
                    break;
                }

                playerOneTurn = !playerOneTurn;

            }
        } while(checkers.replayPrompt() == true);
    }
}