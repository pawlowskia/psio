package observer.threaded_demo.tests;

import observer.threaded_demo.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtility {
    @Test
    public void testScanString(){
        Utility u = new Utility();
        u = mock(Utility.class);
        when(u.scanString()).thenReturn("Test");

        assertEquals(u.scanString(), "Test");
    }

    @Test
    public void testScanInt(){
        Utility u = new Utility();
        u = mock(Utility.class);
        when(u.scanInt(1, 1)).thenReturn(1);

        assertEquals(u.scanInt(1,1), 1);
    }
}
