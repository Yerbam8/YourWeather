package pl.sda.pk.YourWeather.weather;

import org.springframework.stereotype.Component;
import pl.sda.pk.YourWeather.location.Location;

@Component("weatherDTOTransformer")
public class WeatherDTOTransformer {

    public Weather toEntity(WeatherDTO weatherDTO) {
        Weather weather = new Weather();

        weather.setDate(weatherDTO.getDate());
        weather.setHumidity(weatherDTO.getHumidity());
        weather.setPressure(weatherDTO.getPressure());
        weather.setTemp(weatherDTO.getTemp());
        weather.setWindDirections(weatherDTO.getWindDirection());
        weather.setWindSpeed(weatherDTO.getWindSpeed());

        Location location = new Location();
        location.setId(weatherDTO.getLocationId());

        weather.setLocation(location);

        return weather;
    }

    public WeatherDTO toWeatherDTO(Weather weather) {
        WeatherDTO weatherDTO = new WeatherDTO();

        weatherDTO.setId(weather.getId());
        weatherDTO.setDate(weather.getDate());
        weatherDTO.setHumidity(weather.getHumidity());
        weatherDTO.setPressure(weather.getPressure());
        weatherDTO.setTemp(weather.getTemp());
        weatherDTO.setWindSpeed(weather.getWindSpeed());
        weatherDTO.setWindDirection(weather.getWindDirections());
        weatherDTO.setLocationId(weather.getLocation().getId());
        weatherDTO.setLocationName(weather.getLocation().getCityName());

        return weatherDTO;
    }
}
