package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2() {
        given()
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axIsZero() {
        given()
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_smallAx() {
        given()
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeAx() {
        given()
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_backwardRecurrence() {
        given()
        .when()
            .get("/api/bessj/10/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_xNegative_nOdd() {
        given()
        .when()
            .get("/api/bessj/3/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_xNegative_nEven() {
        given()
        .when()
            .get("/api/bessj/4/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN() {
        given()
        .when()
            .get("/api/bessj/abc/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_largeN_smallX() {
        given()
        .when()
            .get("/api/bessj/100/0.1")
        .then()
            .statusCode(200);
    }
}