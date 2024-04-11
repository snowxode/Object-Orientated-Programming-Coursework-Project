import java.io.*;

public class RunGame {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Ask whether to play a card game or a die game
        System.out.print("Card (c) or Die (d) game? ");
        String ans = br.readLine();

        // Initialise the game and run it depending on the type of game chosen
        if (ans.equals("c")) {
            CardGame game = GameFactory.createCardGame();
            game.run();
        }

        else if (ans.equals("d")) {
            DieGame game = GameFactory.createDieGame();
            game.run();
        }

        else {
            System.out.println("Input not understood");
        }

    }
}
