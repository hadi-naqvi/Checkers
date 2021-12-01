/**
 * [ICS4U] Checkers | Pawn.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;

public class Pawn extends Piece {
    /**
     * Constructor method for Pawn objects
     * @param row The row number when the Pawn is initialized
     * @param col The column number when the Pawn is initialized
     * @param marker The marker when the Pawn is initialized
     */
    public Pawn(int row, int col, String marker) {
        super(row, col, marker);
        this.type = "pawn";
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible moves that the Pawn can make
     * @return The list of possible moves that can be made
     */
    @Override
    public ArrayList<int[]> getPossibleMoves() {
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        int[][] moveLocations;

        if(this.marker.equals("\u001B[36mX\u001B[0m")){
            moveLocations = new int[][]{{-1, -1}, {-1, 1}};
        } else {
            moveLocations = new int[][]{{1, 1}, {1, -1}};
        }

        for (int[] coordinates: moveLocations) {
            if (this.row + coordinates[0] <= 7 && this.row + coordinates[0] >=0 && this.col + coordinates[1] <=7 && this.col + coordinates[1] >=0){
                coordinates[0] += this.row;
                coordinates[1] += this.col;
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
     * This method returns a 2D arraylist containing the coordinates of all the possible jumps that the Pawn can make
     * @return The list of possible jumps that can be made
     */
    @Override
    public ArrayList<int[][]> getPossibleJumps() {
        ArrayList<int[][]> possibleJumps = new ArrayList<int[][]>();
        int[][][] moveLocations;

        if(this.marker.equals("\u001B[36mX\u001B[0m")){
            moveLocations = new int[][][]{{{-2, -2}, {-1, -1}}, {{-2, 2}, {-1, 1}}};
        } else {
            moveLocations = new int[][][]{{{2, 2}, {1,1}}, {{2, -2}, {1, -1}}};
        }

        for (int[][] coordinates: moveLocations) {
            if (this.row + coordinates[0][0] <= 7 && this.row + coordinates[0][0] >=0 && this.col + coordinates[0][1] <=7 && this.col + coordinates[0][1] >=0){
                coordinates[0][0] += this.row;
                coordinates[0][1] += this.col;
                coordinates[1][0] += this.row;
                coordinates[1][1] += this.col;
                possibleJumps.add(coordinates);
            }
        }
        
        return possibleJumps;
    }
}