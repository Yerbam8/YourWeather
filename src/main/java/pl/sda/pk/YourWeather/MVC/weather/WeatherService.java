package pl.sda.pk.YourWeather.MVC.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.pk.YourWeather.external_api.openWeatherApi.OpenWeatherApiFetcher;
import pl.sda.pk.YourWeather.external_api.openWeatherApi.WeatherApi;
import pl.sda.pk.YourWeather.external_api.weather_bit.WeatherBitApi;
import pl.sda.pk.YourWeather.external_api.weather_bit.WeatherBitApiFeather;
import pl.sda.pk.YourWeather.MVC.location.Location;
import pl.sda.pk.YourWeather.MVC.location.LocationRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service("weatherService")
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;
    private final WeatherDTOTransformer weatherDTOTransformer;
    private final OpenWeatherApiFetcher openWeatherApiFetcher;
    private final WeatherBitApiFeather weatherBitApiFeather;

    @Autowired
    public WeatherService(@Qualifier("weatherRepository") WeatherRepository weatherRepository,
                          @Qualifier("locationRepository") LocationRepository locationRepository,
                          @Qualifier("weatherDTOTransformer") WeatherDTOTransformer weatherDTOTransformer,
                          OpenWeatherApiFetcher openWeatherApiFetcher,
                          WeatherBitApiFeather weatherBitApiFeather) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
        this.weatherDTOTransformer = weatherDTOTransformer;
        this.openWeatherApiFetcher = openWeatherApiFetcher;
        this.weatherBitApiFeather = weatherBitApiFeather;
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

    public Weather getWeatherFromApiByName(String name) {
        WeatherApi weatherApi = openWeatherApiFetcher.getWeather(name);
        Weather weather = new Weather();
        weather.setHumidity((int) weatherApi.getMain().getHumidity());
        weather.setPressure((int) weatherApi.getMain().getPressure());
        weather.setTemp((int) weatherApi.getMain().getTemp());
        weather.setWindSpeed(weatherApi.getWind().getSpeed());
        weather.setDate(LocalDate.now());
        Location location = new Location();
        location.setCountryName(weatherApi.getSys().getCountry());
        location.setCityName(weatherApi.getCityName());
        location.setLatitude(weatherApi.getCoord().getLat());
        location.setLongitude(weatherApi.getCoord().getLon());
        weather.setLocation(location);
        return weather;
    }

    public Weather getWeatherFromWeatherBitApi(String name, String country) {
        WeatherBitApi weatherBitApi = weatherBitApiFeather.getWeather(name, country);
        Weather weather = new Weather();
        weather.setWindSpeed((int) weatherBitApi.getData().get(0).getWindSpeed());
        weather.setPressure((int) weatherBitApi.getData().get(0).getPres());
        weather.setTemp((int) weatherBitApi.getData().get(0).getTemp());
        weather.setHumidity((int) weatherBitApi.getData().get(0).getHumidity());
        weather.setDate(
                (LocalDate.of(weatherBitApi.getData().get(0).getLocalDate().getYear(),
                        weatherBitApi.getData().get(0).getLocalDate().getMonth(),
                        weatherBitApi.getData().get(0).getLocalDate().getDayOfMonth()
                )));
        Location location = new Location();
        location.setCountryName(weatherBitApi.getData().get(0).getCountryCode());
        location.setCountryName(weatherBitApi.getData().get(0).getCityName());
        location.setLatitude(weatherBitApi.getData().get(0).getLat());
        location.setLongitude(weatherBitApi.getData().get(0).getLon());
        weather.setLocation(location);

        return weather;
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
