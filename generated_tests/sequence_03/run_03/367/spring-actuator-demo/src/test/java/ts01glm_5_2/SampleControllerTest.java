package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("basePort", "8080"));
    }

    @Test(timeout = 60000)
    public void sayHello_withName_returnsGreeting() throws Exception {
        String encoded = URLEncoder.encode("John Smith", "UTF-8");
        given()
        .when()
            .get("/?name=" + encoded)
        .then()
            .statusCode(200)
            .body(equalTo("Hello John Smith!!"));
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

    @Test(timeout = 60000)
    public void sayHello_withSpecialCharacters_returnsGreeting() throws Exception {
        String encoded = URLEncoder.encode("María-José O'Connor-Smith III", "UTF-8");
        given()
        .when()
            .get("/?name=" + encoded)
        .then()
            .statusCode(200)
            .body(equalTo("Hello María-José O'Connor-Smith III!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withDelayZero_returnsResult() {
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
    public void timeConsumingAPI_withInvalidDelay_returnsError() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }
}