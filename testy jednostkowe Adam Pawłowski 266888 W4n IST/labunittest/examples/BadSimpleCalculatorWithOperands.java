package labunittest.examples;

public class BadSimpleCalculatorWithOperands {

    private OperandsProvider operandsProvider = new OperandsProvider();

    public int addSomething() {
        try {
            Operands operands = operandsProvider.getOperands();
            return operands.a + operands.b;
        } catch (ArithmeticException ae) {
            return 0;
        }
    }
}
