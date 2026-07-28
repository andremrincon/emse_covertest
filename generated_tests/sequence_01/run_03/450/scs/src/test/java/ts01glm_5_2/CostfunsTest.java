package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveAndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNegative444() {
        given()
            .when()
                .get("/api/costfuns/-445/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegativeFour() {
        given()
            .when()
                .get("/api/costfuns/-4/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/667/zzzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidIParameterReturns400() {
        given()
            .when()
                .get("/api/costfuns/one/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterEqual555Boundary() {
        given()
            .when()
                .get("/api/costfuns/555/abab")
            .then()
                .statusCode(200);
    }
}