package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testCORSFilterExecutionOnV1All() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCORSFilterExecutionOnV1Alpha() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCORSFilterExecutionOnV1Name() {
        given()
            .when()
            .get("/v1/name/France")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCORSFilterExecutionOnV1Capital() {
        given()
            .when()
            .get("/v1/capital/London")
            .then()
            .statusCode(404);
    }
}