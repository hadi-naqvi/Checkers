/**
 * [ICS4U] Checkers | Piece.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, Andrew Kwok
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;

public abstract class Piece {
    // Attributes of a Piece object
    protected int row;
    protected int col;
    protected String marker;
    protected String type;

    /**
     * Constructor method for Piece objects
     * @param row The row number when the piece is initialized
     * @param col The column number when the piece is initialized
     * @param marker The marker when the piece is initialized
     */
    protected Piece(int row, int col, String marker) {
        this.row = row;
        this.col = col;
        this.marker = marker;
    }

    /**
     * This getter method returns the row number of a piece
     * @return The row of the piece
     */
    public int getRow() {
        return this.row;
    }

    /**
     * This getter method returns the column number of a piece
     * @return The column of the piece
     */
    public int getCol() {
        return this.col;
    }

    /**
     * This getter method returns the marker of a piece
     * @return The marker of the piece
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * This getter method returns the type of the piece
     * @return The piece type
     */
    public String type() {
        return this.type;
    }

    /**
     * This setter method changes the row number of a piece
     * @param row Row to be set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * This setter method changes the column number of a piece
     * @param col Column to be set
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * This method determines all possible moves that can be made for a piece given its position on the checkerboard
     * @return The list of possible moves that can be made
     */
    public abstract ArrayList<int[]> getPossibleMoves();

    /**
     * This method determines all possible jumps that can be made for a piece given its position on the checkerboard
     * @return The list of possible jumps that can be made
     */
    public abstract ArrayList<int[][]> getPossibleJumps();
}