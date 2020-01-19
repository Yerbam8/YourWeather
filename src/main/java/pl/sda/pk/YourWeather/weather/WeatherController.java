package pl.sda.pk.YourWeather.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(@Qualifier("weatherService") WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<WeatherDTO> getWeather(@RequestParam(required = false) Map<String, String> param) {
        return weatherService.getWeather(param);
    }

    @PostMapping
    public WeatherDTO addWeather(@RequestBody  WeatherDTO weatherDTO) {
        return weatherService.addWeather(weatherDTO);
    }

    @DeleteMapping
    public void removeWeather(@RequestParam String id) {
        weatherService.removeWeather(id);
    }

    @PutMapping("/{id}")
    public Weather updateWeather(@PathVariable Long id,
                                 @RequestBody Weather weather) {
        return weatherService.updateWeather(id, weather);
    }
}
