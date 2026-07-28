package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testGserPositiveX() {
        given()
            .when()
            .get("/api/gammq/5.5/2.3")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPath() {
        given()
            .when()
            .get("/api/gammq/5.5/1000.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidA() {
        given()
            .when()
            .get("/api/gammq/-1.0/3.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeXNegative() {
        given()
            .when()
            .get("/api/gammq/5.5/-1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .when()
            .get("/api/gammq/0.001/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidParamType() {
        given()
            .when()
            .get("/api/gammq/abc/2.3")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserNonConvergence() {
        given()
            .when()
            .get("/api/gammq/100000.0/100000.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfBoundary() {
        given()
            .when()
            .get("/api/gammq/5.5/6.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserSmallA() {
        given()
            .when()
            .get("/api/gammq/0.001/0.5")
            .then()
            .statusCode(200);
    }
}