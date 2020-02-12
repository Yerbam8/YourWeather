package pl.sda.pk.YourWeather.MVC.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{cityName}/{country}")
    public Weather getFromApi(@PathVariable("cityName") String cityName,
                              @PathVariable("country") String country) {
        return weatherService.getWeatherFromWeatherBitApi(cityName, country);
    }

    @GetMapping("/{cityName}")
    public Weather getFromApi(@PathVariable String cityName) {
        return weatherService.getWeatherFromApiByName(cityName);
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
