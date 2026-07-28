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
    public void testSubjectIEquals5() {
        given()
            .when()
            .get("/api/costfuns/5/algorithm")
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
    public void testSubjectIGreaterThan666() {
        given()
            .when()
            .get("/api/costfuns/667/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegative4() {
        given()
            .when()
            .get("/api/costfuns/-4/abab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/0/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEquals555SEqualsAbabba() {
        given()
            .when()
            .get("/api/costfuns/555/ababba")
            .then()
            .statusCode(200);
    }
}