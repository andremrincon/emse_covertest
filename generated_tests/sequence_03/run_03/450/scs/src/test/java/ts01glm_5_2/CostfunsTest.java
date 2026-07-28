package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveBranch() {
        when()
            .get("/api/costfuns/5/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectINotEqualsMinusFourFalseBranch() {
        when()
            .get("/api/costfuns/-4/baab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanMinus444Branch() {
        when()
            .get("/api/costfuns/-500/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666Branch() {
        when()
            .get("/api/costfuns/700/z")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSCompareToEqualBranch() {
        when()
            .get("/api/costfuns/1/ababba")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAllStringComparisonsFalseBranch() {
        when()
            .get("/api/costfuns/1/a")
        .then()
            .statusCode(200);
    }
}