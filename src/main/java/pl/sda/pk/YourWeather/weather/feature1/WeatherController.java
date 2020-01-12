package pl.sda.pk.YourWeather.weather.feature1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(@Qualifier("weatherService") WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public Weather addWeather(@RequestBody @Valid Weather weather){
        return weatherService.addWeather(weather);
    }

    public void removeWeather(String id){
        weatherService.removeWeather(id);
    }

    public List<Weather> geAllWeather(){
        return weatherService.getAllWeather();
    }

    public Optional<Weather> getById(String id){
        return weatherService.getById(id);
    }

    public void updateWeather(){
        weatherService.updateWeather();
    }
}
