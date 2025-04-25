package dev.sheet_co.request_races;

import dev.sheet_co.request_races.model.dto.RaceRequestDto;
import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.repository.RaceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RaceControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RaceRepository raceRepository;

  @BeforeEach
  void setup() {
//    raceRepository.deleteAll();

  }

  @Test
  void createRaceTest() throws Exception {
    String raceJson = """
        { "name": "Speedy",
          "color": "Red"
        }
        """;

    var request = post("/api/race")
        .contentType(MediaType.APPLICATION_JSON)
        .content(raceJson);

    mockMvc.perform(request).andExpect(status().isCreated());

    var race = raceRepository.findRaceRequestByName("Speedy");
    var expectedName = race.getName();
    var actualName = "Speedy";
    assertNotNull(race);
    assertEquals(actualName, expectedName);
  }

  @Test
  void getAllRaceTest() throws Exception {
    Race race = new Race();
    var raceInDb = raceRepository.save(race);
    var request = get("/api/race");
    var result = mockMvc.perform(request)
                        .andExpect(status().isOk())
                        .andReturn();
    var body = result.getResponse().getContentAsString();
    assertNotNull(body);
    assertEquals(raceInDb.getName(), "Speedy");
  }

}
