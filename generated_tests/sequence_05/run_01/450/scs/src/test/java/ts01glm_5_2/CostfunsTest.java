package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFive() {
        given()
            .when()
            .get("/api/costfuns/5/a")
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
    public void testSubjectIBetweenMinus444AndMinus333() {
        given()
            .when()
            .get("/api/costfuns/-400/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666AndSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/700/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIBetween555And666AndSGreaterThanAbabba() {
        given()
            .when()
            .get("/api/costfuns/600/z")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsMinus4AndSEqualsAbabba() {
        given()
            .when()
            .get("/api/costfuns/-4/ababba")
            .then()
            .statusCode(200);
    }
}