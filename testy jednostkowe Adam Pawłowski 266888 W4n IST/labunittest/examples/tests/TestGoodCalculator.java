package labunittest.examples.tests;

import labunittest.examples.BadSimpleCalculatorWithOperands;
import labunittest.examples.GoodSimpleCalculatorWithOperands;
import labunittest.examples.Operands;
import labunittest.examples.OperandsProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestGoodCalculator {
    @Test
    public void testAdd() {
        //step 1 - not yet successful
        OperandsProvider operandsProvider = new OperandsProvider();
        GoodSimpleCalculatorWithOperands tester = new GoodSimpleCalculatorWithOperands(operandsProvider);

        assertEquals("Result", 10 * 2, tester.addSomething()); // how to test that method?
    }

    @Test
    public void testAddMocked() {
        OperandsProvider operandsProviderMock = mock(OperandsProvider.class);
        when(operandsProviderMock.getOperands()).thenReturn(new Operands(10,20));
        //step 1 - not yet successful
        GoodSimpleCalculatorWithOperands tester = new GoodSimpleCalculatorWithOperands(operandsProviderMock);

        assertEquals("Result", 10 + 20, tester.addSomething()); // how to test that method?
    }

    @Test
    public void testAddMockedWillThrow() {
        OperandsProvider operandsProviderMock = mock(OperandsProvider.class);
        when(operandsProviderMock.getOperands()).thenThrow(new ArithmeticException());
        //step 1 - not yet successful
        GoodSimpleCalculatorWithOperands tester = new GoodSimpleCalculatorWithOperands(operandsProviderMock);
        assertEquals("Result", 0, tester.addSomething()); // how to test that method?
    }
}
