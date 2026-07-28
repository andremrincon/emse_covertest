package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectIEquals5AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNeg444() {
        given()
            .when()
                .get("/api/costfuns/-500/zzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessEqNeg333ButNotLessThanNeg444() {
        given()
            .when()
                .get("/api/costfuns/-400/ababba")
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
    public void testSubjectIEqualsNeg4() {
        given()
            .when()
                .get("/api/costfuns/-4/algorithm")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterEq555ButNotGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/600/aaa")
            .then()
                .statusCode(200);
    }
}