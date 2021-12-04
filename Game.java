/**
 * [ICS4U] Checkers | Game.java
 * Date: December 2nd, 2021
 * @author Hadi Naqvi, Arjun Menon, Anderew Kwok
 * Teacher: Mr. Ho
 */

import java.util.Scanner;
import java.util.HashMap;

public class Game {
    // Attributes of Game object
    private Board checkerboard;

    // Initialization of a hashmap which stores the rows and their corresponding integer values and a scanner object for user-input
    private static final HashMap<String, Integer> ROWLABELS = new HashMap<String, Integer>();
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructor method for Game object which initializes its attributes
     */
    public Game() {
        this.checkerboard = new Board();

        // Populates the rowLabels hashmap with the rows and their corresponding integer values
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < 8; i++) {
            ROWLABELS.put(rows[i], i + 1);
        }
    }

    /**
     * Initializes the checkerboard by instantiating and storing 24 pieces and storing 40 empty spaces/cells as null
     */
    public void initializeBoard() {
        // Itereates over every cell and places a new piece/pawn or null
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Places all of player 1's pawns
                if (row <= 2 && (col - row + 4) % 2 == 0) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[33mO\u001B[0m"));
                }
                // Places all of player 2's pawns
                else if (row >= 5 && (col - row + 7) % 2 == 1) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[36mO\u001B[0m"));
                }
                // Removes pieces in cells/squares which should be empty and sets them to null
                else {
                    this.checkerboard.removePiece(row, col);
                }
            }
        }
    }

    /**
     * Outputs the checkerboard on-screen in a visually appealing manner
     */
    public void printBoard() {
        System.out.println("--------------------CHECKERBOARD--------------------");
        this.checkerboard.print();
    }

    /**
     * This method determines whether a given player has won the game by checking if the opponent has no pieces left to move/jump with
     * @param marker The player's marker
     * @return If the given player has won the game
     */
    public boolean hasPlayerWon(String marker) {
        // Iterates through every cell on the board and checks if the opponent has any pieces left on the board which can make a move
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Checks if the opponent can make a move/jump with any of their pieces
                if (this.checkerboard.getPiece(row, col) != null
                    && !this.checkerboard.getPiece(row, col).getMarker().equals(marker)
                    && (this.canJump(this.checkerboard.getPiece(row, col)) || this.canMove(this.checkerboard.getPiece(row, col)))) {
                    return false;
                }
            }
        }

        // Returns true if a jump or move cannot be made by the opponent (They've lost)
        return true;
    }

    /**
     * This method determines if a given piece is able to make jump(s)
     * @param piece The piece being checked
     * @return If the player can make a jump with the piece
     */
    public boolean canJump(Piece piece) {
        if (piece != null) {
            for (int[][] jump : piece.getPossibleJumps()) {
                // A piece can make a valid jump if the adjacent piece being jumped is the opponent's piece
                if (this.checkerboard.getPiece(jump[1][0], jump[1][1]) != null
                        && !this.checkerboard.getPiece(jump[1][0], jump[1][1]).getMarker().equals(piece.getMarker())
                        && this.checkerboard.getPiece(jump[0][0], jump[0][1]) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method determines if a given piece is able to make a jump to a specific location on the board
     * @param piece The piece being checked
     * @param coordinates The coordinates of the jump location being checked
     * @return If the player can make a jump with the piece to the specified location
     */
    public boolean canJump(Piece piece, int[] coordinates) {
        if (piece != null) {
            for (int[][] jump : piece.getPossibleJumps()) {
                // A piece can make a valid jump if the landing position is empty and the adjacent piece being jumped is the opponent's piece
                if (this.checkerboard.getPiece(jump[1][0], jump[1][1]) != null
                        && !this.checkerboard.getPiece(jump[1][0], jump[1][1]).getMarker().equals(piece.getMarker())
                        && this.checkerboard.getPiece(jump[0][0], jump[0][1]) == null
                        && coordinates[0] == jump[0][0]
                        && coordinates[1] == jump[0][1]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method determines if a given player has any pieces on the checkerboard which are able to make a jump
     * @param marker The player's marker
     * @return If the player can make a jump with any of their pieces
     */
    public boolean hasOutstandingJump(String marker) {
        // Iterates over every piece on the board to find pieces which the player owns and can possibly jump with
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece temp = this.checkerboard.getPiece(row, col);
                if (temp != null) {
                    // Checks if the player owns the piece and if it can make a jump
                    if (temp.getMarker().equals(marker) && canJump(temp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method determines if a given piece can make a move
     * @param piece The piece being checked
     * @return If the piece can make a move
     */
    public boolean canMove(Piece piece) {
        if (piece != null) {
            for (int[] move : piece.getPossibleMoves()) {
                // Checks if the final position of the piece after making its move is empty and free to move into
                if (this.checkerboard.getPiece(move[0], move[1]) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method determines if a given piece can make move to a specific location on the checkerboard
     * @param piece The piece being checked
     * @param coordinates The coordinates of the final position after the move is made
     * @return If the piece can make a move
     */
    public boolean canMove(Piece piece, int[] coordinates) {
        if (piece != null) {
            for (int[] move : piece.getPossibleMoves()) {
                // Checks if the final position of the piece after making its move is empty and free to move into
                if (this.checkerboard.getPiece(move[0], move[1]) == null && coordinates[0] == move[0] && coordinates[1] == move[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method prompts the user to enter the coordinates of the piece which they would like to move
     * @param marker The marker of the piece which represents which player is making the move
     * @return The piece the user selected to move
     */
    public Piece getMovePiece(String marker) {
        boolean validPiece = false;
        int[] coordinates = {0, 0};

        // Continuously prompts the user to enter coordinates until they enter coordinates for a valid piece
        do {
            try {
                boolean canJump = this.hasOutstandingJump(marker);
                if (!canJump) {
                    System.out.println("Player " + marker + ", enter the coordinates of the piece you would like to move (Ex. A,5):");
                } else {
                    System.out.println("You have outstanding jump(s) to make.\n" + "Player " + marker + ", enter the coordinates of the piece you would like to move (Ex. A,5):");
                }

                // Prompts the user to enter comma separated coordinates
                String[] input = SCANNER.nextLine().split(",");
                coordinates[0] = ROWLABELS.get(input[0].toUpperCase()) - 1;
                coordinates[1] = Integer.parseInt(input[1]) - 1;

                // Checks if the selected piece is owned by the player and isn't an empty piece
                if (!this.checkerboard.isCellEmpty(coordinates[0], coordinates[1]) && this.checkerboard.getPiece(coordinates[0], coordinates[1]).getMarker().equals(marker)) {
                    // If an outstanding jump exists, it checks if the piece is able to jump, otherwise it checks if the piece is simply able to move
                    if (canJump) {
                        validPiece = this.canJump(this.checkerboard.getPiece(coordinates[0], coordinates[1]));
                    } else {
                        validPiece = this.canMove(this.checkerboard.getPiece(coordinates[0], coordinates[1]));
                    }
                }

                // If the coordinates the user entered aren't a valid piece, they are told to try entering coordinates again
                if (!validPiece) {
                    System.out.println("Invalid input! The piece you selected cannot be moved, does not exist, or you have outstanding jumps you must make instead.\nPlease try selecting again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! The piece you selected cannot be moved, or does not exist, or you have outstanding jumps you must make instead.\nPlease try selecting again.");
            }
        } while (!validPiece);

        return this.checkerboard.getPiece(coordinates[0], coordinates[1]);
    }

    /**
     * This method retrieve the position of the move the player wishes to make with their piece (get move position)
     * @param piece The piece being moved
     * @return The coordinates of the position the user selected to move their piece to
     */
    public int[] getMovePos(Piece piece) {
        boolean validMove = false;
        int[] coordinates = {0, 0};

        // Continuously prompts the user to enter coordinates until they enter coordinates for a valid piece
        do {
            try {
                // Prompts the user to enter comma separated coordinates
                System.out.println("Player " + piece.getMarker() + ", enter the coordinates where you want to move your piece: (Ex. A,5):");
                String[] input = SCANNER.nextLine().split(",");
                coordinates[0] = ROWLABELS.get(input[0].toUpperCase()) - 1;
                coordinates[1] = Integer.parseInt(input[1]) - 1;

                // If the piece is able to jump, it checks if the move position is a jump, otherwise it checks if the move position is a valid move
                if (this.canJump(piece)) {
                    if (this.canJump(piece, coordinates)) {
                        validMove = true;
                        return coordinates;
                    }
                } else if (this.canMove(piece, coordinates)) {
                    validMove = true;
                }

                // If the coordinates the user entered aren't a valid move, they are told to try entering coordinates again
                if (!validMove) {
                    System.out.println("Invalid input! The move you selected is either out of bounds, cannot be made, or you have outstanding jumps you must make instead.\nPlease try selecting again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! The move you selected is either out of bounds, cannot be made, or you have outstanding jumps you must make instead.\nPlease try selecting again.");
            }
        } while (!validMove);

        // The coordinates of the move position are returned
        return coordinates;
    }

    /**
     * This method moves a piece from one location to another specified location
     * @param piece The piece being moved
     * @param coordinates The coordinates of the next location the piece is being moved to
     */
    public void makeMove(Piece piece, int[] coordinates) {
        // If the move being made is a jump, the position of the piece being captured is calculated and removed from the board
        if (Math.abs(coordinates[0] - piece.getRow()) > 1 && Math.abs(coordinates[1] - piece.getCol()) > 1) {
            this.checkerboard.removePiece((piece.getRow() + coordinates[0]) / 2, (piece.getCol() + coordinates[1]) / 2);
        }

        // The move is made by removing the piece from its original position, then changing its row/col values and setting it in its new position
        this.checkerboard.removePiece(piece.getRow(), piece.getCol());
        piece.setRow(coordinates[0]);
        piece.setCol(coordinates[1]);
        this.checkerboard.setPiece(piece);

        // Checks if the piece has become a king by making its move and turns it into a King if it has
        this.transformPossibleKing(piece);
    }

    /**
     * This method checks if a Pawn is in the opponent's first row and has become king, and replaces it with a king piece
     * @param piece The player's piece
     */
    public void transformPossibleKing(Piece piece) {
        // Depending on which player's piece is being checked, a different "first row" is checked
        if (piece.getMarker().equals("\u001B[33mO\u001B[0m") && piece.getRow() == 7 && piece.type().equals("pawn")) {
            // Removes the pawn piece and creates a new King piece in its place
            this.checkerboard.removePiece(piece.getRow(), piece.getCol());
            this.checkerboard.setPiece(new King(piece.getRow(), piece.getCol(), piece.getMarker()));
        } else if (piece.getRow() == 0 && piece.type().equals("pawn")) {
            // Removes the pawn piece and creates a new King piece in its place
            this.checkerboard.removePiece(piece.getRow(), piece.getCol());
            this.checkerboard.setPiece(new King(piece.getRow(), piece.getCol(), piece.getMarker()));
        }
    }

    /**
     * Prompts the user to play again
     * @return If the user wishes to play again
     */
    public boolean replay() {
        String replay;

        // Repeatedly asks the user to enter "Yes" or "No" until they enter one of the two options
        do {
            System.out.println("Would you like to play again? (Yes/No):");
            replay = SCANNER.nextLine();
            if (!(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no"))) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no")));

        return replay.equalsIgnoreCase("yes");
    }

    /**
     * This deconstructor method closes the scanner used in the program for user-input
     */
    public void closeScanner() {
        SCANNER.close();
    }
}