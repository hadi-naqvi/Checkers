/**
 * [ICS4U] Checkers | Board.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

 public class Board {
    // The board is a 2D Piece array stored as an attribute of a Board object (Private/Encapsulated attribute with getter/setter methods)
    private Piece[][] board;

    // Constructor method which initliazes the board attribute
    public Board() {
        // The dimensions of the 2D array are the same as the dimensions of a checkerboard which is 8x8
        this.board = new Piece[8][8];
    }

     /**
      * This getter method returns the Piece object at a request location on the checkerboard
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
        if (piece != null) {
            this.board[piece.getRow()][piece.getCol()] = piece;
        }
    }

    /**
      * This setter method moves a specified piece by changing its location in the 2D board array which stores the pieces
      * @param piece The piece being moved
      */
    public void setPiece(int row, int col) {
        this.board[row][col] = null;
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
        if (this.board[row][col] == null) {
            return true;
        }
        return false;
    }

    /**
     * This method prints the current pieces stored in the 2D board array in a visually appealing manner in the console
     */
    public void print() {
        // Labels for each column are printed on-screen to make it easier for the players to make their moves
        System.out.println("   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |\n----------------------------------------------------");
        for (int row = 0; row < 8; row++) {
            // Labels for each row are printed on-screen to make it easier for the players to make their moves
            System.out.print(" " + (row + 1) + " |  ");
            for (int col = 0; col < 8; col++) {
                if (this.board[row][col] == null) {
                    System.out.print("   |  ");
                    continue;
                }
                else if (this.getPiece(row, col).type().equals("king")) {
                    System.out.print(this.board[row][col].getMarker().substring(0, 5) + "K" + "\u001B[0m" + "  |  ");
                }
                else {
                    System.out.print(this.board[row][col].getMarker() + "  |  ");
                }
            }
            System.out.println("\n----------------------------------------------------");
        }
    }
}