package labunittest.examples;

import java.util.Random;

public class OperandsProvider {

    public Operands getOperands() {
        Random r = new Random();
        boolean willThrow = r.nextBoolean();
        if (willThrow) {
            throw new ArithmeticException();
        } else {
            return new Operands(r.nextInt(100), r.nextInt(100));
        }
    }
}
