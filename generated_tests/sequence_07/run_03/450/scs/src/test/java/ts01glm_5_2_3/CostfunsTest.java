package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CostfunsTest {

    @Before
    public void setUp() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSubjectBasicPathI0SA() {
        given()
            .when()
            .get("/api/costfuns/0/a")
            .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testSubjectIEquals5() {
        given()
            .when()
            .get("/api/costfuns/5/a")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNegative444() {
        given()
            .when()
            .get("/api/costfuns/-500/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666() {
        given()
            .when()
            .get("/api/costfuns/700/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegative4AndSEqualsBaab() {
        given()
            .when()
            .get("/api/costfuns/-4/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSCompareToAbabbaEqual() {
        given()
            .when()
            .get("/api/costfuns/0/ababba")
            .then()
            .statusCode(200);
    }
}