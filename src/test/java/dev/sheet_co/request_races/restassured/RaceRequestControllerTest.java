package dev.sheet_co.request_races.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 *  All tests start in the order set by the developer, one after another.
 *  This will be fixed later :)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class RaceRequestControllerTest {

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
  }

  @Test
  @DisplayName("#1 Posting race")
  void createRaceTest() {
    var raceJson = """
        {  "name": "Tom",
          "color": "Red"
        }
        """;
    given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson)
        .post("/api/race-request")
        .then()
        .assertThat().body("id", equalTo(1))
        .assertThat().body("name", isA(String.class))
        .assertThat().body("color", isA(String.class))
        .assertThat().body("name", equalTo("Tom"))
        .assertThat().body("color", equalTo("Red"))
        .statusCode(201);
  }

  @Test
  @DisplayName("#2 Posting race")
  void createRaceTest2() {
    String raceJson = """
        {  "name": "Jack",
          "color": "Rose"
        }
        """;
    given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson)
        .post("/api/race-request")
        .then()
        .assertThat().body("id", equalTo(2))
        .assertThat().body("name", isA(String.class))
        .assertThat().body("color", isA(String.class))
        .assertThat().body("name", equalTo("Jack"))
        .assertThat().body("color", equalTo("Rose"))
        .statusCode(201);
  }

  @Test
  @DisplayName("#3 Getting all races")
  void getRacesTest() {

    ValidatableResponse response = given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/race-request")
        .then()
        .statusCode(200)
        .assertThat().body("[0].name", isA(String.class))
        .assertThat().body("[0].name", equalTo("Tom"))
        .assertThat().body("[0].color", isA(String.class))
        .assertThat().body("[0].color", equalTo("Red"))
        .assertThat().body("[1].name", isA(String.class))
        .assertThat().body("[1].name", equalTo("Jack"))
        .assertThat().body("[1].color", isA(String.class))
        .assertThat().body("[1].color", equalTo("Rose"));

    response.log().all();
  }

  @Test
  @DisplayName("#4. Getting race by id")
  void getRaceByIdTest() {

    String actualName = "Tom";
    String expectedId = "1";

    ValidatableResponse response = given()
        .contentType(ContentType.JSON)
        .pathParam("id", expectedId)
        .when()
        .get("/api/race-request/{id}")
        .then()
        .statusCode(200)
        .assertThat().body("name", isA(String.class))
        .assertThat().body("name", equalTo(actualName));

    response.log().all();
  }

  @Test
  @DisplayName("#5. Put race by id")
  void putRaceByIdTest() {

    String createRaceJson = """
        { 
          "name": "Initial name",
          "color": "Initial color"
        }
        """;

        var raceId = given() // POST
            .contentType(ContentType.JSON)
            .body(createRaceJson)
            .when()
            .post("api/race-request")
            .then()
            .statusCode(201)
            .extract()
            .path("id");

    var updateRaceJson = """
        { 
          "name": "Update name",
          "color": "Update color"
        }
        """;

    given() // PUT
        .contentType(ContentType.JSON)
        .body(updateRaceJson)
        .when()
        .put("api/race-request/{id}", raceId)
        .then()
        .statusCode(200);

    given() // GET
        .contentType(ContentType.JSON)
        .when()
        .get("api/race-request/{id}", raceId)
        .then()
        .statusCode(200)
        .body("name", equalTo("Update name"))
        .body("color", equalTo("Update color"));

    given() // DELETE
        .contentType(ContentType.JSON)
        .when()
        .delete("api/race-request/{id}", raceId)
        .then()
        .statusCode(is(204));
  }

  @Test
  @DisplayName("#6. Delete race by id")
  void deleteRaceByIdTest() {
    var actualId = 1;
    ValidatableResponse response = given()
        .contentType(ContentType.JSON)
        .pathParam("id", actualId)
        .delete("/api/race-request/{id}")
        .then()
        .statusCode(204);

    response.log().all();
  }

}
