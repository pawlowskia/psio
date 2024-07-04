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


public class TestCsi {
    Sensor s = null;
    Utility u = null;

    @Test
    public void testCsiExportSensors() throws FileNotFoundException {
        Csi csi = new Csi(new DataRandomizer());
        csi.exportSensors();
        Scanner in = new Scanner(new File("sensorList.json"));

        assertEquals(in.nextLine(), "[{\"location\":\"Jelenia\",\"t\":false,\"h\":false,\"p\":false},{\"location\":\"Legnica\",\"t\":false,\"h\":true,\"p\":false},{\"location\":\"Lubin\",\"t\":true,\"h\":false,\"p\":false},{\"location\":\"Walbrzych\",\"t\":true,\"h\":false,\"p\":true},{\"location\":\"Wroclaw\",\"t\":true,\"h\":true,\"p\":true}]");
    }

    @Test
    public void testMeasureAll() throws FileNotFoundException, InterruptedException {
        DataRandomizer dr = mock(DataRandomizer.class);
        when(dr.measure(new boolean[]{true, true, true}, "Wroclaw")).thenReturn(new Measurement(10, 10, 10, "Wroclaw"));
        when(dr.measure(new boolean[]{true, false, true}, "Walbrzych")).thenReturn(new Measurement(10, 10, 10, "Walbrzych"));
        when(dr.measure(new boolean[]{false, true, false}, "Legnica")).thenReturn(new Measurement(10, 10, 10, "Legnica"));
        when(dr.measure(new boolean[]{false, false, true}, "Jelenia Gora")).thenReturn(new Measurement(10, 10, 10, "Jelenia Gora"));
        when(dr.measure(new boolean[]{true, false, false}, "Lubin")).thenReturn(new Measurement(10, 10, 10, "Lubin"));
        Csi csi = new Csi(dr);
        csi.measureAll(dr);

        TreeMap<String, Measurement> returned = csi.getLastMeasurement();

        assertEquals(returned.toString(), "{Jelenia=null, Legnica=10C, 10%, 10kPa, Lubin=10C, 10%, 10kPa, Walbrzych=10C, 10%, 10kPa, Wroclaw=10C, 10%, 10kPa}");
    }
    @Test
    public void testSaveToJson() throws FileNotFoundException, InterruptedException {
        DataRandomizer dr = mock(DataRandomizer.class);
        when(dr.measure(new boolean[]{true, true, true}, "Wroclaw")).thenReturn(new Measurement(10, 10, 10, "Wroclaw"));
        when(dr.measure(new boolean[]{true, false, true}, "Walbrzych")).thenReturn(new Measurement(10, 10, 10, "Walbrzych"));
        when(dr.measure(new boolean[]{false, true, false}, "Legnica")).thenReturn(new Measurement(10, 10, 10, "Legnica"));
        when(dr.measure(new boolean[]{false, false, true}, "Jelenia Gora")).thenReturn(new Measurement(10, 10, 10, "Jelenia Gora"));
        when(dr.measure(new boolean[]{true, false, false}, "Lubin")).thenReturn(new Measurement(10, 10, 10, "Lubin"));

        Csi csi = new Csi(dr);
        csi.startObservable();

        u = new Utility();
        u = mock(Utility.class);
        when(u.scanString()).thenReturn("a");
        when(u.scanInt(1, 1)).thenReturn(1);

        csi.register(u);
        csi.addLoc(u);

        System.out.println("please, wait 10 seconds for the test to end. do not enter any data");
        Thread.sleep(10000);


        csi.saveToJson();
        csi.stopObservable();

        Scanner in = new Scanner(new File("measured_data.json"));

        assertEquals(in.nextLine(), "[{\"locations\":[\"Wroclaw\"],\"measurements\":{\"Wroclaw\":[{\"t\":10,\"h\":10,\"p\":10,\"location\":\"Wroclaw\"},{\"t\":10,\"h\":10,\"p\":10,\"location\":\"Wroclaw\"}]},\"username\":\"a\"}]");
    }
}
