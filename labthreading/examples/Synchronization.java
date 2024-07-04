package labthreading.examples;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/* https://en.wikipedia.org/wiki/Dekker%27s_algorithm */
public class Synchronization {
    static Object o = new Object();
    static Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadForSynchronizedBlock("Thread One for Synchronized");
        Thread t2 = new ThreadForSynchronizedBlock("Thread Two for Synchronized");

        Thread ts1 = new ThreadForSemaphore("Thread One for Semaphore");
        Thread ts2 = new ThreadForSemaphore("Thread Two for Semaphore");

        Thread tts1 = new ThreadForTimeBoxedSemaphore("Thread One for TimeBoxed Semaphore");
        Thread tts2 = new ThreadForTimeBoxedSemaphore("Thread Two for  TimeBoxed Semaphore");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("===============================");
        ts1.start();
        ts2.start();
        ts1.join();
        ts2.join();
        System.out.println("===============================");
        tts1.start();
        tts2.start();
        tts1.join();
        tts2.join();
    }

    public static void synchronizeWithTimeboxedSemaphores() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": Will enter synchronized block " + LocalTime.now());
            if (mutex.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                doSomething();
                mutex.release();
            } else {
                System.out.println(Thread.currentThread().getName() + ": timeout while trying to acquire mutex");
            }
        }
    }

    public static void synchronizeWithSemaphores() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": Will enter synchronized block " + LocalTime.now());
            mutex.acquire();
            doSomething();
            mutex.release();
        }
    }

    public static void synchronizeWithSynchronized() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": Will enter synchronized block " + LocalTime.now());
            synchronized (o) {
                doSomething();
            }
        }
    }

    private static void doSomething() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": Entered synchronized block " + LocalTime.now());
        Thread.sleep((int) (Math.random() * 500));
        System.out.println(Thread.currentThread().getName() + ": Exit synchronized block " + LocalTime.now());
    }

    static class ThreadForSynchronizedBlock extends AbstractThread {

        public ThreadForSynchronizedBlock(String name) {
            super(name);
        }

        @Override
        protected void doSomething() throws InterruptedException {
            synchronizeWithSynchronized();
        }
    }

    static class ThreadForSemaphore extends AbstractThread {

        public ThreadForSemaphore(String name) {
            super(name);
        }

        @Override
        protected void doSomething() throws InterruptedException {
            synchronizeWithSemaphores();
        }
    }

    static class ThreadForTimeBoxedSemaphore extends AbstractThread {

        public ThreadForTimeBoxedSemaphore(String name) {
            super(name);
        }

        @Override
        protected void doSomething() throws InterruptedException {
            synchronizeWithTimeboxedSemaphores();
        }
    }
}




