package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOrigin() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethods() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeaders() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControl() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(lessThan(300))
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnAlphaEndpoint() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV2Endpoint() {
        given()
            .when()
            .get("/v2")
            .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Origin", nullValue());
    }
}