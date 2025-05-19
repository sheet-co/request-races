package dev.sheet_co.request_races.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RaceRequestNotFoundExceptionTest {

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
  }

  @Test
  void raceRequestNotFoundException_GET_Test() {

    Long raceId = 1L;
    given() // GET
            .contentType(ContentType.JSON)
            .when()
            .get("api/race-request/{id}", raceId)
            .then()
            .statusCode(404)
            .body("message", equalTo("RaceRequest not found"));
  }

  @Test
  void raceRequestNotFoundException_PUT_Test() {

    Long raceId = 1L;
    String json = """
         { 
          "name": "Initial name",
          "color":"Initial color"
        }
        """;
    given() // PUT
            .contentType(ContentType.JSON)
            .body(json)
            .when()
            .put("api/race-request/{id}", raceId)
            .then()
            .statusCode(404)
            .body("message", equalTo("RaceRequest not found"));
  }

}

