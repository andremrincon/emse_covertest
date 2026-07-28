package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
    public void sayHello_withoutNameParam_returnsDefaultGuestGreeting() {
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void slowApi_withDelayZero_returns200() {
        given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void slowApi_withSpecificDelay_returnsResult() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void slowApi_withNonNumericDelay_returns400() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void slowApi_withNegativeDelay_returns500() {
        given()
            .queryParam("delay", -1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }
}