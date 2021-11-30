/**
 * [ICS4U] Checkers | Game.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

import java.util.Scanner;
import java.util.Arrays;

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
                // Places all of player X's pawns
                if (row <= 2 && (col - row + 4) % 2 == 0) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[33mX\u001B[0m"));
                }
                // Places all of player O's pawns
                else if (row >= 5 && (col - row + 7) % 2 == 1) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[31mO\u001B[0m"));
                }
                // Sets every empty cell/square on the checkerboard as null
                else {
                    this.checkerboard.setPiece(null);
                }
            }
        }
    }

    /**
     * Outputs the checkerboard on-screen in a visually appealing manner
     */
    public void printBoard() {
        this.checkerboard.print();
    }

    public boolean checkWin() {

    }

    public boolean canJump() {

    }

    /**
     * This method prompts the user to enter the coordinates of the piece which they would like to move
     * @param marker The marker of the piece which represents which player is making the move
     * @return The piece the user selected to move
     */
    public Piece getMovePiece(String marker) {
        boolean validPiece = false;
        int[] coordinates;
        
        // Continuously prompts the user to enter coordinates until they enter coordinates for a valid piece
        do {
            System.out.println("Enter the coordinates of the piece you would like to move (Ex. 1,5):");
            coordinates = Arrays.stream(new Scanner(System.in).nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

            // Checks if the 
            if (!this.checkerboard.isCellEmpty(coordinates[0], coordinates[1])) {
                if (this.checkerboard.getPiece(coordinates[0], coordinates[1]) != null && this.checkerboard.getPiece(coordinates[0], coordinates[1]).getMarker() == marker) {
                    for (int[] move : this.checkerboard.getPiece(coordinates[0], coordinates[1]).getPossibleMoves()) {
                        if (this.checkerboard.getPiece(move[0], move[1]) != null) {
                            validPiece = true;
                        }
                    }
                    for (int[] jump : this.checkerboard.getPiece(coordinates[0], coordinates[1]).getPossibleJumps()) {
                        if (this.checkerboard.getPiece(jump[0], jump[1]) != null) {
                            validPiece = true;
                        }
                    }
                }
            }
        } while(!validPiece);

        return this.checkerboard.getPiece(coordinates[0], coordinates[1]);
    }

    public int[][] getMovePos(Piece piece) {

    }

    public void capturePiece() {

    }

    public boolean promptReplay() {

    }
}