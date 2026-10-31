package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherHappyPath() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMGreaterThan1000() {
        given()
            .when()
            .get("/api/fisher/1001/5/0.75")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeException() {
        given()
            .when()
            .get("/api/fisher/10/-3/0.5")
            .then()
            .statusCode(200);
    }
}