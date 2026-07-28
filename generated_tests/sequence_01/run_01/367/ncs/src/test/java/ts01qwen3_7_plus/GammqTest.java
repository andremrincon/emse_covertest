package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Test;

public class GammqTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqValidGserPath() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqValidGcfPath() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 1000.0)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqXZero() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 0.0)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqNegativeX() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", -1.0)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqZeroA() {
        given()
            .pathParam("a", 0.0)
            .pathParam("x", 2.3)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNegativeA() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 2.3)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserNoConvergence() {
        given()
            .pathParam("a", 1000000.0)
            .pathParam("x", 999999.0)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfNoConvergence() {
        given()
            .pathParam("a", 1000000.0)
            .pathParam("x", 1000001.0)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAString() {
        given()
            .pathParam("a", "abc")
            .pathParam("x", 2.3)
        .when()
            .get(getBaseUrl() + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }
}