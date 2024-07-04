package labunittest;

import org.junit.Test;
import javax.xml.ws.http.HTTPException;
import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestWeatherDownloader {
    WeatherDownloader w = null;
    WeatherObject tmp = null;
    UrlObject url = null;

    //testing getCityByName
    @Test
    public void testGetCityByName(){
        w = new WeatherDownloader();
        w.weatherDownloadWorker = mock(WeatherDownloadWorker.class);
        url = new UrlObject();
        url.parameters.put("city", "London");
        when(w.weatherDownloadWorker.downloadData()).thenReturn(true);

        assertEquals("Result", WeatherObject.class, w.getByCityName(url).getClass());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetCityByNameThrowsIPE(){
        w = new WeatherDownloader();
        w.weatherDownloadWorker = mock(WeatherDownloadWorker.class);
        url = new UrlObject();
        url.parameters.put("miasto", "Wroclaw");
        when(w.weatherDownloadWorker.downloadData()).thenReturn(true);

        tmp = w.getByCityName(url);
    }

    @Test(expected = javax.xml.ws.http.HTTPException.class)
    public void testGetCityByNameThrowsHTTPE(){
        w = new WeatherDownloader();
        w.weatherDownloadWorker = mock(WeatherDownloadWorker.class);
        url = new UrlObject();
        url.parameters.put("city", "London");
        when(w.weatherDownloadWorker.downloadData()).thenReturn(false);

        tmp = w.getByCityName(url);
    }

    //testing getCityByCoordinates
    @Test
    public void testGetCityByCoordinates(){//lat={lat}&lon={lon}
        w = new WeatherDownloader();
        w.weatherDownloadWorker = mock(WeatherDownloadWorker.class);
        url = new UrlObject();
        url.parameters.put("lon", "0");
        url.parameters.put("lat", "0");
        when(w.weatherDownloadWorker.downloadData()).thenReturn(true);

        assertEquals("Result", WeatherObject.class, w.getByCoordinates(url).getClass());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetCityByCoordinatesThrowsIPE(){
        w = new WeatherDownloader();
        w.weatherDownloadWorker = mock(WeatherDownloadWorker.class);
        url = new UrlObject();
        url.parameters.put("city", "London");
        when(w.weatherDownloadWorker.downloadData()).thenReturn(true);

        tmp = w.getByCoordinates(url);
    }

    @Test(expected = javax.xml.ws.http.HTTPException.class)
    public void testGetCityByCoordinatesThrowsHTTPE(){
        w = new WeatherDownloader();
        w.weatherDownloadWorker = mock(WeatherDownloadWorker.class);
        url = new UrlObject();
        url.parameters.put("lat", "0");
        url.parameters.put("lon", "0");
        when(w.weatherDownloadWorker.downloadData()).thenReturn(false);

        tmp = w.getByCoordinates(url);
    }

}
