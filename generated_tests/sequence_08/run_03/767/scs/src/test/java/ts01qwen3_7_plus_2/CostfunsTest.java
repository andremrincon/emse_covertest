package ts01qwen3_7_plus_2;

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
    public void testCostfuns_I5_SBaba() {
        given()
            .when()
                .get("/api/costfuns/5/baba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_ILessThanMinus444() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_IGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_IMinus4_SA() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_I0_SC() {
        given()
            .when()
                .get("/api/costfuns/0/c")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_InitCoverage() {
        given()
            .when()
                .get("/api/costfuns/1/algorithm")
            .then()
                .statusCode(200);
    }
}