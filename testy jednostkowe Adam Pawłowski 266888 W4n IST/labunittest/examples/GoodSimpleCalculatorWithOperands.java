package labunittest.examples;

public class GoodSimpleCalculatorWithOperands {

    private OperandsProvider operandsProvider;

    public GoodSimpleCalculatorWithOperands(OperandsProvider operandsProvider) {
        this.operandsProvider = operandsProvider;
    }

    public int addSomething() {
        try {
            Operands operands = operandsProvider.getOperands();
            return operands.a + operands.b;
        } catch (ArithmeticException ae) {
            return 0;
        }
    }
}
