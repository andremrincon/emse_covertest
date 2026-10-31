package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/0/2.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNSmallX() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/5.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNLargeX() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessEqualNPositiveX() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/-2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenN() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/4/-2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeLargeX() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/-10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjVerySmallX() {
        given()
            .when()
            .get(baseUrl + "/api/bessj/3/1e-10")
            .then()
            .statusCode(200);
    }
}