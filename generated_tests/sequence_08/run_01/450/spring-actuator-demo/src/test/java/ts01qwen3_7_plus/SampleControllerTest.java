package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("test.base.url", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given()
            .queryParam("name", "John%20Smith")
        .when()
            .get("/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithZeroDelay() {
        given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithNonZeroDelay() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithInvalidDelay() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }
}