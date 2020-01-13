package pl.sda.pk.YourWeather.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("locationRepository")
public interface LocationRepository extends JpaRepository<Location,String> {
}
