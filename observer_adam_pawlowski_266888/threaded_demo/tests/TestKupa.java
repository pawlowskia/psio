package observer.threaded_demo.tests;

import observer.threaded_demo.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestKupa {
    @Test
    public void testSendNotification(){
        Kupa k = new Kupa(new TreeSet<String>(), new TreeMap<String, ArrayList<Measurement>>());
        Measurement m = new Measurement(0,0,0, "Test");

        k.sendNotification("Test", m);
        ArrayList<Measurement> tmp = k.getMeasurements().get("Test");
        ArrayList<Measurement> tmp2 = new ArrayList<Measurement>();
        tmp2.add(m);

        assertEquals(tmp, tmp2);
    }
}