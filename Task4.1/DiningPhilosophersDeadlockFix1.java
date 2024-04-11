public class DiningPhilosophersDeadlockFix1 {

    public static void main(String[] args) throws Exception {

        final int problemSize = 5;
        ForkDeadlockFix1 leftFork;
        ForkDeadlockFix1 rightFork;

        PhilosopherDeadlockFix1[] philosophers = new PhilosopherDeadlockFix1[problemSize];
        ForkDeadlockFix1[] forks = new ForkDeadlockFix1[problemSize];

        for (int i = 0; i < problemSize; i++) {
            forks[i] = new ForkDeadlockFix1();
        }

        for (int i = 0; i < problemSize; i++) {
            leftFork = forks[i];
            rightFork = forks[(i + 1) % problemSize];

            if (i == 0) {
                // Swap the first philosopher's forks
                philosophers[i] = new PhilosopherDeadlockFix1(rightFork, leftFork, i + 1);
            } else {
                philosophers[i] = new PhilosopherDeadlockFix1(leftFork, rightFork, i + 1);
            }

            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }
}
