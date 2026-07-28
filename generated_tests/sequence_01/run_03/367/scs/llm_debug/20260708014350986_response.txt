package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_LessThanMinus444() {
        given()
            .pathParam("i", -445)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_GreaterThanOrEqual555() {
        given()
            .pathParam("i", 555)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_StringEqualsBaab() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_StringCompareToGreaterThanZero() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "z")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_StringCompareToGreaterThanOrEqualZero() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_StringNotEqualsAbab() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "notabab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}