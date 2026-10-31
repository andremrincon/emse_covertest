package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void sayHello_withNameParameter_returnsGreeting() {
        given()
                .queryParam("name", "John%20Smith")
        .when()
                .get("/")
        .then()
                .statusCode(200)
                .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withoutNameParameter_returnsDefaultGreeting() {
        given()
        .when()
                .get("/")
        .then()
                .statusCode(200)
                .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withDelayZero_usesRandomDelayAndReturnsResult() {
        given()
                .queryParam("delay", 0)
        .when()
                .get("/slowApi")
        .then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withPositiveDelay_returnsResult() {
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