package observer.threaded_demo.tests;

import observer.threaded_demo.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class TestDataRandomizer {
    @Test
    public void testMeasure011(){
        Measurement m = new DataRandomizer().measure(
                new boolean[]{false, true, true},
                "Test");

        assertEquals(m.getT(), -300);
    }
    @Test
    public void testMeasure101(){
        Measurement m = new DataRandomizer().measure(
                new boolean[]{true, false, true},
                "Test");

        assertEquals(m.getH(), -300);
    }
    @Test
    public void testMeasure110(){
        Measurement m = new DataRandomizer().measure(
                new boolean[]{true, true, false},
                "Test");

        assertEquals(m.getP(), -300);
    }
}
