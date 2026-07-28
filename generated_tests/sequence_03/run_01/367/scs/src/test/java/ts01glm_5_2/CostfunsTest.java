package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
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
    public void testSubjectILessThanNegative444Branch() {
        given()
            .when()
            .get("/api/costfuns/-500/a")
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
    public void testSubjectIEqualsNegativeFourAndSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/-4/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSCompareToAbabbaGreaterThanZero() {
        given()
            .when()
            .get("/api/costfuns/0/z")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidIntegerParameterReturns400() {
        given()
            .when()
            .get("/api/costfuns/one/test")
            .then()
            .statusCode(400);
    }
}