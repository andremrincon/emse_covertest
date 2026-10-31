package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @Before
    public void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2Returns400() {
        given()
        .when()
            .get("/api/bessj/0/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZeroReturns200() {
        given()
        .when()
            .get("/api/bessj/3/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithSmallAx() {
        given()
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithLargeAx() {
        given()
        .when()
            .get("/api/bessj/3/10")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualNBranch() {
        given()
        .when()
            .get("/api/bessj/3/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
        .when()
            .get("/api/bessj/3/-2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenN() {
        given()
        .when()
            .get("/api/bessj/4/-10")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTypeReturns400() {
        given()
        .when()
            .get("/api/bessj/abc/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjMinimalNWithSmallX() {
        given()
        .when()
            .get("/api/bessj/2/0.5")
        .then()
            .statusCode(400);
    }
}