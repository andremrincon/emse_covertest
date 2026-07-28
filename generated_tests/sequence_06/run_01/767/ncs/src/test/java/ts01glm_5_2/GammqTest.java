package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqGserNormalPath() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath() {
        given()
            .pathParam("a", 0.001)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserXZero() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegative() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 3.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegative() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", -1.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNonNumericA() {
        given()
            .pathParam("a", "abc")
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserNonConvergingLargeA() {
        given()
            .pathParam("a", 100.0)
            .pathParam("x", 100.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAZero() {
        given()
            .pathParam("a", 0.0)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserSmallAXLarge() {
        given()
            .pathParam("a", 0.5)
            .pathParam("x", 10.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }
}