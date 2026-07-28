package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
    public void testSubjectILessThanNeg444AndSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/-445/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNeg4AndSEqualsAbabba() {
        given()
            .when()
            .get("/api/costfuns/-4/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666AndSEqualsAbab() {
        given()
            .when()
            .get("/api/costfuns/667/abab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterEqual555AndSGreaterThanAbabba() {
        given()
            .when()
            .get("/api/costfuns/555/zzz")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessEqualNeg333AndNotLessThanNeg444() {
        given()
            .when()
            .get("/api/costfuns/-333/a")
            .then()
            .statusCode(200);
    }
}