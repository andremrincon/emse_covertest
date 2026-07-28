package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultName() {
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloCustomName() {
        given()
            .queryParam("name", "John")
        .when()
            .get("/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiDelayZero() {
        given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiDelayNonZero() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiDelayNegative() {
        given()
            .queryParam("delay", -1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }
}