package labthreading.exercises;


public class PhilosopherExample implements Runnable {
    // The forks on either side of this Philosopher
    private Fork leftFork;
    private Fork rightFork;

    public PhilosopherExample(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        System.out.println(add(10, 16));
    }
    public int add(Integer a, Integer b ) {
        return a + b;
    }

    @Override
    public void run() {
        try {
            while (true) {

                doAction(System.nanoTime() + ": Thinking");

                if (!leftFork.is_used && !rightFork.is_used){
                    synchronized (leftFork) {
                        leftFork.is_used = true;

                        doAction(
                                System.nanoTime()
                                        + ": Picked up left fork");
                        synchronized (rightFork) {
                            rightFork.is_used = true;

                            // eating
                            doAction(
                                    System.nanoTime()
                                            + ": Picked up right fork - eating");

                            doAction(
                                    System.nanoTime()
                                            + ": Put down right fork");
                            rightFork.is_used = false;
                        }

                        // Back to thinking
                        doAction(
                                System.nanoTime()
                                        + ": Put down left fork. Back to thinking");
                        leftFork.is_used = false;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 5)));
    }

    boolean flip_is_used(Fork f){
        synchronized (f){
            f.is_used = !f.is_used;
            return !f.is_used;
        }
    }
}
