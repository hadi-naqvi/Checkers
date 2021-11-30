/**
 * [ICS4U] Checkers | King.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

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
     * @param board The 2D array which stores the pieces on the checkerboard
     * @return The list of possible moves that can be made
     */
    @Override
    public ArrayList<ArrayList<Integer>> getPossibleMoves(Piece[][] board) {
        ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<ArrayList<Integer>>();

        return possibleMoves;
    }

    /**
     * This method returns a 2D arraylist containing the coordinates of all the possible jumps that the King can make
     * @param board The 2D array which stores the pieces on the checkerboard
     * @return The list of possible jumps that can be made
     */
    @Override
    public ArrayList<ArrayList<Integer>> getPossibleJumps(Piece[][] board) {
        ArrayList<ArrayList<Integer>> possibleJumps = new ArrayList<ArrayList<Integer>>();

        return possibleJumps;
    }
}