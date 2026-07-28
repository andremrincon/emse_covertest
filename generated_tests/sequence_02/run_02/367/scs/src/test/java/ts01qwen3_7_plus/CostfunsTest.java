package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIEquals5AndSEqualsBaab() {
        given()
        .when()
            .get("/api/costfuns/5/baab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchILessThanMinus444() {
        given()
        .when()
            .get("/api/costfuns/-500/algorithm")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIBetweenMinus444AndMinus333() {
        given()
        .when()
            .get("/api/costfuns/-400/ababba")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIGreaterThan666() {
        given()
        .when()
            .get("/api/costfuns/700/xyz")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIBetween555And666() {
        given()
        .when()
            .get("/api/costfuns/600/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIEqualsMinus4() {
        given()
        .when()
            .get("/api/costfuns/-4/abab")
        .then()
            .statusCode(200);
    }
}