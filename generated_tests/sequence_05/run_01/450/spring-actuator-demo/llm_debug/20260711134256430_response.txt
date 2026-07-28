package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    public void sayHello_withDefaultName_returns200() {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void sayHello_withExplicitName_returns200() {
        String encoded = encode("John Smith");
        given()
                .when()
                .get("/?name=" + encoded)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void sayHello_withName_returnsCorrectBody() {
        String name = "María-José O'Connor-Smith III";
        String encoded = encode(name);
        given()
                .when()
                .get("/?name=" + encoded)
                .then()
                .body(equalTo("Hello " + name + "!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withDefaultDelayZero_returns200() {
        given()
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withExplicitDelay_returns200() {
        given()
                .queryParam("delay", 1)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withExplicitDelay_returnsCorrectBody() {
        given()
                .queryParam("delay", 0)
                .when()
                .get("/slowApi")
                .then()
                .body(equalTo("Result"));
    }

    private static String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}