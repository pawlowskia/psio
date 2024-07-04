package labthreading.examples;

public class ThreadCreation {

    public static void createThreads() {
        //1. Extend Thread class
        Thread thread1 = new MyThread();
        //2. Implement Runnable interface
        Thread thread2 = new Thread(new MyRunnable());

        //anonymous class
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm inside anonymous runnable!");
            }
        });

        // lambda
        Thread thread4 = new Thread(() -> System.out.println("I'm inside lambda runnable!"));


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("I'm inside thread!");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("I'm inside runnable!");
    }
}
