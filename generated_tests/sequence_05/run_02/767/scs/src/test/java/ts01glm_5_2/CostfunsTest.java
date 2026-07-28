package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBasicCaseAllIConditionsFalse() {
        given()
        .when()
            .get("/api/costfuns/0/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsFive() {
        given()
        .when()
            .get("/api/costfuns/5/algorithm")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanMinus444() {
        given()
        .when()
            .get("/api/costfuns/-500/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsMinusFour() {
        given()
        .when()
            .get("/api/costfuns/-4/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666WithEqualCompareTo() {
        given()
        .when()
            .get("/api/costfuns/700/ababba")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEquals555AndStringBaab() {
        given()
        .when()
            .get("/api/costfuns/555/baab")
        .then()
            .statusCode(200);
    }
}