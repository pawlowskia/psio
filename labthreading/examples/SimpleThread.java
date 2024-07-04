package labthreading.examples;

public class SimpleThread {

    // If you run it several times you'll see, that one time main thread executes first - the other time, the new one
    public static void simpleThread() {

        Thread thread = new Thread(() -> countFrom0to4("New thread"));
        thread.start();
        countFrom0to4("Main thread");
    }

    private static void countFrom0to4(String name) {
        System.out.println(name + " starts");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%s %d%n", name, i);
        }
        System.out.println(name + " stops");
    }
}
