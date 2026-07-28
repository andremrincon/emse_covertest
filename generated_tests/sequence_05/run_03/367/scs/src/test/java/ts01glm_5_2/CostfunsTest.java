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
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNeg444AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-500/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNeg4Branch() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
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
    public void testSubjectILessEqualNeg333ButNotLessThanNeg444() {
        given()
            .when()
                .get("/api/costfuns/-400/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterEqual555ButNotGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/600/a")
            .then()
                .statusCode(200);
    }
}