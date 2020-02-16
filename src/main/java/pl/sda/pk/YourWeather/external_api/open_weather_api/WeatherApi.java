package pl.sda.pk.YourWeather.external_api.open_weather_api;

public class WeatherApi {
    private String name;
    private WeatherMainInfo main;
    private WeatherCoord coord;
    private WeatherWind wind;
    private String cityName;
    private WeatherSys sys;

    public WeatherSys getSys() {
        return sys;
    }

    public void setSys(WeatherSys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherWind getWind() {
        return wind;
    }

    public void setWind(WeatherWind wind) {
        this.wind = wind;
    }

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
