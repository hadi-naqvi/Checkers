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
    private int moves;
    private static final HashMap<String, Integer> rowLabels = new HashMap<String, Integer>();

    /**
     * Constructor method for Game object which initializes its attributes
     */
    public Game() {
        this.checkerboard = new Board();
        this.moves = 0;
        rowLabels.put("A", 1);
        rowLabels.put("B", 2);
        rowLabels.put("C", 3);
        rowLabels.put("D", 4);
        rowLabels.put("E", 5);
        rowLabels.put("F", 6);
        rowLabels.put("G", 7);
        rowLabels.put("H", 8);
    }

    /**
     * Initializes the checkerboard by instantiating and storing 24 pieces and storing 40 empty spaces/cells as null
     */
    public void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Places all of player X's pawns
                if (row <= 2 && (col - row + 4) % 2 == 0) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[33mO\u001B[0m"));
                }
                // Places all of player O's pawns
                else if (row >= 5 && (col - row + 7) % 2 == 1) {
                    this.checkerboard.setPiece(new Pawn(row, col, "\u001B[36mO\u001B[0m"));
                }
                // Sets every empty cell/square on the checkerboard as null
                else {
                    this.checkerboard.setPiece(row, col);
                }
            }
        }
    }

    /**
     * Outputs the checkerboard on-screen in a visually appealing manner
     */
    public void printBoard() {
        System.out.println("----------------------CHECKERS----------------------" + " \n" + "Number of Moves made: " + this.moves);
        this.checkerboard.print();
    }

    /**
     * This method determines whether a given player has won the game by checking if the opponent has no pieces left to move/jump with
     * @param marker The player's marker
     * @return If the given player has won the game
     */
    public boolean checkWin(String marker) {
        // Iterates through every cell on the board and checks if the opponent has any pieces left on the board which can make a move
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Checks if the opponent can make a move/jump with any of their pieces
                if (this.checkerboard.getPiece(row, col) != null && !this.checkerboard.getPiece(row, col).getMarker().equals(marker) && (this.canJump(this.checkerboard.getPiece(row, col)) || this.canMove(this.checkerboard.getPiece(row, col)))) {
                    return false;
                }
            }
        }
        // Returns true if a jump or move cannot be made by the opponent
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
     * This method determines if a given piece is able to make jump(s)
     * @param piece The piece being checked
     * @return If the player can make a jump with the piece
     */
    public boolean canJump(Piece piece, int[] coordinates) {
        if (piece != null) {
            for (int[][] jump : piece.getPossibleJumps()) {
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
     * This method determines if a given piece is able to make jump(s)
     * @param marker The player's marker
     * @return If the player can make a jump with the piece
     */
    public boolean canJump(String marker) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece temp = this.checkerboard.getPiece(row, col);
                if (temp != null) {
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
                if (this.checkerboard.getPiece(move[0], move[1]) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method determines if a given piece can make a move
     * @param piece The piece being checked
     * @param coordinates The coordinates of the final position after the move is made
     * @return If the piece can make a move
     */
    public boolean canMove(Piece piece, int[] coordinates) {
        if (piece != null) {
            for (int[] move : piece.getPossibleMoves()) {
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
                boolean canJump = this.canJump(marker);
                if (!canJump) {
                    System.out.println("Enter the coordinates of the piece you would like to move (Ex. A,5):");
                } else {
                    System.out.println("You have outstanding jump(s) to make. Enter the coordinates of the piece you would like to jump with (Ex. A,5):");
                }
                
                String[] input = new Scanner(System.in).nextLine().split(",");
                //coordinates = Arrays.stream(new Scanner(System.in).nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
                coordinates[0] = rowLabels.get(input[0].toUpperCase()) - 1;
                coordinates[1] = Integer.parseInt(input[1]) - 1;

                if (!this.checkerboard.isCellEmpty(coordinates[0], coordinates[1]) && this.checkerboard.getPiece(coordinates[0], coordinates[1]).getMarker().equals(marker)) {
                    if (canJump) {
                        validPiece = this.canJump(this.checkerboard.getPiece(coordinates[0], coordinates[1]));
                    } else {
                        validPiece = this.canMove(this.checkerboard.getPiece(coordinates[0], coordinates[1]));
                    }
                }

                if (!validPiece) {
                    System.out.println("Invalid input! The piece you selected cannot be moved, does not exist, or you have outstanding jumps. Please try selecting again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! The piece you selected cannot be moved, or does not exist, or you have outstanding jumps. Please try selecting again.");
            }
        } while (!validPiece);

        return this.checkerboard.getPiece(coordinates[0], coordinates[1]);
    }

    public int[] getMovePos(Piece piece) {
        boolean validMove = false;
        int[] coordinates = {0, 0};

        // Continuously prompts the user to enter coordinates until they enter coordinates for a valid piece
        do {
            try {
                System.out.println("Enter the coordinates of the location you would like the piece to move to (Ex. A,5):");
                
                String[] input = new Scanner(System.in).nextLine().split(",");
                //coordinates = Arrays.stream(new Scanner(System.in).nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
                coordinates[0] = rowLabels.get(input[0].toUpperCase()) - 1;
                coordinates[1] = Integer.parseInt(input[1]) - 1;

                if (this.canJump(piece)) {
                    if (this.canJump(piece, coordinates)) {
                        validMove = true;
                        return coordinates;
                    }
                } else if (this.canMove(piece, coordinates)) {
                    validMove = true;
                }

                if (!validMove) {
                    System.out.println("Invalid input.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        } while (!validMove);

        return coordinates;
    }

    /**
     * This method moves a piece from one location to another specified location
     * @param piece The piece being moved
     * @param coordinates The coordinates of the next location the piece is being moved to
     */
    public void makeMove(Piece piece, int[] coordinates) {
        if (Math.abs(coordinates[0] - piece.getRow()) > 1 && Math.abs(coordinates[1] - piece.getCol()) > 1) {
            this.checkerboard.removePiece((piece.getRow() + coordinates[0]) / 2, (piece.getCol() + coordinates[1]) / 2);
        }

        this.checkerboard.removePiece(piece.getRow(), piece.getCol());
        piece.setRow(coordinates[0]);
        piece.setCol(coordinates[1]);
        this.checkerboard.setPiece(piece);
        this.transformPossibleKing(piece);
        this.moves++;
    }

    /**
     * This method checks if a Pawn is in the opponent's first row and has become king, and replaces it with a king piece
     * @param piece The player's piece
     */
    public void transformPossibleKing(Piece piece) {
        if (piece.getMarker().equals("\u001B[33mO\u001B[0m") && piece.getRow() == 7 && piece.type().equals("pawn")) {
            this.checkerboard.removePiece(piece.getRow(), piece.getCol());
            this.checkerboard.setPiece(new King(piece.getRow(), piece.getCol(), piece.getMarker()));
        } else if (piece.getRow() == 0 && piece.type().equals("pawn")) {
            this.checkerboard.removePiece(piece.getRow(), piece.getCol());
            this.checkerboard.setPiece(new King(piece.getRow(), piece.getCol(), piece.getMarker()));
        }
    }

    /**
     * Prompts the user to play again
     * @return If the user wishes to play again
     */
    public boolean replayPrompt() {
        String replay;

        // Repeatedly asks the user to enter "Yes" or "No" until they enter one of the two options
        do {
            System.out.println("Would you like to play again?");
            replay = new Scanner(System.in).nextLine();
            if (!(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no"))) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!(replay.equalsIgnoreCase("yes") || replay.equalsIgnoreCase("no")));

        return replay.equalsIgnoreCase("yes");
    }
}