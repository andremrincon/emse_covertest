package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = System.getenv("BASE_URL") != null ?
            System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2() {
        given()
            .when()
            .get("/api/bessj/1/2.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithXZero() {
        given()
            .when()
            .get("/api/bessj/3/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndLessThan8() {
        given()
            .when()
            .get("/api/bessj/2/5.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxLessThanOrEqualToN() {
        given()
            .when()
            .get("/api/bessj/3/1.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndOddN() {
        given()
            .when()
            .get("/api/bessj/3/-2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndGreaterThanOrEqualTo8() {
        given()
            .when()
            .get("/api/bessj/3/10.0")
            .then()
            .statusCode(200);
    }
}