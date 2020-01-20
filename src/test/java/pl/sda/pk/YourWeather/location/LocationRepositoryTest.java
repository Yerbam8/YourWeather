package pl.sda.pk.YourWeather.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    public void save_location_then_return_one(){
        //given
        Location location = new Location();
        location.setLongitude(20.00F);  
        location.setLatitude(20.00F);
        location.setRegion("Pol");
        location.setCityName("Warsaw");
        location.setCountryName("Poland");
        //when
        locationRepository.save(location);
        Optional<Location> location1= locationRepository.findByCityName("Warsaw");
        //then
        assertEquals(location,location1.get());

    }
}