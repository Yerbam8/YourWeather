package pl.sda.pk.YourWeather.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class LocationRepositoryTest {
    @Autowired
    @Qualifier("locationRepository")
    private LocationRepository locationRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void when_no_data_return_empty_list(){
        //given
        //when
        List<Location> locations = locationRepository.findAll();
        //then
        assertEquals(0,locations.size());
    }
    @Test
    public void when_add_one_location_return_list_with_one(){
        //given
        Location location = new Location("Warsaw","POL",20.00F,20.00F,"Poland");
        //when
        testEntityManager.persist(location);
        List<Location> locations = locationRepository.findAll();
        //then
        assertEquals(1,locations.size());
    }

    @Test
    public void save_location_then_return_one(){
        //given
        Location location = new Location("Warsaw","POL",20.00F,20.00F,"Poland");
        //when
        testEntityManager.persist(location);
        Optional<Location> location1= locationRepository.findByCityName("Warsaw");
        //then
        assertEquals(location,location1.get());

    }

}