package pl.sda.pk.YourWeather.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.pk.YourWeather.location.Location;
import pl.sda.pk.YourWeather.location.LocationRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WeatherServiceTest {

    @TestConfiguration
    static class WeatherServiceTestConfiguration {
        @Bean
        public static WeatherService weatherService(WeatherRepository weatherRepository,
                                                    LocationRepository locationRepository,
                                                    WeatherDTOTransformer weatherDTOTransformer) {
            return new WeatherService(weatherRepository, locationRepository, weatherDTOTransformer);
        }
    }

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private WeatherDTOTransformer weatherDTOTransformer;

    @MockBean
    private LocationRepository locationRepository;


    @Test
    void when_add_valid_item_it_should_be_insert_to_db() {
        //given
        WeatherDTO weatherDTO = new WeatherDTO();
        Weather weather = new Weather();
        Location location = new Location();
        location.setId("1");
        location.setWeathers(new ArrayList<>());
        weather.setLocation(location);

        when(weatherDTOTransformer.toEntity(any())).thenReturn(weather);
        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        //when
        weatherService.addWeather(weatherDTO);
        //then
        verify(weatherRepository).save(weather);
    }

    @Test
    void when_remove_item_from_repo_then_return_method_remove_from_repo_should_be_called() {
        //given
        Weather weather = new Weather();
        weather.setId(1L);
        //when
        when(weatherRepository.findById(1L)).thenReturn(Optional.of(weather));
        weatherService.removeWeather(weather.getId());
        //then
        verify(weatherRepository).delete(weather);
    }

    @Test
    void when_no_item_found_to_remove_then_throw_NoSuchElementException() {
        //given
        when(weatherRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> weatherService.removeWeather(1L));
    }


}
