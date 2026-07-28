package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqValidGser() {
        given()
            .pathParam("a", 1.0)
            .pathParam("x", 0.5)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidGserZeroX() {
        given()
            .pathParam("a", 1.0)
            .pathParam("x", 0.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidGcf() {
        given()
            .pathParam("a", 1.0)
            .pathParam("x", 3.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 1.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .pathParam("a", 1.0)
            .pathParam("x", -1.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAType() {
        given()
            .pathParam("a", "abc")
            .pathParam("x", 1.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }
}