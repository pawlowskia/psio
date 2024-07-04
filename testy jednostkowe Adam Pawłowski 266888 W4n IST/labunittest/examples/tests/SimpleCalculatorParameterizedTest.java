package labunittest.examples.tests;

import labunittest.examples.SimpleCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SimpleCalculatorParameterizedTest {

    private int m1;
    private int m2;

    public SimpleCalculatorParameterizedTest(int p1, int p2) {
        m1 = p1;
        m2 = p2;
    }

    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 1 , 2 }, { 5, 3 }, { 121, 4 } };
        return Arrays.asList(data);
    }


    @Test
    public void testMultiplyException() {
        SimpleCalculator tester = new SimpleCalculator();
        assertEquals("Result", m1 + m2, tester.add(m1, m2));
    }

}
