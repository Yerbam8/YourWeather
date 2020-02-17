package pl.sda.pk.YourWeather.MVC.location;

import org.springframework.stereotype.Component;
import pl.sda.pk.YourWeather.external_api.arithmetics.Arithmetics;
import pl.sda.pk.YourWeather.external_api.open_weather_api.WeatherApi;
import pl.sda.pk.YourWeather.external_api.weather_bit.WeatherBitApi;
import pl.sda.pk.YourWeather.external_api.weather_bit.WeatherBitData;

import java.util.Arrays;

@Component
public class LocationDTOTransformer {

    public LocationDTO toLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setFullName(location.getCityName() + ", " + location.getRegion());
        locationDTO.setFullCoordinate(location.getLatitude() + ", " + location.getLongitude());
        return locationDTO;
    }

    public static LocationDTO getAverageWeather(WeatherBitApi weatherBitApi, WeatherApi weatherApi) {
        LocationDTO locationDTO = new LocationDTO();
        WeatherBitData weatherBitData = weatherBitApi.getData().get(0);

        locationDTO.setHumidity(Arithmetics.getAverageInteger(Arrays.asList(
                (int) weatherApi.getMain().getHumidity(),
                weatherBitData.getHumidity()
        )));
        locationDTO.setPressure(Arithmetics.getAverageInteger(Arrays.asList(
                (int) weatherApi.getMain().getPressure(),
                weatherBitData.getPres()
        )));
        locationDTO.setTemp(Arithmetics.getAverageFloat(Arrays.asList(
                weatherApi.getMain().getTemp(),
                weatherBitData.getTemp()
        )));
        locationDTO.setWindSpeed(Arithmetics.getAverageInteger(Arrays.asList(
                weatherApi.getWind().getSpeed(),
                weatherBitData.getWindSpeed()
        )));
        locationDTO.setFullCoordinate(weatherApi.getCoord().getLat() + "/" + weatherApi.getCoord().getLon());
        locationDTO.setFullName(weatherBitData.getCityName() + ", " + weatherBitData.getCountryCode());
        return locationDTO;
    }

}