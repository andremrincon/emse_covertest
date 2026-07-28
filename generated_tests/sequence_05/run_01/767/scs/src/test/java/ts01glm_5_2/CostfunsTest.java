package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveBranch() {
        given()
            .when()
            .get("/api/costfuns/5/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanMinus444AndSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/-500/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsMinusFourAndSEqualsAbabba() {
        given()
            .when()
            .get("/api/costfuns/-4/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666Branch() {
        given()
            .when()
            .get("/api/costfuns/700/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectCompareToGreaterThanZeroWithZzzz() {
        given()
            .when()
            .get("/api/costfuns/0/zzzz")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanMinus333ButNotLessThanMinus444() {
        given()
            .when()
            .get("/api/costfuns/-350/abab")
            .then()
            .statusCode(200);
    }
}