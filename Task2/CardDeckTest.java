class DealCardThread extends Thread {
    private CardDeck cardDeck;

    public DealCardThread(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void run() {
        cardDeck.dealCard();
    }

    public static void main(String[] args) {
        return;
    }
}

class SafeDealCardThread extends Thread {
    private ThreadSafeCardDeck cardDeck;

    public SafeDealCardThread(ThreadSafeCardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void run() {
        cardDeck.ThreadSafeDealCard();
    }

    public static void main(String[] args) {
        return;
    }
}

public class CardDeckTest {
    public static int thread_no = 20;
    public static int test_no = 100;

    public static boolean test(int choice) {
        if (choice == 1) {
            System.out.println("================================ \nUnsafe card deck test... \n");

            for (int l = 1; l <= test_no; l++) {
                CardDeck card_deck = new CardDeck();
                Thread[] threads_t1 = new Thread[thread_no];

                // Start all threads
                for (int i = 0; i < thread_no; i++) {
                    threads_t1[i] = new DealCardThread(card_deck);
                    threads_t1[i].start();
                }

                // Wait for all threads to finish
                for (int i = 0; i < thread_no; i++) {
                    try {
                        threads_t1[i].join();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }

                if (card_deck.sequenceNumber != thread_no) {
                    System.out.println("Error at test " + l);
                    System.out.println("Sequence Number: " + card_deck.sequenceNumber + ", Threads: " + thread_no);
                    System.out.println("CardDeck method 'dealCard()' is not thread-safe \n");
                    return true;
                }
            }
            return false;
        }

        else if (choice == 2) {
            System.out.println("================================ \nthreadSafeCardDeck() test... \n");

            for (int l = 1; l <= test_no; l++) {

                ThreadSafeCardDeck ts_card_deck = new ThreadSafeCardDeck();
                Thread[] threads_t2 = new Thread[thread_no];

                // Start all threads
                for (int i = 0; i < thread_no; i++) {
                    threads_t2[i] = new SafeDealCardThread(ts_card_deck);
                    threads_t2[i].start();
                }

                // Wait for all threads to finish
                for (int i = 0; i < thread_no; i++) {
                    try {
                        threads_t2[i].join();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }

                if (ts_card_deck.sequenceNumber != thread_no) {
                    System.out.println("Error at test " + l);
                    System.out.println("Sequence Number: " + ts_card_deck.sequenceNumber + ", Threads: " + thread_no);
                    System.out.println("ThreadSafeCardDeck method 'threadSafeDealCard()' is not thread-safe \n");
                    return true;
                }
            }
            return false;
        } else {
            System.out.println("Error caught");
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        if (test(1) == false) {
            System.out.println(
                    "No thread inconsistencies found in " + test_no + " tests. \n You may want to re-run... \n");
        }
        if (test(2) == false) {
            System.out.println("No thread inconsistencies found in " + test_no + " tests. \n");
        }
    }
}
