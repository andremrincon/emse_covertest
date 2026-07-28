package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveBranch() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanMinus444Branch() {
        given()
            .pathParam("i", -500)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666Branch() {
        given()
            .pathParam("i", 700)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsMinusFourBranch() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSEqualsBaabBranch() {
        given()
            .pathParam("i", 1)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSCompareToAbabbaZeroBranch() {
        given()
            .pathParam("i", 1)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}