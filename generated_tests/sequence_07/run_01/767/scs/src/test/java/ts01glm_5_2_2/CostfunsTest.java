package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("app.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveBranch() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testINotEqualsMinusFourFalseBranch() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanMinus444TrueBranch() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666TrueBranch() {
        given()
            .when()
                .get("/api/costfuns/700/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSEqualsBaabTrueBranch() {
        given()
            .when()
                .get("/api/costfuns/1/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSCompareToGreaterThanZeroTrueBranch() {
        given()
            .when()
                .get("/api/costfuns/1/z")
            .then()
                .statusCode(200);
    }
}