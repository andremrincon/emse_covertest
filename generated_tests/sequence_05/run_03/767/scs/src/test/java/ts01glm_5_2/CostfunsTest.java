package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
    public void testSubjectILessThanNegative444AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-445/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegativeFourAndCompareToZero() {
        given()
            .when()
                .get("/api/costfuns/-4/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666AndCompareToNegative() {
        given()
            .when()
                .get("/api/costfuns/667/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessOrEqualNegative333AndCompareToNegative() {
        given()
            .when()
                .get("/api/costfuns/-333/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterEqual555AndCompareToPositive() {
        given()
            .when()
                .get("/api/costfuns/555/zzzz")
            .then()
                .statusCode(200);
    }
}