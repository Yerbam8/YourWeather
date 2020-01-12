package pl.sda.pk.YourWeather.weather.feature1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("weatherRepository")
public interface WeatherRepository extends JpaRepository<Weather, String> {

}
