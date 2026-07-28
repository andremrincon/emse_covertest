package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2Returns400() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndAbsXGreaterThanOrEqual8Returns200() {
        given()
            .when()
                .get("/api/bessj/3/-10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithPositiveXAndAbsXGreaterThanOrEqual8Returns200() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithXEqualsZeroReturns200() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAbsXLessThanOrEqualToNReturns200() {
        given()
            .when()
                .get("/api/bessj/5/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithInvalidNParameterReturns400() {
        given()
            .when()
                .get("/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }
}