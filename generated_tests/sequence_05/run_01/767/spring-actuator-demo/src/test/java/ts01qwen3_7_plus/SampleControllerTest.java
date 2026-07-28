package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultName() {
        given()
            .when()
                .get("/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloCustomName() throws Exception {
        String encoded = URLEncoder.encode("John Smith", "UTF-8");
        given()
            .queryParam("name", encoded)
            .when()
                .get("/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiZeroDelay() {
        given()
            .queryParam("delay", 0)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiPositiveDelay() {
        given()
            .queryParam("delay", 1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiInvalidDelay() {
        given()
            .queryParam("delay", "abc")
            .when()
                .get("/slowApi")
            .then()
                .statusCode(401);
    }
}