package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFive() {
        given()
            .when()
                .get("/api/costfuns/5/algorithm")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanMinus444() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanOrEqualMinus333WithBaab() {
        given()
            .when()
                .get("/api/costfuns/-400/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsMinusFourWithAbab() {
        given()
            .when()
                .get("/api/costfuns/-4/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIBetween555And666() {
        given()
            .when()
                .get("/api/costfuns/600/ababba")
            .then()
                .statusCode(200);
    }
}