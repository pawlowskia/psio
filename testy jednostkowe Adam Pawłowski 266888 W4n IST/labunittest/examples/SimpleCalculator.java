package labunittest.examples;

public class SimpleCalculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        if (b == 0) throw new ArithmeticException();
        return a / b;
    }
}
