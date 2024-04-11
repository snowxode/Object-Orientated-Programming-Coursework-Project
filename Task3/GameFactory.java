public abstract class GameFactory {
    // Initialise a new card game
    public static CardGame createCardGame() {
        return new CardGame();
    }

    // Initialise a new die game
    public static DieGame createDieGame() {
        return new DieGame();
    }
}