package observer.threaded_demo;

import java.util.Random;

public class DataRandomizer {
    public DataRandomizer(){}

    public Measurement measure(boolean[] thp, String location){
        int T, H, P;
        Random random = new Random();
        T = random.nextInt(100) - 50; //celcius
        H = random.nextInt(101); //percentage
        P = random.nextInt(20) + 90; //kiloPascal

        if(!thp[0]) T = -300; //avoiding null, -300 should be impossible for all taken measurements in natural circumstances
        if(!thp[1]) H = -300;
        if(!thp[2]) P = -300;

        return new Measurement(T, H, P, location);
    }
}
