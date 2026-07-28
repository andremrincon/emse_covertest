package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNegative444SEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/-500/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666SGreaterThanAbabba() {
        given()
            .when()
                .get("/api/costfuns/700/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegativeFour() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIBetween555And666SEqualsAbab() {
        given()
            .when()
                .get("/api/costfuns/600/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectGeneralCaseIEqualsOneSAlgorithm() {
        given()
            .when()
                .get("/api/costfuns/1/algorithm")
            .then()
                .statusCode(200);
    }
}