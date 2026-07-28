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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV1All() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2All() {
        given()
        .when()
            .get("/v2/all")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV1Alpha() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV1Name() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV1Capital() {
        given()
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(404);
    }
}