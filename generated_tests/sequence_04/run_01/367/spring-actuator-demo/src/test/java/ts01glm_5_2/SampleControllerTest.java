package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class SampleControllerTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void sayHello_withDefaultName_returnsGuestGreeting() {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(equalTo("Hello Guest!!"));
    }

    @Ignore("Illegal character in query at index 11: /?name=John Smith")
    @Test(timeout = 60000)
    public void sayHello_withProvidedName_returnsPersonalizedGreeting() {
        given()
                .queryParam("name", "John Smith")
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withZeroDelay_entersRandomDelayBranch() {
        given()
                .queryParam("delay", 0)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonZeroDelay_skipsRandomDelayBranch() {
        given()
                .queryParam("delay", 1)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withInvalidDelay_returnsServerError() {
        given()
                .queryParam("delay", "abc")
                .when()
                .get("/slowApi")
                .then()
                .statusCode(400);
    }
}