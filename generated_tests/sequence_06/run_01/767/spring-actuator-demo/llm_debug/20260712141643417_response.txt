package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void sayHello_withDefaultName_returns200AndGuestGreeting() {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withCustomName_returns200AndCustomGreeting() {
        given()
                .when()
                .get("/?name=John%20Smith")
                .then()
                .statusCode(200)
                .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withZeroDelay_entersRandomBranchAndReturns200() {
        given()
                .queryParam("delay", 0)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonZeroDelay_skipsRandomBranchAndReturns200() {
        given()
                .queryParam("delay", 1)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withInvalidDelay_returns500() {
        given()
                .queryParam("delay", "abc")
                .when()
                .get("/slowApi")
                .then()
                .statusCode(400);
    }
}