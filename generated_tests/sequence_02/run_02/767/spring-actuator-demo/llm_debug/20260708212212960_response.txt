package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given()
            .queryParam("name", "John%20Smith")
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(containsString("\"_links\""));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithoutName() {
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(containsString("\"_links\""));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithZeroDelay() {
        given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithPositiveDelay() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithInvalidDelay() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }
}