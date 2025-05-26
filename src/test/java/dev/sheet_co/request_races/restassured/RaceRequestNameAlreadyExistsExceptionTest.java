package dev.sheet_co.request_races.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class RaceRequestNameAlreadyExistsExceptionTest {

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
  }

  @Test
  @DisplayName("#1 Repeat name")
  void raceRequestNameAlreadyExistsException_RepeatNamePOST_test() {

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

    var raceRepeatJson = """
        {  "name": "Tom",
          "color": "Red"
        }
        """;

    ValidatableResponse validatableResponse = given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .body(raceRepeatJson)
        .post("/api/race-request")
        .then()
        .statusCode(409)
        .body("title", equalTo("NameAlreadyExistsException"));

    validatableResponse.log().all();
  }

}

