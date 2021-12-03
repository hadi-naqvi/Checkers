/**
 * [ICS4U] Checkers | King.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, Andrew Kwok
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;

public class King extends Piece {
    /**
     * Constructor method for King objects
     * @param row The row number when the King is initialized
     * @param col The column number when the King is initialized
     * @param marker The marker when the King is initialized
     */
    public King(int row, int col, String marker) {
        super(row, col, marker);
        this.type = "king";
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible moves that the King can make given its current position
     * @return The list of possible moves that can be made
     */
    @Override
    public ArrayList<int[]> getPossibleMoves() {
        // Initializes an arraylist of integer arrays which will store the [row, col] positions of every possible move location
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();

        // The displacement of the potential possible moves are diagonally in all four directions by a unit of 1 for Kings
        int[][] moveLocations = {
                {-1, -1}, {-1, 1}, {1, 1}, {1, -1}
        };

        // Checks for potential moves in every direction of movement to see if the move is within bounds, and adds it to the list of possible moves
        for (int[] coordinates : moveLocations) {
            if (this.row + coordinates[0] <= 7 && this.row + coordinates[0] >= 0 && this.col + coordinates[1] <= 7 && this.col + coordinates[1] >= 0) {
                // Adds the displacement of the move to the piece's current position to determine and add the coordinates of the possible move
                coordinates[0] += this.row;
                coordinates[1] += this.col;
                possibleMoves.add(coordinates);
            }
        }

        // All possible moves found are returned
        return possibleMoves;
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible jumps that the King can make given its current position
     * @return The list of possible jumps that can be made
     */
    @Override
    public ArrayList<int[][]> getPossibleJumps() {
        // Initializes an arraylist of 2D integer arrays which will store the [row, col] positions of every possible jump location along with the location of the piece being jumped over
        ArrayList<int[][]> possibleJumps = new ArrayList<int[][]>();

        // The displacement/direction of movement of the King and the distance between the King and the piece being jumped/captured is stored in a 3D integer array
        int[][][] moveLocations = {
                {{-2, -2}, {-1, -1}}, {{-2, 2}, {-1, 1}}, {{2, 2}, {1, 1}}, {{2, -2}, {1, -1}}
        };

        // Checks for potential jumps in every direction of movement to see if the jump is within bounds, and adds it to the list of jumps
        for (int[][] coordinates : moveLocations) {
            if (this.row + coordinates[0][0] <= 7 && this.row + coordinates[0][0] >= 0 && this.col + coordinates[0][1] <= 7 && this.col + coordinates[0][1] >= 0) {
                // Adds the displacement values to the piece's current position and adds the positions (the jump, and piece being jumped) to the list of possible jumps
                coordinates[0][0] += this.row;
                coordinates[0][1] += this.col;
                coordinates[1][0] += this.row;
                coordinates[1][1] += this.col;
                possibleJumps.add(coordinates);
            }
        }

        // All possible jumps found are returned (With the location of the jump, and the piece being captured in a 2D array all stored in an Arraylist)
        return possibleJumps;
    }
}