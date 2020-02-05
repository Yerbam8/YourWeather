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
import pl.sda.pk.YourWeather.core.LocationAlreadyExistException;
import pl.sda.pk.YourWeather.external_api.OpenWeatherApiFetcher;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LocationServiceTest {

    @TestConfiguration
    static class LocationServiceTestContextConfiguration {
        @Bean
        public LocationService locationService(LocationRepository locationRepository, LocationDTOTransformer locationDTOTransformer, OpenWeatherApiFetcher openWeatherApiFetcher) {
            return new LocationService(locationRepository, openWeatherApiFetcher, locationDTOTransformer);
        }
    }

    @Autowired
    LocationDTOTransformer locationDTOTransformer;
    @Autowired
    OpenWeatherApiFetcher openWeatherApiFetcher;
    @MockBean
    private LocationRepository locationRepository;
    @Autowired
    private LocationService locationService;

    @Test
    void when_add_valid_item_it_should_be_insert_to_db() {
        //given
        Location location = new Location("Warsaw", "POL", 20.00F, 20.00F, "Poland", null);
        //when
        locationService.addLocation(location);
        //then
        Mockito.verify(locationRepository).save(location);
    }

    @Test
    void given_invalid_location_then_location_should_not_be_added() {
        //given
        Location location = new Location("", "P", 20.00F, 20.00F, "Poland", null);
        //when
        //then
        when(locationService.addLocation(location)).thenThrow(IllegalArgumentException.class);
    }

    @Test
    void when_try_to_add_duplicate_location_then_exception_should_be_thrown() {
        //given
        String cityName = "Warsaw";
        float lat = 0.0f;
        float longi = 0.0f;
        Location location = new Location();
        location.setCityName("Warsaw");
        location.setLatitude(0.0f);
        location.setLongitude(0.0f);
        when(locationRepository.findByCityNameAndLongitudeAndLatitude(cityName, lat, longi)).thenReturn(Optional.of(location));
        //when
        //then
        assertThrows(LocationAlreadyExistException.class, () -> {
            locationService.addLocation(location);
        });
    }

    @Test
    void when_get_by_name_it_should_be_return_location() {
        //given
        Location location = new Location("Warsaw", "POL", 20.00F, 20.00F, "Poland", null);
        LocationDTO locationDTO = locationDTOTransformer.toLocationDTO(location);
        when(locationService.getLocationByCityName("Warsaw")).thenReturn(Optional.of(locationDTO));
        //when
        locationService.addLocation(location);
        Optional<LocationDTO> locationReturn = locationService.getLocationByCityName("Warsaw");

        //then
        assertEquals(location, locationReturn.get());
    }

    @Test
    void when_remove_location_which_not_exist_in_db() {
        //given
        when(locationRepository.findById(any()))
                .thenReturn(Optional.ofNullable(null));
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            locationService.removeLocation("1");
        });
    }


}

