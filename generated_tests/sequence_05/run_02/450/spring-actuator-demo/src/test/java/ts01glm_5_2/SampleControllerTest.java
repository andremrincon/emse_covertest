package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void sayHello_withNameParam_returnsGreeting() throws Exception {
        given()
        .when()
            .get("/?name=" + URLEncoder.encode("John Smith", "UTF-8"))
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
    public void sayHello_withSpecialCharactersInName_returnsGreeting() throws Exception {
        given()
        .when()
            .get("/?name=" + URLEncoder.encode("María-José O'Connor-Smith III", "UTF-8"))
        .then()
            .statusCode(200)
            .body(equalTo("Hello María-José O'Connor-Smith III!!"));
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
    public void timeConsumingAPI_withSpecificDelay_returnsResult() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withInvalidDelayParam_returns500() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }
}