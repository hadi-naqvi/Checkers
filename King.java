/**
 * [ICS4U] Checkers | King.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
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
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible moves that the King can make
     * @return The list of possible moves that can be made
     */
    @Override
    public ArrayList<int[]> getPossibleMoves() {
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        int[][] moveLocations = {
                {-1, -1}, {-1, 1}, {1, 1}, {1, -1}
        };

        for (int[] coordinates: moveLocations) {
            if (row + coordinates[0] <= 7 && row + coordinates[0] >=0 && col + coordinates[1] <=7 && coordinates[1] >=0){
                    possibleMoves.add(coordinates);
            }
        }

        /*
        for (int[] coordinates: moveLocations) {
            if (row + coordinates[0] <= 7 && row + coordinates[0] >=0 && col + coordinates[1] <=7 && coordinates[1] >=0){
                if (board[row + coordinates[0]][col + coordinates[1]] == null){
                    possibleMoves.add(coordinates);
                }
            }
        }*/
        return possibleMoves;
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible jumps that the King can make
     * @return The list of possible jumps that can be made
     */
    @Override
    public ArrayList<int[]> getPossibleJumps() {
        ArrayList<int[]> possibleJumps = new ArrayList<int[]>();
        int[][] moveLocations = {
                {-2, -2}, {-2, 2}, {2, 2}, {2, -2}
        };

        for (int[] coordinates: moveLocations) {
            if (row + coordinates[0] <= 7 && row + coordinates[0] >=0 && col + coordinates[1] <=7 && coordinates[1] >=0){
                    possibleJumps.add(coordinates);
            }
        }

        /*for (int[] coordinates: moveLocations) {
            if (row + coordinates[0] <= 7 && row + coordinates[0] >=0 && col + coordinates[1] <=7 && coordinates[1] >=0){
                if (board[row + coordinates[0]][col + coordinates[1]] == null){
                    possibleJumps.add(coordinates);
                }
            }
        }*/

        return possibleJumps;
    }
}