package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SampleControllerTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost");
        String port = System.getProperty("port", "8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void sayHello_withNameParam_returnsGreeting() {
        given()
        .when()
            .get("/?name=John%20Smith")
        .then()
            .statusCode(200)
            .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withoutNameParam_returnsDefaultGreeting() {
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withDelayZero_usesRandomDelay() {
        given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonZeroDelay_sleepsSpecifiedDelay() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonIntegerDelay_returns500() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNegativeDelay_returns500() {
        given()
            .queryParam("delay", -1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }
}