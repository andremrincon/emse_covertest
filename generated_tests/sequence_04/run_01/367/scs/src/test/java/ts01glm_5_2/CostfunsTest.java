package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals5_sEqualsA() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanMinus444_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-500/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetweenMinus444AndMinus333_sGreaterThanAbabba() {
        given()
            .when()
                .get("/api/costfuns/-400/zzzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666_sEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetween555And666_sEqualsAbab() {
        given()
            .when()
                .get("/api/costfuns/600/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsMinus4_sEqualsA() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }
}