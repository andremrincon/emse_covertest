package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testGammqNormalGser() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 5.5, 2.3)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXZero() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 5.5, 0.0)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqNormalGcf() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 0.001, 1000.0)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqLargeXGcf() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 1.0, 1.0E40)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfITMAX() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 100000.0, 100001.5)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserITMAX() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 100000.0, 99999.5)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", -1.0, 2.0)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 5.5, -1.0)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqSmallAGser() {
        given()
        .when()
            .get("/api/gammq/{a}/{x}", 0.001, 0.0001)
        .then()
            .statusCode(200);
    }
}