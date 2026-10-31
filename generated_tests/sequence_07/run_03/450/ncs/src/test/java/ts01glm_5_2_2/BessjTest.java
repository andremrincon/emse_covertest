package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getenv("API_HOST");
        if (host == null) host = "localhost";
        String port = System.getenv("API_PORT");
        if (port == null) port = "8080";
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNSmallX() {
        given()
            .when()
                .get("/api/bessj/3/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNLargeX() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNLargeNegativeXOddN() {
        given()
            .when()
                .get("/api/bessj/3/-10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualN() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualNNegativeXOddN() {
        given()
            .when()
                .get("/api/bessj/3/-2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualNNegativeXEvenN() {
        given()
            .when()
                .get("/api/bessj/4/-2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNType() {
        given()
            .when()
                .get("/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }
}