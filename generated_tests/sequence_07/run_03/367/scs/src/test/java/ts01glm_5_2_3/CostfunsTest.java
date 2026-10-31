package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectWithIEquals5() {
        given()
        .when()
            .get("/api/costfuns/5/baab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithIEqualsNegative4() {
        given()
        .when()
            .get("/api/costfuns/-4/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithILessThanNegative444() {
        given()
        .when()
            .get("/api/costfuns/-445/algorithm")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithIGreaterThan666() {
        given()
        .when()
            .get("/api/costfuns/700/c")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithSCompareToZero() {
        given()
        .when()
            .get("/api/costfuns/1/ababba")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithIBetween555And666() {
        given()
        .when()
            .get("/api/costfuns/600/a")
        .then()
            .statusCode(200);
    }
}