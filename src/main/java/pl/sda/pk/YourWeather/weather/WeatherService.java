package pl.sda.pk.YourWeather.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sda.pk.YourWeather.location.Location;
import pl.sda.pk.YourWeather.location.LocationRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service("weatherService")
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public WeatherService(@Qualifier("weatherRepository") WeatherRepository weatherRepository,
                          @Qualifier("locationRepository") LocationRepository locationRepository) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }

    public Weather addWeather(Weather weather) {
        String locationId = weather.getLocation().getId();
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new NoSuchElementException("no location found"));
        weather.setLocation(location);
        Weather savedWeather = weatherRepository.save(weather);
        location.getWeathers().add(savedWeather);
        locationRepository.save(location);
        return savedWeather;
    }

    public List<Weather> getWeather(Map<String, String> params) {
        if (params.containsKey("id")) {
            return Collections.singletonList(weatherRepository.findById(Long.parseLong(params.get("id")))
                    .orElseThrow(NoSuchElementException::new));
        } else if (params.containsKey("date")) {
            return Collections.singletonList(weatherRepository.findByDate(params.get("date"))
                    .orElseThrow(NoSuchElementException::new));
        } else {
            return weatherRepository.findAll();
        }
    }

    public void removeWeather(String id) {
        Weather weatherToRemove = weatherRepository.findAll().stream()
                .filter(weather -> id.equals(weather.getId()))
                .findAny().orElseThrow(() -> new NoSuchElementException("element not found"));
        weatherRepository.delete(weatherToRemove);
    }

    public Weather updateWeather(Long id, Weather weather) {

        Weather weatherToUpdate = weatherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("element not found"));

        if (weather.getTemp() != weatherToUpdate.getTemp()) {
            weatherToUpdate.setTemp(weather.getTemp());
        }
        if (weather.getHumidity() != weatherToUpdate.getHumidity()) {
            weatherToUpdate.setHumidity(weather.getHumidity());
        }
        if (weather.getPressure() != weatherToUpdate.getPressure()) {
            weatherToUpdate.setPressure(weather.getPressure());
        }
        if (weather.getWindDirections() != weatherToUpdate.getWindDirections()) {
            weatherToUpdate.setWindDirections(weather.getWindDirections());
        }
        if (weather.getWindSpeed() != weatherToUpdate.getWindSpeed()) {
            weatherToUpdate.setWindSpeed(weather.getWindSpeed());
        }
        return weatherRepository.save(weatherToUpdate);
    }
}
