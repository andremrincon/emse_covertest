package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2() {
        given()
            .when()
                .get("/api/bessj/-5/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThan8() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxEqualTo0() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxLessThanOrEqualToN() {
        given()
            .when()
                .get("/api/bessj/10/2.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndOddN() {
        given()
            .when()
                .get("/api/bessj/3/-2.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithSmallAxToTriggerRescaling() {
        given()
            .when()
                .get("/api/bessj/10/0.001")
            .then()
                .statusCode(200);
    }
}