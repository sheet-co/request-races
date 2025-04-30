package dev.sheet_co.request_races.restassured;

import dev.sheet_co.request_races.repository.RaceRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class RaceControllerTest {
  @Autowired
  RaceRepository raceRepository;

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;

  }

  @Test
  @DisplayName("#1")
  void createRaceTest() {
    String raceJson = """
        { "name": "Tom",
          "color": "Red"
        }
        """;
    ValidatableResponse response = given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson)
        .post("/api/race")
        .then()
        .assertThat().body("name", isA(String.class))
        .assertThat().body("color", isA(String.class))
        .assertThat().body("name", equalTo("Tom"))
        .assertThat().body("color", equalTo("Red"))
        .statusCode(201);

    response.log().all();
  }

  @Test
  @DisplayName("#2")
  void createRaceTest2() {
    String raceJson = """
        { "name": "Jack",
          "color": "Rose"
        }
        """;
    given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson)
        .post("/api/race")
        .then()
        .assertThat().body("name", isA(String.class))
        .assertThat().body("color", isA(String.class))
        .assertThat().body("name", equalTo("Jack"))
        .assertThat().body("color", equalTo("Rose"))
        .statusCode(201);
  }

  @Test
  @DisplayName("#3")
  void getRacesTest() {

    ValidatableResponse response = given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/race")
        .then()
        .assertThat().body("[0].name", isA(String.class))
        .assertThat().body("[0].name", equalTo("Tom"))
        .assertThat().body("[0].color", isA(String.class))
        .assertThat().body("[0].color", equalTo("Red"))
        .assertThat().body("[1].name", isA(String.class))
        .assertThat().body("[1].name", equalTo("Jack"))
        .assertThat().body("[1].color", isA(String.class))
        .assertThat().body("[1].color", equalTo("Rose"))
        .statusCode(200);

    response.log().all();

  }

}
