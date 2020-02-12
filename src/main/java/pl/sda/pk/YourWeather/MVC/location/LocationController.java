package pl.sda.pk.YourWeather.MVC.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;


    @Autowired
    public LocationController(@Qualifier("locationService") LocationService locationService) {
        this.locationService = locationService;

    }

    @GetMapping("/api/{cityName}")
    public LocationDTO getFromApi(@PathVariable String cityName){
        return locationService.getLocationFromApiByName(cityName);
    }


    @PostMapping
    public Location addLocation(@RequestBody @Valid Location locationDTO) {
        return locationService.addLocation(locationDTO);
    }

    @DeleteMapping("/{id}")
    public void removeLocation(@PathVariable String id) {
        locationService.removeLocation(id);
    }

    @GetMapping("/{id}")
    public Optional<LocationDTO> getLocationById(@PathVariable String id) {
        return locationService.getLocationById(id);
    }

    @GetMapping
    public List<LocationDTO> getAllLocation(@RequestParam(required = false) String sort) {
        return locationService.getAllLocation(sort);
    }

    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable String id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }

    @GetMapping("/city/{cityName}")
    public Optional<LocationDTO> getByCityName(@PathVariable String cityName) {
        return locationService.getLocationByCityName(cityName);
    }
    @GetMapping("/country/{country}")
    public Optional<LocationDTO> getByCountryName(@PathVariable String country){
        return locationService.getLocationByCountry(country);
    }
    @GetMapping("/{lat}/{longitude}")
    public Optional<LocationDTO> getByLatAndLong(@PathVariable float lat,@PathVariable float longitude){
        return locationService.getLocationByLatAndLong(lat,longitude);
    }
    @GetMapping("/region/{region}")
    public Optional<LocationDTO> getLocationByRegion(@PathVariable String region){
        return locationService.getLocationByRegion(region);
    }
}
