package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class CostfunsTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveAndSEqualsBaab() {
        RestAssured.given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanMinus444AndSEqualsAbabba() {
        RestAssured.given()
            .when()
                .get("/api/costfuns/-445/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsMinusFourAndSEqualsA() {
        RestAssured.given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666AndSEqualsZ() {
        RestAssured.given()
            .when()
                .get("/api/costfuns/667/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEquals555AndSEqualsAbab() {
        RestAssured.given()
            .when()
                .get("/api/costfuns/555/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsMinus333AndSEqualsTest() {
        RestAssured.given()
            .when()
                .get("/api/costfuns/-333/test")
            .then()
                .statusCode(200);
    }
}