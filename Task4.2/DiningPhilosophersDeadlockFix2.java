public class DiningPhilosophersDeadlockFix2 {

    public static void main(String[] args) throws Exception {

        final int problemSize = 5;
        ForkDeadlockFix2[] forks = new ForkDeadlockFix2[problemSize];

        for (int i = 0; i < problemSize; i++) {
            forks[i] = new ForkDeadlockFix2();
        }

        // Create a shared object for locking
        Object lock = new Object();

        PhilosopherDeadlockFix2[] philosophers = new PhilosopherDeadlockFix2[problemSize];

        for (int i = 0; i < problemSize; i++) {
            // Pass the shared lock object to each philosopher
            philosophers[i] = new PhilosopherDeadlockFix2(forks[i], forks[(i + 1) % problemSize], i + 1, lock);

            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }
}
