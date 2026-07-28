package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given()
            .queryParam("name", "John%20Smith")
        .when()
            .get("/")
        .then()
            .body(containsString("\"_links\""));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
        .when()
            .get("/")
        .then()
            .body(containsString("\"_links\""));
    }

    @Test(timeout = 60000)
    public void testSlowApiWithDefaultDelay() {
        given()
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithExplicitDelay() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(401);
    }
}