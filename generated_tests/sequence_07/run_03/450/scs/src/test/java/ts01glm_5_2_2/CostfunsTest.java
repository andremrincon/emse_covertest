package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
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
    public void testSubjectIBetweenNegative444And333SEqualsA() {
        given()
            .when()
                .get("/api/costfuns/-400/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666SEqualsTest() {
        given()
            .when()
                .get("/api/costfuns/700/test")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIBetween555And666SEqualsAlgorithm() {
        given()
            .when()
                .get("/api/costfuns/600/algorithm")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNegativeFourSEqualsA() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }
}