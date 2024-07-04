package observer.threaded_demo;

public class Sensor implements Comparable<Sensor>{
    private String location;
    private boolean t, h, p;

    public Sensor(String location, boolean t, boolean h, boolean p) {
        this.location = location;
        this.t = t;
        this.h = h;
        this.p = p;
    }

    public Measurement getData(DataRandomizer dr){
        return dr.measure(new boolean[]{t, h, p}, location);
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int compareTo(Sensor s) {
        return this.location.compareTo(s.location);
    } //I assumed that there is only one sensor per location

    @Override
    public String toString() {
        return "Sensor{" +
                "location='" + location + '\'' +
                ", t=" + t +
                ", h=" + h +
                ", p=" + p +
                '}';
    }
}
