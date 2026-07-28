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
    }

    @Test(timeout = 60000)
    public void sayHello_withDefaultName_returnsGuestGreeting() {
        given()
            .when()
                .get("/")
            .then()
                .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withSpecificName_returnsPersonalizedGreeting() {
        given()
            .param("name", "John%20Smith")
            .when()
                .get("/")
            .then()
                .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withZeroDelay_generatesRandomDelay() {
        given()
            .param("delay", 0)
            .when()
                .get("/slowApi")
            .then()
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withPositiveDelay_returnsResult() {
        given()
            .param("delay", 1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNegativeDelay_returnsServerError() {
        given()
            .param("delay", -1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200);
    }
}