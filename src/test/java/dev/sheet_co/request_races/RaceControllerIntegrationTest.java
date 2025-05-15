//package dev.sheet_co.request_races;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dev.sheet_co.request_races.mapper.RaceRequestMapper;
//import dev.sheet_co.request_races.model.dto.RaceRequestCreateIn;
//import dev.sheet_co.request_races.repository.RaceRequestRepository;
//import dev.sheet_co.request_races.testutil.TestModelGenerator;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//class RaceControllerIntegrationTest {
//
//
//  @Autowired
//  private WebApplicationContext webApplicationContext;
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  private RaceRequestRepository raceRepository;
//
//  @Autowired
//  private RaceRequestMapper raceMapper;
//
//  @Autowired
//  private RaceRequestCreateIn raceCreateRequest;
//
//  @Autowired
//  private TestModelGenerator modelGenerator;
//
//  @Autowired
//  private ObjectMapper objectMapper;

//  @BeforeEach
//  void setUp() {
//    mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//                             .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
//                             .apply(springSecurity())
//                             .build();
//
//    testLabel = Instancio.of(modelGenerator.getLabelModel()).create();
//    raceRepository.save(testLabel);
//
//  }
//
//  @AfterEach
//  void setup() {
//    raceRepository.deleteAll();
//
//  }
//
//  @Test
//  void createRaceTest() throws Exception {
//    String raceJson = """
//        { "name": "Speedy",
//          "color": "Red"
//        }
//        """;
//
//    var request = post("/api/race")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(raceJson);
//
//    mockMvc.perform(request).andExpect(status().isCreated());
//
//    var race = raceRepository.findRaceRequestByName("Speedy");
//    var expectedName = race.getName();
//    var actualName = "Speedy";
//    assertNotNull(race);
//    assertEquals(actualName, expectedName);
//  }
//
//
//}
