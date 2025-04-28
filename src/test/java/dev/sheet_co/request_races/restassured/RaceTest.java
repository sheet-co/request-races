package dev.sheet_co.request_races.restassured;

import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.repository.RaceRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class RaceTest {
  @Autowired
  RaceRepository raceRepository;

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
  }

//  @BeforeEach
//  void setupRaceTest() {
//    String raceJson = """
//        { "name": "SetupRaceTest",
//          "color": "************"
//        }
//        """;
//    given()
//        .when()
//        .contentType(ContentType.JSON)
//        .body(raceJson)
//        .post("/api/race")
//        .then()
//        .statusCode(201);
//
//  }

  @AfterEach
  void cleanUp() {
    raceRepository.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("#1")
  void createRaceTest() {
    String raceJson = """
        { "name": "Tom",
          "color": "Red"
        }
        """;
    given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson)
        .post("/api/race")
        .then()
        .statusCode(201);
  }

  @Test
  @Order(2)
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
        .statusCode(201);
  }

  @Test
  @Order(3)
  @DisplayName("#3")
  void getRacesTest() {

//    List<Race> races = given()
//        .contentType(ContentType.JSON)
//        .when()
//        .get("/api/race")
//        .then().log().body().extract().jsonPath().getList("", Race.class);
//    races.forEach(System.out::println);
    String raceJson = """
        { "name": "Fred from 3",
          "color": "Black"
        }
        """;
    given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson)
        .post("/api/race")
        .then()
        .statusCode(201);

    String raceJson2 = """
        { "name": "Molly from 3",
          "color": "Black"
        }
        """;
    given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceJson2)
        .post("/api/race")
        .then()
        .statusCode(201);

    ValidatableResponse response = given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/race")
        .then()
        .statusCode(200);

    response.log().all();

  }
}
