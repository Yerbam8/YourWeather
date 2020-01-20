package pl.sda.pk.YourWeather.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LocationServiceTest {

    @TestConfiguration
    static class LocationServiceTestConfiguration {
        @Bean
        public LocationService locationService(@Qualifier("locationRepository") LocationRepository locationRepository) {
            return new LocationService(locationRepository);
        }
    }

    @MockBean
    private LocationRepository locationRepository;
    @Autowired
    private LocationService locationService;

    @Test
    void when_add_valid_item_it_should_be_insert_to_db() {
        //given
        Location location = new Location();
        location.setLongitude(20.00F);
        location.setLatitude(20.00F);
        location.setRegion("Pol");
        location.setCityName("Warsaw");
        location.setCountryName("Poland");
        //when
        locationService.addLocation(location);
        //then
        verify(locationRepository).save(location);
    }

    @Test
    void when_get_by_name_it_should_be_return_location() {
        //given
        Location location = new Location();
        location.setLongitude(20.00F);
        location.setLatitude(20.00F);
        location.setRegion("Pol");
        location.setCityName("Warsaw");
        location.setCountryName("Poland");
        Mockito.when(locationService.getLocationByCityName("Warsaw")).thenReturn(Optional.of(location));
        //when
        locationService.addLocation(location);
        Optional<Location> locationReturn = locationService.getLocationByCityName("Warsaw");

        //then
        assertEquals(location, locationReturn.get());
    }
    @Test
    void when_remove_location_return_true(){
        //given
        Location location = new Location();
       
        Mockito.when(locationRepository.findById(any())).thenReturn(Optional.of(location));
        //when
         locationService.removeLocation("abc");




        //then
        verify(locationRepository).deleteById("abc");
    }

}