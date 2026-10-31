package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given()
            .urlEncodingEnabled(true)
            .queryParam("name", "John Smith")
        .when()
            .get("/")
        .then()
            .body(containsString("Hello John Smith"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
        .when()
            .get("/")
        .then()
            .body(containsString("Hello Guest"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithDefaultDelay() {
        given()
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithNonZeroDelay() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithNegativeDelay() {
        given()
            .queryParam("delay", -1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithInvalidDelayType() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }
}