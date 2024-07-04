package observer.threaded_demo;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Kupa {
    private TreeSet<String> locations;
    private TreeMap<String, ArrayList<Measurement>> measurements;
    public String username;

    public Kupa(TreeSet<String> locations, TreeMap<String, ArrayList<Measurement>> measurements) {
        this.locations = locations;
        this.measurements = measurements;
    }

    public void sendNotification(String l, Measurement m) {
        if(measurements.get(l) == null){
            ArrayList<Measurement> tmp = new ArrayList<Measurement>();
            measurements.put(l, tmp);
        }
        measurements.get(l).add(m);
    }

    public TreeSet<String> getLocations() {
        return locations;
    }


    public Map<String, ArrayList<Measurement>> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(TreeMap<String, ArrayList<Measurement>> measurements) {
        this.measurements = measurements;
    }
}
