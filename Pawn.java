/**
 * [ICS4U] Checkers | Pawn.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

public class Pawn extends Piece {
    /**
     * Constructor method for Pawn objects
     * @param row The row number when the Pawn is initialized
     * @param col The column number when the Pawn is initialized
     * @param marker The marker when the Pawn is initialized
     */
    public Pawn(int row, int col, String marker) {
         super(row, col, marker);
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible moves that the Pawn can make
     * @param board The 2D array which stores the pieces on the checkerboard
     * @return The list of possible moves that can be made
     */
    @Override
    public ArrayList<ArrayList<Integer>> getPossibleMoves(Piece[][] board) {
        ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<ArrayList<Integer>>();

        return possibleMoves;
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible jumps that the Pawn can make
     * @param board The 2D array which stores the pieces on the checkerboard
     * @return The list of possible jumps that can be made
     */
    @Override
    public ArrayList<ArrayList<Integer>> getPossibleJumps(Piece[][] board) {
        ArrayList<ArrayList<Integer>> possibleJumps = new ArrayList<ArrayList<Integer>>();

        return possibleJumps;
    }
}