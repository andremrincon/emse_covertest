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
    public void testCostfuns_iEquals5_sEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/5/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444() {
        given()
            .when()
            .get("/api/costfuns/-500/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessEqualMinus333_sEqualsAbab() {
        given()
            .when()
            .get("/api/costfuns/-400/abab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        given()
            .when()
            .get("/api/costfuns/700/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterEqual555() {
        given()
            .when()
            .get("/api/costfuns/600/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4_sEqualsAbab() {
        given()
            .when()
            .get("/api/costfuns/-4/abab")
            .then()
            .statusCode(200);
    }
}