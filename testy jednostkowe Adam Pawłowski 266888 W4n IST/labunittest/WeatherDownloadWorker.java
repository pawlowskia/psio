package labunittest;

import java.util.Random;

public class WeatherDownloadWorker {

    boolean downloadData() {
        Random r = new Random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r.nextInt(2) == 1;
    }
}
