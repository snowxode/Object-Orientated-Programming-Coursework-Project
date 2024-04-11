import java.util.*;

public class DieGame implements GameInterface {
    // Variable holding the numbers rolled by the player during the game
    public static HashSet<Integer> numbersRolled;
    // Die object used to "roll the die" during the game
    public static Die die;

    // DieGame constructor
    public DieGame() {
        // Initalise the variables
        numbersRolled = new HashSet<Integer>();
        die = new Die();
    }

    // Main game loop
    public void startGame() throws Exception {

        // Let the user roll the die twice
        for (int i = 0; i < 2; i++) {
            // Prompt the user to roll the die
            System.out.println("Hit <RETURN> to roll the die");
            br.readLine();

            // Roll the die
            die.roll(r);
            // Display the number rolled
            System.out.println("You rolled " + die.getCurrentFace());
            // Add the number rolled to the numbers rolled variable
            numbersRolled.add(Integer.valueOf(die.getCurrentFace()));
        }

        // Display the numbers rolled
        System.out.println("Numbers rolled: " + numbersRolled);
    }

    // Declare the winner of the game
    public void declareWinner() throws Exception {

        // User wins if at least one of the die rolls is a 1
        if (numbersRolled.contains(Integer.valueOf(1))) {
            System.out.println("You won!");
        } else
            System.out.println("You lost!");
    }

    // Run the game
    public void run() throws Exception {
        startGame();
        declareWinner();
    }
}