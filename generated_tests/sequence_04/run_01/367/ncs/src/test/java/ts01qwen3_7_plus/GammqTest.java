package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GammqTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testGammqNormalGser() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get(BASE_URL + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqNormalGcf() {
        given()
            .pathParam("a", 0.001)
            .pathParam("x", 1000.0)
        .when()
            .get(BASE_URL + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXZero() {
        given()
            .pathParam("a", 1.0)
            .pathParam("x", 0.0)
        .when()
            .get(BASE_URL + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 3.0)
        .when()
            .get(BASE_URL + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", -1.0)
        .when()
            .get(BASE_URL + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqLargeA() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 2000.0)
        .when()
            .get(BASE_URL + "/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }
}