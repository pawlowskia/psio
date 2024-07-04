package observer.threaded_demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;
import com.google.gson.Gson;

public class Csi {

    private ArrayList<Kupa> KUPAs;
    private Object KUPAsSemaphore, lastMeasurementSemaphore;
    private boolean shouldContinue;
    private Thread t;
    private TreeSet<Sensor> Sensors;
    private TreeMap<String, Measurement> lastMeasurement;

    public Csi(DataRandomizer dr) throws FileNotFoundException {
        t = new Thread(() -> runInternal(dr));

        KUPAs = new ArrayList<Kupa>();
        shouldContinue = true;
        KUPAsSemaphore = new Object();
        Sensors = new TreeSet<Sensor>();
        readSensors();
        lastMeasurement = new TreeMap<String, Measurement>();
    }

    public void update() {
        synchronized (KUPAsSemaphore) {
            for (Kupa k : KUPAs) {
                for(String l : k.getLocations()){
                    k.sendNotification(l, lastMeasurement.get(l));

                }
            }
        }
    }

    public void runInternal(DataRandomizer dr) {
        while(shouldContinue) {
            measureAll(dr);
            update();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readSensors() throws FileNotFoundException {
        Scanner in = new Scanner(new File("Sensors.txt"));
        while(in.hasNextLine()){
            String line = in.nextLine();

            String[] data = line.split(" ");

            boolean t, h, p; t = h = p = false;

            if(data[1].charAt(0) == '1') t = true;
            if(data[2].charAt(0) == '1') h = true;
            if(data[3].charAt(0) == '1') p = true;

            Sensors.add(new Sensor(data[0], t, h, p));
        }
        //System.out.println(Sensors.toString());
    }

    public void measureAll(DataRandomizer dr){
        for(Sensor s : Sensors){
            lastMeasurement.put(s.getLocation(), s.getData(dr));
        }
    }

    public void register(Utility u) {
        Kupa k = new Kupa(new TreeSet<String>(), new TreeMap<String, ArrayList<Measurement>>());
        System.out.print("Enter username. ");
        k.username = u.scanString();

        synchronized (KUPAsSemaphore) {
            KUPAs.add(k);
        }
    }

    public void startObservable() {
        t.start();
    }

    public void stopObservable() {
        System.out.println("Exitting...");
        shouldContinue = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printAll(){
        for(Kupa k : KUPAs){
            System.out.println(k.username + "'s measurments: ");
            for(String l : k.getMeasurements().keySet()){
                System.out.println("    " + l + ':');
                for(Measurement m : k.getMeasurements().get(l)){
                    System.out.println("    " + m.toString());
                }
            }
        }
        System.out.println();
    }

    public void exportSensors() throws FileNotFoundException {
        Gson gson = new Gson();
        PrintWriter out = new PrintWriter("sensorList.json");
        out.println(gson.toJson(Sensors));
        out.close();
    }

    public void saveToJson() throws FileNotFoundException {
        Gson gson = new Gson();
        PrintWriter out = new PrintWriter("measured_data.json");
        out.println(gson.toJson(KUPAs));
        out.close();
    }

    public void calc(Utility u){
        Kupa tmp = chooseKupa(u);
        if(tmp.getLocations().isEmpty()){
            System.out.println("No locations added, to this KUPA, please add at least one.");
            addLoc(u);
        }
        String key = getLocation(u, tmp);
        if(!tmp.getMeasurements().containsKey(key)){
            System.out.println("This KUPA has no measurements saved for chosen location");
            return;
        }
        ArrayList<Measurement> tmp3 = tmp.getMeasurements().get(key);

        //t
        boolean bad = false;
        int sum = 0, min = 100, max = -100;

        for(Measurement m : tmp3){
            if(m.getT() == -300)
                bad = true;

            sum += m.getT();
            min = Math.min(min, m.getT());
            max = Math.max(max, m.getT());
        }

        if(tmp3.isEmpty())
            System.out.println("For chosen KUPA and location, there is no measurements made yet.");
        else if(bad)
            System.out.println("Sensor in this location cannot measure temperature.");
        else
            System.out.printf("Avg. temperature: %.0fC, minimal temperature: %dC, maximal temperature: %dC%n", (float)(sum)/(float)(tmp3.size()), min, max);

        //h
        bad = false;
        sum = 0; min = 100; max = 0;

        for(Measurement m : tmp3){
            if(m.getH() == -300)
                bad = true;

            sum += m.getH();
            min = Math.min(min, m.getH());
            max = Math.max(max, m.getH());
        }

        if(tmp3.isEmpty())
            System.out.println("For chosen KUPA and location, there is no measurements made yet.");
        else if(bad)
            System.out.println("Sensor in this location cannot measure humidity.");
        else
            System.out.printf("Avg. humidity: %.0f%%, minimal humidity: %d%%, maximal humidity: %d%%%n", (float)(sum)/(float)(tmp3.size()), min, max);

        //p
        bad = false;
        sum = 0; min = 200; max = 0;

        for(Measurement m : tmp3){
            if(m.getP() == -300)
                bad = true;

            sum += m.getP();
            min = Math.min(min, m.getP());
            max = Math.max(max, m.getP());
        }

        if(tmp3.isEmpty())
            System.out.println("For chosen KUPA and location, there is no measurements made yet.");
        else if(bad)
            System.out.println("Sensor in this location cannot measure pressure.");
        else
            System.out.printf("Avg. pressure: %.0fkPa, minimal pressure: %dkPa, maximal pressure: %dkPa%n", (float)(sum)/(float)(tmp3.size()), min, max);
    }

    public void deleteLoc(Utility u){
        Kupa tmp = chooseKupa(u);
        tmp.getLocations().remove(getLocation(u, tmp));
    }

    public void printLoc(Utility u){
        for(String l : chooseKupa(u).getLocations()){
            System.out.println(l);
        }
    }

    public void addLoc(Utility u){
        chooseKupa(u).getLocations().add(chooseFromAllLocations(u));
    }

    private String chooseFromAllLocations(Utility u){
        System.out.println("a) Wroclaw\n" +
                "b) Walbrzych\n" +
                "c) Legnica\n" +
                "d) Jelenia Gora\n" +
                "e) Lubin\n" +
                "Please, insert a character (a-e).");
        String choice = u.scanString(), ret = "Wroclaw";
        switch(choice){
            case "a" :
                ret = "Wroclaw";
                break;
            case "b" :
                ret = "Walbrzych";
                break;
            case "c" :
                ret = "Legnica";
                break;
            case "d" :
                ret = "Jelenia Gora";
                break;
            case "e" :
                ret = "Lubin";
                break;
            default :
                System.out.println("Wrong output, please try again.");
                return chooseFromAllLocations(u);
        }
        return ret;
    }

    private String getLocation(Utility u, Kupa k){
        //choose the location from a tree set
        String searched = "";

        if(k.getLocations() == null){
            System.out.println("No measurements were made for this KUPA at this location yet.");
            return "no location";
        }

        Iterator<String> it = k.getLocations().iterator();
        int num = 1;

        while(it.hasNext()){
            System.out.println(num++ + " " + it.next());
        }
        System.out.print("Choose one location. ");
        int tmp2 = u.scanInt(1, k.getLocations().size());
        it = k.getLocations().iterator();
        num = 1;

        while(it.hasNext()){
            if(num++ == tmp2) return it.next();
            else it.next();
        }
        return "no_location";
    }

    private Kupa chooseKupa(Utility u){
        if(KUPAs.isEmpty()) register(u);
        System.out.println("Choose one KUPA.");

        for(int i = 0; i < KUPAs.size(); i++){
            System.out.println((i + 1) + " " + KUPAs.get(i).username);
        }
        return KUPAs.get(u.scanInt(1, KUPAs.size()) - 1);
    }

    public TreeMap<String, Measurement> getLastMeasurement() {
        return lastMeasurement;
    }

}
