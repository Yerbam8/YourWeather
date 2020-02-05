package pl.sda.pk.YourWeather.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<Location,String> {
    Optional<Location> findByCityNameAndLongitudeAndLatitude(String placeName, float longi, float lat);
    Optional<Location> findByLatitudeAndLongitude(float Lat,float Long);
    Optional<Location> findByCityName(String cityName);
    Optional<Location> findByCountryName(String countryName);
    Optional<Location> findByRegion(String region);
}
