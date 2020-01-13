package pl.sda.pk.YourWeather.weather.feature1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
                .findAny().orElseThrow(() -> new NoSuchElementException("element not found"));
        weatherRepository.delete(weatherToRemove);
    }

    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    public Weather getById(String id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("element not found"));
    }

    public Weather updateWeather(String id, Weather weather) {

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
