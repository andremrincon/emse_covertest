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
    public void testGammqValidGserXZero() {
        given()
            .when()
                .get("/api/gammq/1.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidGserXPositive() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidGcf() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .when()
                .get("/api/gammq/-1.0/3.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .when()
                .get("/api/gammq/1.0/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAString() {
        given()
            .when()
                .get("/api/gammq/abc/3.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXString() {
        given()
            .when()
                .get("/api/gammq/1.0/abc")
            .then()
                .statusCode(400);
    }
}