package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class BessjTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeXAndLargeAbsX() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/-10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithPositiveXAndAbsXBetweenNAnd8() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAbsXLessThanOrEqualToN() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithXEqualToZero() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithInvalidParameterType() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }
}