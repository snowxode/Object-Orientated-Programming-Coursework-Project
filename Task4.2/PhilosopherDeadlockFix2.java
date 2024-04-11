public class PhilosopherDeadlockFix2 implements Runnable {
    // The forks on either side of this Philosopher and his/her philosopher number
    private ForkDeadlockFix2 leftFork;
    private ForkDeadlockFix2 rightFork;
    private int philosopherNumber;
    private Object lock; // Add a private instance variable for the shared lock object

    public PhilosopherDeadlockFix2(ForkDeadlockFix2 left, ForkDeadlockFix2 right, int philNumber, Object lock) {
        leftFork = left;
        rightFork = right;
        philosopherNumber = philNumber;
        this.lock = lock; // Initialize the shared lock object
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println("Philosopher number " + philosopherNumber + " time: " + System.nanoTime() + ": " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    public void run() {
        try {
            while (true) {

                // thinking
                doAction("Thinking");

                // Acquire the shared lock object to avoid interleaving with other philosophers'
                // threads
                synchronized (lock) {
                    // Check if both forks are available, and if so, pick them up
                    if (!leftFork.inUse && !rightFork.inUse) {
                        leftFork.inUse = true;
                        rightFork.inUse = true;
                        doAction("Picking up both forks");
                    }
                }

                // If the philosopher has both forks, eat for a while
                if (leftFork.inUse && rightFork.inUse) {
                    doAction("Eating");

                    // Put down both forks
                    leftFork.inUse = false;
                    rightFork.inUse = false;
                    doAction("Putting down both forks");
                } else {
                    // If the philosopher didn't get both forks, put down any forks that were picked
                    // up
                    if (leftFork.inUse) {
                        leftFork.inUse = false;
                        doAction("Putting down left fork");
                    }
                    if (rightFork.inUse) {
                        rightFork.inUse = false;
                        doAction("Putting down right fork");
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}