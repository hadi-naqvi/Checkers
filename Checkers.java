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
                int[] coordinates = {4-1, 2-1};
                checkers.makeMove(piece, coordinates);

                /*checkers.printBoard();
                Piece piece2 = checkers.getMovePiece("\u001B[36mX\u001B[0m");
                int[] coordinatess = checkers.getMovePos(piece);
                checkers.makeMove(piece2, coordinatess);*/
            }
        } while(checkers.replayPrompt() == true);
    }
}