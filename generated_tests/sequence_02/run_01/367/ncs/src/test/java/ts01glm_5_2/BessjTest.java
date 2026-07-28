package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjWithZeroXReturns200() {
        given()
            .when()
                .get("/api/bessj/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndLargeAbsReturns200() {
        given()
            .when()
                .get("/api/bessj/3/-9.0")
            .then()
                .statusCode(200);
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
    public void testBessjWithAxLessThanOrEqualNReturns200() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndSmallAbsReturns200() {
        given()
            .when()
                .get("/api/bessj/3/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithInvalidNReturns400() {
        given()
            .when()
                .get("/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }
}