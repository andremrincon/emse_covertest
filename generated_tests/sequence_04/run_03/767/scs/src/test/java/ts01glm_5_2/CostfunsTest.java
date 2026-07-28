package ts01glm_5_2;

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
    public void testIEqualsFive() {
        given()
        .when()
            .get("/api/costfuns/5/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testStringCompareToGreaterThanZero() {
        given()
        .when()
            .get("/api/costfuns/1/z")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanNegative444() {
        given()
        .when()
            .get("/api/costfuns/-445/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNegativeFour() {
        given()
        .when()
            .get("/api/costfuns/-4/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterOrEqual555AndStringEqualsBaab() {
        given()
        .when()
            .get("/api/costfuns/555/baab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessOrEqualNegative333() {
        given()
        .when()
            .get("/api/costfuns/-333/abab")
        .then()
            .statusCode(200);
    }
}