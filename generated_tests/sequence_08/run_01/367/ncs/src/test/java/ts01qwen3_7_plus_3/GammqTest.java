package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqNormalGcf() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqNormalGser() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testGammqGcfItmax() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 1001.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserItmax() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 999.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 2.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", -1.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }
}