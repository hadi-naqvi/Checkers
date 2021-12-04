/**
 * [ICS4U] Checkers | Board.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, Andrew Kwok
 * Teacher: Mr. Ho
 */

public class Board {
    // The board is a 2D Piece array stored as an attribute of a Board object
    private Piece[][] board;

    /**
     * Constructor method which initializes the board attribute
     */
    public Board() {
        // The dimensions of the 2D array are the same as the dimensions of a checkerboard which is 8x8
        this.board = new Piece[8][8];
    }

    /**
     * This getter method returns the Piece object at a requested location on the checkerboard
     * @param row The row of the piece
     * @param col The column of the piece
     * @return The piece being requested
     */
    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }

    /**
     * This setter method moves a specified piece by changing its location in the 2D board array which stores the pieces
     * @param piece The piece being moved
     */
    public void setPiece(Piece piece) {
        this.board[piece.getRow()][piece.getCol()] = piece;
    }

    /**
     * This setter method removes a specified piece on the checkerboard and sets it to null (empty cell)
     * @param row The row of the piece being removed
     * @param col The column of the piece being removed
     */
    public void removePiece(int row, int col) {
        this.board[row][col] = null;
    }

    /**
     * This method determines whether a given square/cell on the checkerboard is empty or not
     * @param row The row of the specified cell/square
     * @param col The column of the specified cell/square
     * @return If the cell is empty
     */
    public boolean isCellEmpty(int row, int col) {
        return this.board[row][col] == null;
    }

    /**
     * This method prints the current pieces stored in the 2D board array in a visually appealing manner in the console
     */
    public void print() {
        // The corresponding letter labels for each row is stored in an array
        String[] rowLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};

        // Labels for each column are printed on-screen to make it easier for the players to make their moves
        System.out.println("   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |\n----------------------------------------------------");
        for (int row = 0; row < 8; row++) {
            // Labels for each row are printed on-screen to make it easier for the players to make their moves
            System.out.print(" " + rowLabels[row] + " |  ");
            for (int col = 0; col < 8; col++) {
                if (this.board[row][col] == null) {
                    // An open space is outputted as a blank space indicating a move can be made there
                    System.out.print("   |  ");
                } else if (this.getPiece(row, col).type().equals("king")) {
                    // Kings are ouputted as the letter 'K' with the player's corresponding colour
                    System.out.print(this.board[row][col].getMarker().substring(0, 5) + "K" + "\u001B[0m" + "  |  ");
                } else {
                    // Pieces on the board are outputted as their marker (Colour and the symbol O)
                    System.out.print(this.board[row][col].getMarker() + "  |  ");
                }
            }
            System.out.println("\n----------------------------------------------------");
        }
    }
}