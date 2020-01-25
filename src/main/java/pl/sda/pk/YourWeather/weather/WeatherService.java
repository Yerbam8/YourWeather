package pl.sda.pk.YourWeather.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.pk.YourWeather.location.Location;
import pl.sda.pk.YourWeather.location.LocationRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;
    private final WeatherDTOTransformer weatherDTOTransformer;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository,
                          LocationRepository locationRepository,
                          WeatherDTOTransformer weatherDTOTransformer) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
        this.weatherDTOTransformer = weatherDTOTransformer;
    }

    public WeatherDTO addWeather(WeatherDTO weatherDTO) {
        Weather weather = weatherDTOTransformer.toEntity(weatherDTO);

        String locationId = weather.getLocation().getId();
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new NoSuchElementException("no location found"));
        weather.setLocation(location);
        Weather savedWeather = weatherRepository.save(weather);
        location.getWeathers().add(savedWeather);
        locationRepository.save(location);
        return weatherDTOTransformer.toWeatherDTO(savedWeather);
    }

    public List<WeatherDTO> getWeather(Map<String, String> params) {
        if (params.containsKey("id")) {
            return Collections.singletonList(weatherDTOTransformer.toWeatherDTO(weatherRepository
                    .findById(Long.parseLong(params.get("id")))
                    .orElseThrow(() -> new NoSuchElementException("weather not found"))));
        } else if (params.containsKey("date")) {
            return Collections.singletonList(weatherDTOTransformer.toWeatherDTO(weatherRepository
                    .findByDate(LocalDate.parse(params.get("date")))
                    .orElseThrow(() -> new NoSuchElementException("weather not found"))));
        } else {
            throw new IllegalArgumentException("no argument passed");
        }
    }

    public List<WeatherDTO> getAllWeathers(String sort) {
        Sort sorting = Sort.by(Sort.Direction.ASC, "date");
        if ("DESC".equals(sort)) {
            sorting = Sort.by(Sort.Direction.DESC, "date");
        }
        return weatherRepository.findAll(sorting).stream()
                .map(weatherDTOTransformer::toWeatherDTO)
                .collect(Collectors.toList());
    }

    public void removeWeather(long id) {

        Weather weatherToRemove = weatherRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("element not found"));

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
