package labthreading.exercises;

public class Exercise2DP {
    public static void main(String[] args) throws Exception {

        PhilosopherExample[] philosophers = new PhilosopherExample[5];
        Fork[] forks = new Fork[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new PhilosopherExample(leftFork, rightFork);

            Thread t
                    = new Thread(philosophers[i], "PhilosopherExample " + (i + 1));
            t.start();
        }
    }
}
