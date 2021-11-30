/**
 * [ICS4U] Checkers | Checkers.java 
 * Date: December 2nd, 2021
 * @author Hadi Naqvi
 * Teacher: Mr. Ho
 */

public class Checkers {
    public static void main(String[] args) {
        Game checkers = new Game();

        checkers.initializeBoard();
        checkers.printBoard();

        System.out.println("Test");
    }
}