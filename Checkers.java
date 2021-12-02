/**
 * [ICS4U] Checkers | Checkers.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, Andrew Kwok
 * Teacher: Mr. Ho
 */

public class Checkers {
    public static void main(String[] args) {
        Game checkers = new Game();
        boolean playerOneTurn = true;
        String marker;
        int[] movePos;

        // Main program/game-loop
        do {
            checkers.initializeBoard();
            checkers.printBoard();
            while (true) {
              if (playerOneTurn) {
                    marker = "\u001B[33mO\u001B[0m";
                } else {
                    marker = "\u001B[36mO\u001B[0m";
                }

                Piece piece = checkers.getMovePiece(marker);
                if (checkers.canJump(piece)) {
                    do {
                        System.out.println("You have outstanding jump(s) to make.");
                        movePos = checkers.getMovePos(piece);
                        checkers.makeMove(piece, movePos);
                        checkers.printBoard();

                    } while (checkers.canJump(piece));
                } else {
                    movePos = checkers.getMovePos(piece);
                    checkers.makeMove(piece, movePos);
                    checkers.printBoard();
                }

                if (checkers.checkWin(marker)) {
                    checkers.printBoard();
                    System.out.println("Player " + marker + " has won the game!");
                    break;
                }

                playerOneTurn = !playerOneTurn;

            }
        } while (checkers.replayPrompt());
    }
}