package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            return s;
        }
    }

    @Test(timeout = 60000)
    public void sayHello_withNameParam_returnsGreeting() {
        given()
            .queryParam("name", enc("John Smith"))
        .when()
            .get("/")
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
    public void timeConsumingAPI_withExplicitDelay_skipsRandomAndReturnsResult() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonIntegerDelay_returnsError() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void sayHello_withSpecialCharactersInName_returnsGreeting() {
        given()
            .queryParam("name", enc("María-José O'Connor-Smith III"))
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(equalTo("Hello María-José O'Connor-Smith III!!"));
    }
}