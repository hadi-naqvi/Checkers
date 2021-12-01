/**
 * [ICS4U] Checkers | Game.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, and Andrew Kwok
 * Teacher: Mr. Ho
 */

import java.util.Scanner;
import java.util.Arrays;

public class Game {
    // Attributes of Game object
    private Board checkerboard;
    private int moves;
    
    /**
     * Constructor method for Game object which initializes its attributes
     */
    public Game() {
        this.checkerboard = new Board();
        this.moves = 0;
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
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[36mX\u001B[0m"));
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
        System.out.println("Number of Moves made: " + this.moves + " \n");
        this.checkerboard.print();
    }

    /**
     * This method determines whether a given player has won the game by checking if the opponent has no pieces left to move/jump with
     * @param marker The opponent's marker
     * @return If the given player has won the game
     */
    public boolean checkWin(String marker) {
        // Checks if the opponent can make a jump
        if (canJump(marker)) return false;

        // Iterates through every cell on the board and checks if the opponent has any pieces left on the board which can make a move
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = this.checkerboard.getPiece(row, col);
                if (piece.getMarker().equals(marker) && piece.getPossibleMoves().size() > 0) {
                    return false;
                }
            }
        }

        // Returns true if a jump or move cannot be made by the opponent
        return true;
    }

    /**
     * Checks if a given player has pieces which can make a jump on the checkerboard
     * @param marker The marker of the given player's pieces being checked
     * @return If the player can make a jump with one of their pieces
     */
    public boolean canJump(String marker) {
        // Iterates through every cell and checks if it contains one of the player's pieces and if it can make a jump or not
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = this.checkerboard.getPiece(row, col);
                if (piece.getMarker().equals(marker) && piece.getPossibleJumps().size() > 0) {
                    return true;
                }
            }
        }

        // Returns false if a jump cannot be made
        return false;
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
            coordinates[0]--;
            coordinates[1]--;

            // Checks if the 
            if (!this.checkerboard.isCellEmpty(coordinates[0], coordinates[1])) {
                if (this.checkerboard.getPiece(coordinates[0], coordinates[1]).getMarker() == marker) {
                    for (int[] move : this.checkerboard.getPiece(coordinates[0], coordinates[1]).getPossibleMoves()) {
                        if (this.checkerboard.getPiece(move[0], move[1]) == null) {
                            validPiece = true;
                        }
                    }
                    for (int[][] jump : this.checkerboard.getPiece(coordinates[0], coordinates[1]).getPossibleJumps()) {
                        if (this.checkerboard.getPiece(jump[1][0], jump[1][1]) != null 
                         && !this.checkerboard.getPiece(jump[1][0], jump[1][1]).getMarker().equals(marker)
                         && this.checkerboard.getPiece(jump[0][0], jump[0][1]) == null) {
                            validPiece = true;
                        }
                    }
                }
            }
        } while(!validPiece);

        return this.checkerboard.getPiece(coordinates[0], coordinates[1]);
    }

    public int[] getMovePos(Piece piece) {
        boolean validMove = false;
        int[] coordinates;

        // Continuously prompts the user to enter coordinates until they enter coordinates for a valid piece
        do {
            System.out.println("Enter the coordinates of the location you would like the piece to move to (Ex. 1,5):");
            coordinates = Arrays.stream(new Scanner(System.in).nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            coordinates[0]--;
            coordinates[1]--;

            // Checks if the
            if (this.checkerboard.isCellEmpty(coordinates[0], coordinates[1])) {
                for (int[] move : piece.getPossibleMoves()) {
                    if (coordinates[0] == move[0] && coordinates[1] == move[1] && this.checkerboard.getPiece(move[0], move[1]) == null){
                        validMove = true;
                    }
                }
                for (int[][] jump : piece.getPossibleJumps()) {
                    if (this.checkerboard.getPiece(jump[1][0], jump[1][1]) != null 
                    && !this.checkerboard.getPiece(jump[1][0], jump[1][1]).getMarker().equals(piece.getMarker())
                    && this.checkerboard.getPiece(jump[0][0], jump[0][1]) == null){
                        validMove = true;
                    }
                }
            }
        } while(!validMove);

        return coordinates;
    }

    /**
     * This method moves a piece from one location to another specified location
     * @param piece The pice being moved
     * @param coordinates The coordinates of the next location the piece is being moved to
     */
    public void makeMove(Piece piece, int[] coordinates) {
        this.checkerboard.removePiece(piece.getRow(), piece.getCol());
        piece.setRow(coordinates[0]);
        piece.setCol(coordinates[1]);
        this.checkerboard.setPiece(piece);
        this.moves++;
    }

    /**
     * This method removes a specified piece which is being captures
     * @param row The row number of the piece
     * @param col the column number of the piece
     */
    public void capturePiece(int row, int col) {
        this.checkerboard.removePiece(row, col);
    }

    /**
     * This method checks if a Pawn is in the opponent's first row and has become king, and replaces it with a king piece
     * @param pawn The player's pawn
     */
    public void checkAndUpdatePawnStatus(Pawn pawn) {
        if (pawn.getMarker().equals("\u001B[33mX\u001B[0m")) {
            if (pawn.getRow() == 0) {
                this.checkerboard.removePiece(pawn.getRow(), pawn.getCol());
                this.checkerboard.setPiece(new King(pawn.getRow(), pawn.getCol(), pawn.getMarker()));
            }
        }
        else if (pawn.getRow() == 7) {
            this.checkerboard.removePiece(pawn.getRow(), pawn.getCol());
            this.checkerboard.setPiece(new King(pawn.getRow(), pawn.getCol(), pawn.getMarker()));
        }
    }

    /**
     * Prompts the user to play again
     * @return If the user wishes to play again
     */
    public boolean replayPrompt() {
        String replay = "";

        // Repeatedly asks the user to enter "Yes" or "No" until they enter one of the two options
        do {
            System.out.println("Would you like to play again?");
            replay = new Scanner(System.in).nextLine();
            if (!(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no"))) {
                System.out.println("Invalid input. Please try again.");
            }
        } while(!(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no")));

        if (replay.equalsIgnoreCase("yes")) return true;
        return false;
    }
}