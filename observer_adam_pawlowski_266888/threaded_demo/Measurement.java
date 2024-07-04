package observer.threaded_demo;

public class Measurement {
    private int t, h, p;
    private String location;

    public Measurement(int t, int h, int p, String location) {
        this.t = t;
        this.h = h;
        this.p = p;
        this.location = location;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return  t + "C, " +
                h + "%, " +
                p + "kPa";
    }
}
