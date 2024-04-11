import java.util.*;

public class CardGame implements GameInterface {
    // Variable holding the cards chosen by the player during the game
    public static HashSet<String> cardsChosen;

    // CardDeck object used to "deal" cards during the game
    public static ThreadSafeCardDeck cardDeck;

    // CardGame constructor
    public CardGame() {
        // Initialise the variables
        cardDeck = new ThreadSafeCardDeck();
        cardsChosen = new HashSet<String>();
        // Print the shuffled deck
        System.out.println(cardDeck.cardList);
        return;
    }

    // Main game loop
    public void startGame() throws Exception {
        // Let user select two cards from the pack
        for (int i = 0; i < 2; i++) {
            // Prompt the user to choose a card
            System.out.println("Hit <RETURN> to choose a card");
            br.readLine();

            // Deal a card using the ThreadSafeDealCard method
            String card = cardDeck.ThreadSafeDealCard();
            // Display the card chosen
            System.out.println("You chose " + card);
            // Add the card chosen to the cards chosen variable
            cardsChosen.add(card);
        }

        // Display the cards chosen and remaining cards
        System.out.println("Cards chosen: " + cardsChosen);
        System.out.println("Remaining cards: " + cardDeck.cardList);
    }

    // Declare the winner of the game
    public void declareWinner() throws Exception {
        // Declare the winner:
        // User wins if one of them is an Ace
        System.out.println("Cards chosen: " + cardsChosen);
        if (cardsChosen.contains("AHrts") || cardsChosen.contains("ADmnds") ||
                cardsChosen.contains("ASpds") || cardsChosen.contains("AClbs")) {
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