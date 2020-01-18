package pl.sda.pk.YourWeather.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("weatherRepository")
public interface WeatherRepository extends JpaRepository<Weather, String> {

    Optional<Weather> findByDate(String date);
}
