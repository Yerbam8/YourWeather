package pl.sda.pk.YourWeather.weather;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.pk.YourWeather.location.Location;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class WeatherRepositoryTest {

    @Qualifier("weatherRepository")
    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void when_there_are_no_items_in_db_then_repo_should_return_empty() {
        //given
//        Location location = new Location();
//        WeatherApi weather = new WeatherApi(60,30,1000,"10-10-1999",WindDirections.NORTH,20, location);
        //when
        List<Weather> weatherList = weatherRepository.findAll();
        //then
        assertEquals(0, weatherList.size());
    }

//    @Test
//    void when_item_added_to_db_then_repo_should_return_one_element() {
//        //given
//        Location location = new Location("Szczecin", "Zachodnipomorskie", 0, 0, "Poland",
//                null);
//        WeatherApi weather = new WeatherApi(20, 20, 1000,
//                "10-01-1990" , WindDirections.NORTH, 10, location);
//        //when
//        testEntityManager.persist(weather);
//        List<WeatherApi> weatherList = weatherRepository.findAll();
//        //then
//        assertEquals(1, weatherList.size());
//    }
}