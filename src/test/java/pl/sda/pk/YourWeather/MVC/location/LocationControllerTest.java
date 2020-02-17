package pl.sda.pk.YourWeather.MVC.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
class LocationControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private LocationDTOTransformer locationDTOTransformer;
    @MockBean
    private LocationService locationService;

    @Test
    void when_request_get_all_then_locations_should_be_return() throws Exception {
        //given
        when(locationService.getAllLocation("ASC")).thenReturn(Collections.singletonList(locationDTOTransformer.toLocationDTO(new Location("Szczecin", "PL", 20.00F, 20.00F, "Polska", null))));

        //when and then
        mvc.perform(MockMvcRequestBuilders.get(("/locations"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void when_valid_location_then_insert_record() throws Exception {
        //given
        //when and then
        mvc.perform(post("/locations").contentType(MediaType.APPLICATION_JSON)
                .content("{\"cityName\":\"Warsaw\",\"region\":\"PL\",\"countryName\":\"Polska\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void when_invalid_location_then_should_be_error_return() throws Exception {
        //given

        //when and then
        mvc.perform(post("/locations").contentType(MediaType.APPLICATION_JSON)
                .content("{\"cityName\":\"\",\"region\":\"PL\",\"countryName\":\"Polska\"}"))
                .andExpect(status().is4xxClientError());


    }

    @Test
    void return_recipe_with_id_after_add_new_one_to_db() throws Exception {
        //given
        when(locationService.addLocation(any(Location.class))).thenReturn(new Location("Warsaw", "POL", 20.00F, 20.00F, "Poland", null));
        //when and then
        mvc.perform(post("/locations").contentType(MediaType.APPLICATION_JSON)
                .content("{\"cityName\":\"Warsaw\",\"region\":\"PL\",\"countryName\":\"Polska\"}"))
                .andExpect(jsonPath("$.cityName", equalTo("Warsaw")))
                .andExpect(jsonPath("$.region", equalTo("PL")));
    }

    @Test
    void abc() throws Exception {
        //given
        when(locationService.getLocationByCityName("Warsaw")).thenReturn(java.util.Optional.of(locationDTOTransformer.toLocationDTO(new Location("Warsaw", "POL", 20.00F, 20.00F, "Poland", null))));
        //when and then
        mvc.perform(get("/locations/city/{cityName}", "Warsaw").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cityName", equalTo("Warsaw")));
    }

}