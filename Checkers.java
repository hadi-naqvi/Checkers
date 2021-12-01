/**
 * [ICS4U] Checkers | Checkers.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

public class Checkers {
    public static void main(String[] args) {
        Game checkers = new Game();

        // Main program/game-loop
        do {
            checkers.initializeBoard();
            while (true) {
                checkers.printBoard();
                break;
            }
        } while(checkers.promptReplay());
    }
}