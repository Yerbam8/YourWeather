package pl.sda.pk.YourWeather.weather.feature1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("weatherService")
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(@Qualifier("weatherRepository") WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather addWeather(Weather weather) {
        if (weatherRepository.findById(weather.getId()).isPresent()) {
            throw new IllegalArgumentException("element exists in database");
        } else {
            return weatherRepository.save(weather);
        }
    }

    public void removeWeather(String id) {
        Weather weatherToRemove = weatherRepository.findAll().stream()
                .filter(weather -> id.equals(weather.getId()))
                .findAny().orElseThrow(() -> new NoSuchElementException("object not found"));
        weatherRepository.delete(weatherToRemove);
    }

    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    public Optional<Weather> getById(String id) {
        return weatherRepository.findById(id);
    }

    public void updateWeather() {

    }
}
