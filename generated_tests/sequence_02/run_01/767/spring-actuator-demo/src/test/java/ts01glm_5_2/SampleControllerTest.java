package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    private static String encode(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
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
        String name = "John Smith";
        given()
            .when()
                .get("/?name=" + encode(name))
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

    @Test(timeout = 60000)
    public void sayHello_withSpecialCharacters_returns200() {
        String name = "María-José O'Connor-Smith III";
        given()
            .when()
                .get("/?name=" + encode(name))
            .then()
                .statusCode(200)
                .body(equalTo("Hello María-José O'Connor-Smith III!!"));
    }
}