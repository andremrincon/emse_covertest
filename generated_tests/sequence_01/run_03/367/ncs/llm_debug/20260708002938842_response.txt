package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessj_validInput() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThanOrEqualTo2() {
        given()
            .when()
                .get("/api/bessj/2/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_nGreaterThan1000() {
        given()
            .when()
                .get("/api/bessj/1001/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_validInput() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mGreaterThan1000() {
        given()
            .when()
                .get("/api/fisher/1001/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_nGreaterThan1000() {
        given()
            .when()
                .get("/api/fisher/10/1001/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidXCausesException() {
        given()
            .when()
                .get("/api/fisher/10/5/-1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_validInput() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalidACausesException() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_validInput() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_aExceedsLimit() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_aBelowNegativeLimit() {
        given()
            .when()
                .get("/api/remainder/-10001/5")
            .then()
                .statusCode(400);
    }
}