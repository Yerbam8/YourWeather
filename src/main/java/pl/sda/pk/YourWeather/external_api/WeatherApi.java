package pl.sda.pk.YourWeather.external_api;

public class WeatherApi {
    private WeatherMainInfo main;
    private WeatherCoord coord;
    private String cityName;

    public WeatherCoord getCoord() {
        return coord;
    }

    public void setCoord(WeatherCoord coord) {
        this.coord = coord;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public WeatherMainInfo getMain() {
        return main;
    }

    public void setMain(WeatherMainInfo main) {
        this.main = main;
    }
}
