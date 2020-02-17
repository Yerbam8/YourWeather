package pl.sda.pk.YourWeather.external_api.weather_bit;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherBitApiFetcher {

    public WeatherBitApi getWeather(String location, String countryCode) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://api.weatherbit.io/v2.0/current?city=%s,%s&key=0be37336da2743f88705f905d8494ff4";
        return restTemplate.getForObject(String.format(URL, location, countryCode), WeatherBitApi.class);
    }
}
