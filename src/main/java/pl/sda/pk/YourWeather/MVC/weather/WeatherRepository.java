package pl.sda.pk.YourWeather.MVC.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository("weatherRepository")
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findByDate(LocalDate date);
}
