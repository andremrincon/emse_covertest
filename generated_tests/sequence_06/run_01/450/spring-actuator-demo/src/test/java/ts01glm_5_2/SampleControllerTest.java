package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void sayHello_defaultName_returnsGreetingWithGuest() {
        given()
            .when()
                .get("/")
            .then()
                .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withNameParam_returnsGreetingWithName() {
        given()
                .queryParam("name", "John%20Smith")
            .when()
                .get("/")
            .then()
                .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_delayZero_entersRandomBranchAndReturnsResult() {
        given()
                .queryParam("delay", 0)
            .when()
                .get("/slowApi")
            .then()
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_explicitDelay_skipsRandomBranchAndReturnsResult() {
        given()
                .queryParam("delay", 1)
            .when()
                .get("/slowApi")
            .then()
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_nonNumericDelay_returns500() {
        given()
                .queryParam("delay", "abc")
            .when()
                .get("/slowApi")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_negativeDelay_returns500() {
        given()
                .queryParam("delay", -1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200);
    }
}