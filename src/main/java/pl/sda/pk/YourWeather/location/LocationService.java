package pl.sda.pk.YourWeather.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("locationService")
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(@Qualifier("locationRepository") LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public void removeLocation(String id) {
        locationRepository.findAll().stream()
                .filter(location -> id.equals(location.getId()))
                .findAny().orElseThrow(() -> new NoSuchElementException("Location no found"));
        locationRepository.deleteById(id);
    }

    public Optional<Location> getLocationById(String id) {
        return locationRepository.findById(id);
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
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
        if (location.getLatitude()!=locationToUpdate.getLatitude()) {
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


}