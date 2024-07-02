package it.unife.jarvis.backend;

import it.unife.jarvis.backend.api.VenueController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseTests {

    final private VenueController venueController;

    final private MockMvc mockMvc;

    BaseTests(VenueController venueController, MockMvc mockMvc) {
        this.venueController = venueController;
        this.mockMvc = mockMvc;
    }

    @Test
    void sanityCheck () throws Exception {
        assertThat(venueController).isNotNull();
    }

//    @Test
//    void checkExistentVenue() throws Exception {
//        this.mockMvc.perform(get('/api/venue/get/1')).andDo
//    }
}
