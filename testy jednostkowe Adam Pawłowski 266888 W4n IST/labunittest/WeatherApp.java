package labunittest;

import java.util.Scanner;

public class WeatherApp {

    private WeatherDownloader weatherDownloader;
    private UrlParser urlParser;

    public WeatherApp(WeatherDownloader weatherDownloader, UrlParser urlParser) {
        this.weatherDownloader = weatherDownloader;
        this.urlParser = urlParser;
    }

    public static void main(String[] args) {
        WeatherDownloader weatherDownloader = new WeatherDownloader();
        UrlParser urlParser = new UrlParser();
        WeatherApp weatherApp = new WeatherApp(weatherDownloader, urlParser);
        weatherApp.runApp();
    }

    void runApp() {
        try {
            System.out.println("Enter url to download weather, currently supporting: ");
            System.out.println("1) By city: http://getmyweather.com/byCity?city={cityName}");
            System.out.println("2) By coordinates: http://getmyweather.com/byCoordinates?lat={lat}&lon={lon}");
            System.out.println("Enter url to download weather: ");
            Scanner scanner = new Scanner(System.in);
            String url = scanner.next();
            UrlObject urlObject = this.urlParser.parseUrl(url);
            if (urlObject.getPath().contains("byCity")) {
                WeatherObject result = weatherDownloader.getByCityName(urlObject);
                System.out.println(result.toString());
            } else if (urlObject.getPath().contains("byCoordinates")) {
                WeatherObject result = weatherDownloader.getByCoordinates(urlObject);
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
