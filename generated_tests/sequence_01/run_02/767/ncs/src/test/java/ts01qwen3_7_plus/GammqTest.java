package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegative() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAZero() {
        given()
            .when()
                .get("/api/gammq/0.0/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqXZero() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserPath() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserLargeA() {
        given()
            .when()
                .get("/api/gammq/100.0/100.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeA() {
        given()
            .when()
                .get("/api/gammq/100.0/200.0")
            .then()
                .statusCode(200);
    }
}