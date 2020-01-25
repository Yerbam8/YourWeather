package pl.sda.pk.YourWeather.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.pk.YourWeather.location.Location;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService weatherService;


    @Test
    void when_request_get_all_then_weather_should_be_removed() throws Exception {
        //given
        Location location = new Location();
        location.setId("1");
        location.setCityName("Szczecin");

        WeatherDTO weatherDTO = new WeatherDTO(
                0, 10, 1000,
                LocalDate.now(), WindDirections.EAST,
                1, location.getCityName(),
                location.getId());

        when(weatherService.getAllWeathers("ASC")).thenReturn(Collections.singletonList(
                weatherDTO));

        //when and then
        mvc.perform(get("/weathers").contentType(MediaType.APPLICATION_JSON).content(
        "{}"
        ));

    }
}