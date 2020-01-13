package pl.sda.pk.YourWeather.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @DeleteMapping("/{id}")
    public void removeLocation(@PathVariable String id) {
        locationService.removeLocation(id);
    }

    @GetMapping("/{id}")
    public Optional<Location> getLocationById(@PathVariable String id) {
        return locationService.getLocationById(id);
    }

    @GetMapping
    public List<Location> getAllLocation(){
        return locationService.getAllLocation();
    }
    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable String id,@RequestBody Location location){
        return locationService.updateLocation(id,location);
    }
}
