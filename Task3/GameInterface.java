import java.io.*;

public interface GameInterface {
    // The BufferedReader used throughout
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // The random number generator used throughout
    public static RandomInterface r = new LinearCongruentialGenerator();

    // Main game loop
    public void startGame() throws Exception;

    // Declare the winner of the game
    public void declareWinner() throws Exception;

    // Run the other game methods
    public void run() throws Exception;

}
