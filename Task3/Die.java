public class Die {
    private static int currentFace;

    public int roll(RandomInterface r) {
        // Use the random number generator to generate a number between 1 and 6
        // and set the die face to that number and return it
        currentFace = (int) (r.next() * 6) + 1;
        return currentFace;
    }

    public int getCurrentFace() {
        // Method to return the current face of the die
        return currentFace;
    }
}
