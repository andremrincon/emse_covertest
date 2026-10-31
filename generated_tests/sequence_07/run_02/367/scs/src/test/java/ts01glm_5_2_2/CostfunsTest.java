package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectIEquals5() {
        given()
            .when()
            .get("/api/costfuns/5/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNegative444() {
        given()
            .when()
            .get("/api/costfuns/-500/a")
            .then()
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanOrEqualNegative333() {
        given()
            .when()
            .get("/api/costfuns/-400/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666() {
        given()
            .when()
            .get("/api/costfuns/700/a")
            .then()
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegative4AndSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/-4/baab")
            .then()
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSubjectSCompareToEqualCase() {
        given()
            .when()
            .get("/api/costfuns/1/ababba")
            .then()
            .statusCode(200);
    }
}