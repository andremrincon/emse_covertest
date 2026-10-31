package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveAndSCompareToGreaterThanZero() {
        given()
            .when()
                .get("/api/costfuns/5/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNegative444AndAllSConditionsFalse() {
        given()
            .when()
                .get("/api/costfuns/-445/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessEqualsNegative333AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-333/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666AndSCompareToEqualsZero() {
        given()
            .when()
                .get("/api/costfuns/667/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegativeFourAllIConditionsFalse() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterEquals555ButNotGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/555/a")
            .then()
                .statusCode(200);
    }
}