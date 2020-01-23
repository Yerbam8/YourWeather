package pl.sda.pk.YourWeather.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weathers")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(@Qualifier("weatherService") @Valid WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<WeatherDTO> getWeather(@RequestParam Map<String, String> param) {
        return weatherService.getWeather(param);
    }

    @GetMapping("/all_weathers")
    public List<WeatherDTO> getAllWeathers(@RequestParam(required = false) String sort) {
        return weatherService.getAllWeathers(sort);
    }

    @PostMapping
    public WeatherDTO addWeather(@RequestBody WeatherDTO weatherDTO) {
        return weatherService.addWeather(weatherDTO);

    }

    @DeleteMapping
    public void removeWeather(@RequestParam long id) {
        weatherService.removeWeather(id);
    }

    @PutMapping("/{id}")
    public Weather updateWeather(@PathVariable Long id,
                                 @RequestBody Weather weather) {
        return weatherService.updateWeather(id, weather);
    }
}
