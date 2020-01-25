package pl.sda.pk.YourWeather.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.pk.YourWeather.location.Location;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        WeatherDTO weatherDTO = new WeatherDTO(1L,
                0, 10, 1000,
                LocalDate.now(), WindDirections.EAST,
                1, "Szczecin",
                "1");

        when(weatherService.getAllWeathers(any()))
                .thenReturn(Collections.singletonList(weatherDTO));

        //when and then
        mvc.perform(get("/weathers/all_weathers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void when_add_valid_weather_ok_status_should_be_returned() throws Exception {
        //given
        //when and then
        mvc.perform(post("/weathers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"temp\":0," +
                        "\"humidity\":1," +
                        "\"pressure\":1000," +
                        "\"date\":\"10-10-1999\"," +
                        "\"windDirection\":\"NORTH\"," +
                        "\"windSpeed\":10," +
                        "\"locationName\":\"Szczcin\"," +
                        "\"locationId\":\"1\"" +
                        "}"))
                .andExpect(status().isOk());
    }

}