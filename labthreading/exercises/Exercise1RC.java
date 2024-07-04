package labthreading.exercises;

public class Exercise1RC {

    public static void main(String[] args) {
        Exercise1RC.raceThreads(5);
    }
    public static void raceThreads(int threadNumber) {
        try {
            for (int i = 0; i < threadNumber; i++) {
                race();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void race() throws InterruptedException {
        Counter c = new Counter();
        Runnable r = () -> {
            for (int i = 0; i < 100_000; i++) {
                c.increment();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(c.getValue());
    }
}

class Counter {
    private int value;

    void increment() {
        synchronized (this) {
            value += 1;
        }
    }

    int getValue() {
        return value;
    }
}