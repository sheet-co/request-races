package dev.sheet_co.request_races;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void givenHelloEndpoint_whenGetRequest_thenReturnsHello() {
    String response = this.restTemplate.getForObject("http://localhost:" + port + "/api/hello", String.class);
    assertThat(response).isEqualTo("Hello"); // Проверяем, что ответ равен "Hello"
  }
}