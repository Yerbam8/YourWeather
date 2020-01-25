package pl.sda.pk.YourWeather.external_api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherApiFetcher {
    private static final String URL = "https://samples.openweathermap.org/data/2.5/weather?q=%s&appid=36301f0513df71557a1c4f1b859b83f3";

    public WeatherApi getWeather(String location) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherApi weatherApi = restTemplate.getForObject(String.format(URL,location), WeatherApi.class);
        return weatherApi;
    }


}
