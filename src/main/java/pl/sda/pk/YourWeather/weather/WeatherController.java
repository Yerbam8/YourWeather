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
<<<<<<< HEAD
    public WeatherDTO addWeather(@RequestBody  WeatherDTO weatherDTO) {
        return weatherService.addWeather(weatherDTO);
=======
<<<<<<< HEAD
    public WeatherDTO addWeather(@RequestBody WeatherDTO weatherDTO) {
        return weatherService.addWeather(weatherDTO);
=======
    public WeatherDTO addWeather(@RequestBody  WeatherDTO weather) {
        return weatherService.addWeather(weather);
>>>>>>> fd1d3bf6d1457666ea2d8a1ec3b29984301479f1
>>>>>>> 8c8bc83b161da695995c7288cc06bb9f77b20a11
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
