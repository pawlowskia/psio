package labthreading.examples;

public class DeadLock {
    private static Object key1 = new Object();
    private static Object key2 = new Object();

    public static void takeAllKeys() {
        Thread keykeeper1 = new Thread(() -> {
            synchronized (key1) {
                System.out.println("Thread 1: Holding key 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 1: Waiting for key 2...");

                synchronized (key2) {
                    System.out.println("Thread 1: Holding key 1 & 2...");
                }
            }
        });
        Thread keykeeper2 = new Thread(() -> {
            synchronized (key2) {
                System.out.println("Thread 2: Holding key 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 2: Waiting for key 1...");

//                synchronized (key1) {
//                    System.out.println("Thread 2: Holding key 1 & 2...");
//                }
            }

        });
        keykeeper1.start();
        keykeeper2.start();
    }

}
