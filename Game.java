/**
 * [ICS4U] Checkers | Game.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

public class Game {
    // Attributes of Game object
    private Board checkerboard;
    private int turns;
    
    /**
     * Constructor method for Game object which initializes its attributes
     */
    public Game() {
        this.checkerboard = new Board();
        this.turns = 0;
    }

    /**
     * Initializes the checkerboard by instantiating and storing 24 pieces and storing 40 empty spaces/cells as null
     */
    public void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // If the 
                if (row <= 2 && (col - row + 4) % 2 == 0) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[33mX\u001B[0m"));
                }
                //
                else if (row >= 5 && (col - row + 7) % 2 == 1) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[31mO\u001B[0m"));
                }
                //
                else {
                    this.checkerboard.setPiece(new Pawn(row, col, "."));
                }
            }
        }
    }

    public void printBoard() {
        this.checkerboard.print();
    }

    public boolean checkWin() {

    }

    public boolean canJump() {

    }

    public Piece getMovePiece() {

    }

    public int[][] getMovePos() {

    }

    public void capturePiece() {

    }

    public boolean promptReplay() {

    }
}