package labunittest.examples.tests;

import labunittest.examples.SimpleCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleCalculatorTest {

    @Test
    public void testMultiply() {
        SimpleCalculator tester = new SimpleCalculator();
        assertEquals("Result", 10 * 2, tester.mul(10, 2));
    }

    @Test
    public void testDivision() {
        SimpleCalculator tester = new SimpleCalculator();
        assertEquals("Result", 5, tester.div(20, 4));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionThrowsException() {
        SimpleCalculator tester = new SimpleCalculator();
        tester.div(5,0);
    }
}
