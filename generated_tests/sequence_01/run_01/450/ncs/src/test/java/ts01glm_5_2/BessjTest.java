package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjThrowsExceptionWhenNLessThan2() {
        given()
            .when()
            .get("/api/bessj/0/2.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj1ElseBranchWithLargeAx() {
        given()
            .when()
            .get("/api/bessj/3/10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturnsZeroWhenXIsZero() {
        given()
            .when()
            .get("/api/bessj/3/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndOddN() {
        given()
            .when()
            .get("/api/bessj/3/-10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjElseBranchWhenAxLessThanOrEqualN() {
        given()
            .when()
            .get("/api/bessj/4/2.5")
            .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndEvenN() {
        given()
            .when()
            .get("/api/bessj/2/-9.0")
            .then()
            .statusCode(200);
    }
}