package labunittest;

public class WeatherObject {
    private Weather weather;
    private double temperature;
    private double pressure;
    private double humidity;

    public WeatherObject(Weather weather, double temperature, double pressure, double humidity) {
        this.weather = weather;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "weather=" + weather +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
