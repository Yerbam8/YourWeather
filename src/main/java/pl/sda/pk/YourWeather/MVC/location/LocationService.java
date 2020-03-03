package pl.sda.pk.YourWeather.MVC.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.pk.YourWeather.core.LocationAlreadyExistException;
import pl.sda.pk.YourWeather.external_api.open_weather_api.OpenWeatherApiFetcher;
import pl.sda.pk.YourWeather.external_api.open_weather_api.WeatherApi;
import pl.sda.pk.YourWeather.MVC.weather.Weather;
import pl.sda.pk.YourWeather.MVC.weather.WindDirections;
import pl.sda.pk.YourWeather.external_api.weather_bit.WeatherBitApi;
import pl.sda.pk.YourWeather.external_api.weather_bit.WeatherBitApiFetcher;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final OpenWeatherApiFetcher openWeatherApiFetcher;
    private final WeatherBitApiFetcher weatherBitApiFeather;
    private final LocationDTOTransformer locationDTOTransformer;


    @Autowired
    public LocationService(@Qualifier("locationRepository") LocationRepository locationRepository, OpenWeatherApiFetcher openWeatherApiFetcher, WeatherBitApiFetcher weatherBitApiFeather, LocationDTOTransformer locationDTOTransformer) {
        this.locationRepository = locationRepository;
        this.openWeatherApiFetcher = openWeatherApiFetcher;
        this.weatherBitApiFeather = weatherBitApiFeather;
        this.locationDTOTransformer = locationDTOTransformer;
    }

    public Location addLocation(Location location) {
        locationRepository.findByCityNameAndLongitudeAndLatitude(
                location.getCityName(),
                location.getLongitude(),
                location.getLatitude())
                .ifPresent(l -> {
                    throw new LocationAlreadyExistException(
                            location.getCityName(),
                            location.getLongitude(),
                            location.getLatitude());
                });
        return locationRepository.save(location);
    }

    public void removeLocation(String id) {
        locationRepository.findAll().stream()
                .filter(location -> id.equals(location.getId()))
                .findAny().orElseThrow(() -> new NoSuchElementException("Location no found"));
        locationRepository.deleteById(id);
    }

    public Optional<LocationDTO> getLocationById(String id) {
        return Stream.of(locationRepository.findById(id)
                .orElseThrow(NoSuchElementException::new))
                .map(locationDTOTransformer::toLocationDTO)
                .findFirst();
    }

    public List<LocationDTO> getAllLocation(String sort) {

        Sort sorting = Sort.by(Sort.Direction.ASC, "cityName");
        if ("DESC".equals(sort)) {
            sorting = Sort.by(Sort.Direction.DESC, "cityName");
        }
        return locationRepository.findAll(sorting).stream()
                .map(locationDTOTransformer::toLocationDTO)
                .collect(Collectors.toList());

    }

    public Location updateLocation(String id, Location location) {
        Location locationToUpdate = locationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Location doesn't exist"));
        if (location.getCityName() != null) {
            locationToUpdate.setCityName(location.getCityName());
        }
        if (location.getCountryName() != null) {
            locationToUpdate.setCountryName(location.getCountryName());
        }
        if (location.getLatitude() != locationToUpdate.getLatitude()) {
            locationToUpdate.setLatitude(location.getLatitude());
        }
        if (location.getLongitude() != locationToUpdate.getLongitude()) {
            locationToUpdate.setLongitude(location.getLongitude());
        }
        if (location.getRegion() != null) {
            locationToUpdate.setRegion(location.getRegion());
        }

        return locationRepository.save(locationToUpdate);
    }

    public Optional<LocationDTO> getLocationByLatAndLong(float lat, float longitude) {
        return Stream.of(locationRepository.findByLatitudeAndLongitude(lat, longitude)
                .orElseThrow(NoSuchElementException::new))
                .map(locationDTOTransformer::toLocationDTO)
                .findFirst();
    }

    public Optional<LocationDTO> getLocationByCountry(String country) {
        return Stream.of(locationRepository.findByCountryName(country)
                .orElseThrow(NoSuchElementException::new))
                .map(locationDTOTransformer::toLocationDTO)
                .findFirst();
    }

    public Optional<LocationDTO> getLocationByRegion(String region) {
        return Stream.of(locationRepository.findByRegion(region)
                .orElseThrow(NoSuchElementException::new))
                .map(locationDTOTransformer::toLocationDTO)
                .findFirst();
    }

    public Optional<LocationDTO> getLocationByCityName(String cityName) {
        return Stream.of(locationRepository.findByCityName(cityName)
                .orElseThrow(NoSuchElementException::new))
                .map(locationDTOTransformer::toLocationDTO)
                .findFirst();
    }

    public LocationDTO getAverageLocationFromApiByName(String cityName, String countryCode) {
        WeatherApi weatherApi = openWeatherApiFetcher.getWeather(cityName);
        WeatherBitApi weatherBitApi = weatherBitApiFeather.getWeather(cityName, countryCode);
        return LocationDTOTransformer.getAverageWeather(weatherBitApi, weatherApi);
    }


    public LocationDTO getLocationFromApiByName(String cityName) {
        WeatherApi weatherApi = openWeatherApiFetcher.getWeather(cityName);
        Location location = new Location();
        location.setCityName(cityName);
        location.setRegion(weatherApi.getSys().getCountry());
        location.setLatitude(weatherApi.getCoord().getLat());
        location.setLongitude(weatherApi.getCoord().getLon());
        Weather weather = new Weather();
        weather.setHumidity((int) weatherApi.getMain().getHumidity());
        weather.setPressure((int) weatherApi.getMain().getPressure());
        weather.setTemp((int) weatherApi.getMain().getTemp());
        weather.setWindSpeed(weatherApi.getWind().getSpeed());
        weather.setDate(LocalDate.now());
        weather.setWindDirections(convertDegreeToCardinalDirection(weatherApi.getWind().getDegree()));
        location.setWeathers(Collections.singletonList(weather));
        LocationDTO locationDTO = locationDTOTransformer.toLocationDTO(location);
        return locationDTO;
    }

    private WindDirections convertDegreeToCardinalDirection(int directionInDegrees) {
        WindDirections cardinalDirection = null;
        if ((directionInDegrees >= 348.75) && (directionInDegrees <= 360) ||
                (directionInDegrees >= 0) && (directionInDegrees <= 11.25)) {
            cardinalDirection = WindDirections.NORTH;
        } else if ((directionInDegrees >= 11.25) && (directionInDegrees <= 33.75)) {
            cardinalDirection = WindDirections.NORTH_NORTH_EAST;
        } else if ((directionInDegrees >= 33.75) && (directionInDegrees <= 56.25)) {
            cardinalDirection = WindDirections.NORTH_EAST;
        } else if ((directionInDegrees >= 56.25) && (directionInDegrees <= 78.75)) {
            cardinalDirection = WindDirections.EAST_NORTH_EAST;
        } else if ((directionInDegrees >= 78.75) && (directionInDegrees <= 101.25)) {
            cardinalDirection = WindDirections.EAST;
        } else if ((directionInDegrees >= 101.25) && (directionInDegrees <= 123.75)) {
            cardinalDirection = WindDirections.EAST_SOUTH_EAST;
        } else if ((directionInDegrees >= 123.75) && (directionInDegrees <= 146.25)) {
            cardinalDirection = WindDirections.SOUTH_EAST;
        } else if ((directionInDegrees >= 146.25) && (directionInDegrees <= 168.75)) {
            cardinalDirection = WindDirections.SOUTH_SOUTH_EAST;
        } else if ((directionInDegrees >= 168.75) && (directionInDegrees <= 191.25)) {
            cardinalDirection = WindDirections.SOUTH;
        } else if ((directionInDegrees >= 191.25) && (directionInDegrees <= 213.75)) {
            cardinalDirection = WindDirections.SOUTH_SOUTH_WEST;
        } else if ((directionInDegrees >= 213.75) && (directionInDegrees <= 236.25)) {
            cardinalDirection = WindDirections.SOUTH_WEST;
        } else if ((directionInDegrees >= 236.25) && (directionInDegrees <= 258.75)) {
            cardinalDirection = WindDirections.WEST_SOUTH_WEST;
        } else if ((directionInDegrees >= 258.75) && (directionInDegrees <= 281.25)) {
            cardinalDirection = WindDirections.WEST;
        } else if ((directionInDegrees >= 281.25) && (directionInDegrees <= 303.75)) {
            cardinalDirection = WindDirections.WEST_NORTH_WEST;
        } else if ((directionInDegrees >= 303.75) && (directionInDegrees <= 326.25)) {
            cardinalDirection = WindDirections.NORTH_WEST;
        } else if ((directionInDegrees >= 326.25) && (directionInDegrees <= 348.75)) {
            cardinalDirection = WindDirections.NORTH_NORTH_WEST;
        }

        return cardinalDirection;
    }
}













