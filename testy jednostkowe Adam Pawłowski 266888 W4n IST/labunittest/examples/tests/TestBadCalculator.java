package labunittest.examples.tests;

import labunittest.examples.BadSimpleCalculatorWithOperands;
import labunittest.examples.SimpleCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBadCalculator {
    @Test
    public void testAdd() {
        BadSimpleCalculatorWithOperands tester = new BadSimpleCalculatorWithOperands();
        assertEquals("Result", 10 * 2, tester.addSomething()); // how to test that method?
    }
}
