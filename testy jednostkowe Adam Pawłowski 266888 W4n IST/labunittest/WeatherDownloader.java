package labunittest;

import javax.xml.ws.http.HTTPException;
import java.security.InvalidParameterException;
import java.util.Random;

public class WeatherDownloader {

    WeatherDownloadWorker weatherDownloadWorker;

    public WeatherDownloader() {
        weatherDownloadWorker = new WeatherDownloadWorker();
    }

    public WeatherObject getByCityName(UrlObject urlObject) {
        if (urlObject.getParameters().containsKey("city")) {
            if (weatherDownloadWorker.downloadData()) {
                return new WeatherObject(getWeather(), getTemperature(), getPressure(), getHumidity());
            } else {
                throw new HTTPException(404);
            }
        } else {
            throw new InvalidParameterException();
        }
    }

    public WeatherObject getByCoordinates(UrlObject urlObject) {
        if (urlObject.getParameters().containsKey("lon") && urlObject.getParameters().containsKey("lat")) {
            if (weatherDownloadWorker.downloadData()) {
                return new WeatherObject(getWeather(), getTemperature(), getPressure(), getHumidity());
            } else {
                throw new HTTPException(404);
            }
        } else {
            throw new InvalidParameterException();
        }
    }

    private double getTemperature() {
        Random r = new Random();
        return (double) (r.nextInt(100) + 250) / 10.0;
    }

    private double getPressure() {
        Random r = new Random();
        return 990.0 + r.nextInt(25);
    }

    private double getHumidity() {
        Random r = new Random();
        return (double)r.nextInt(100);
    }

    private Weather getWeather(){
        Random r = new Random();
        int ix = r.nextInt(4);
        return Weather.values()[ix];
    }
}
