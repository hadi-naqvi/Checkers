/**
 * [ICS4U] Checkers | Checkers.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

public class Checkers {
    public static void main(String[] args) {
        Game checkers = new Game();

        // Main program/game-loop
        do {
            checkers.initializeBoard();
            while (true) {
                checkers.printBoard();
                Piece piece = checkers.getMovePiece("\u001B[33mX\u001B[0m");
                int[] coordinates = checkers.getMovePos(piece);
                checkers.makeMove(piece, coordinates);
                /*if (checkers.checkWin("\u001B[33mX\u001B[0m")) {
                    System.out.println("Player 1 wins!");
                    break;
                }*/

                checkers.printBoard();
                Piece piece2 = checkers.getMovePiece("\u001B[36mX\u001B[0m");
                int[] coordinatess = checkers.getMovePos(piece2);
                checkers.makeMove(piece2, coordinatess);
                checkers.transformPossibleKings(piece2);
                /*if (checkers.checkWin("\u001B[36mX\u001B[0m")) {
                    System.out.println("Player 2 wins!");
                    break;
                }*/
            }
        } while(checkers.replayPrompt() == true);
    }
}