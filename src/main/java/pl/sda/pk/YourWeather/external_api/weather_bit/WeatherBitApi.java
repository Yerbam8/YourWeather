package pl.sda.pk.YourWeather.external_api.weather_bit;


import java.util.List;

public class WeatherBitApi {

    private List<WeatherBitData> data;

    public List<WeatherBitData> getData() {
        return data;
    }

    public void setData(List<WeatherBitData> data) {
        this.data = data;
    }
}
